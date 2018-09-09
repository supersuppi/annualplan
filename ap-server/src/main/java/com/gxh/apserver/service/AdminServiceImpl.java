package com.gxh.apserver.service;

import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.dto.AdminPromoDTO;
import com.gxh.apserver.entity.*;
import com.gxh.apserver.exceptions.ResourceNotFoundException;
import com.gxh.apserver.repository.interfaces.*;
import com.gxh.apserver.service.interfaces.AdminService;
import com.gxh.apserver.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnnualPromotionRepository annualPromotionRepository;
    @Autowired
    private RateCardRepository rateCardRepository;
    @Autowired
    private DualMailerRepository dualMailerRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    @Transactional
    public boolean savePromotion(AdminPromoDTO adminPromoDTO) {
        logger.info(">>> saveAdminPromotion");
        final User user = userRepository.findByEmail(adminPromoDTO.getUserName());

        if(user != null) {
            logger.info("User Found.");
            Optional<Promotion> mainPromo = promotionRepository.findPromotionByUser(user);

                logger.info("Promotion not present.Creating new.");
                Promotion newPromo = new Promotion();
                newPromo.setCreatedByUser(user);
                newPromo.setModifiedByUser(user);
                newPromo.setStatus(PromotionStatus.DRAFT);
                newPromo.setName(adminPromoDTO.getName());

                Promotion savedPromo = promotionRepository.saveAndFlush(newPromo);
                logger.info("Saving RateCards.");
                adminPromoDTO.getRatecards().forEach((rateCard) -> {
                    RateCard newRateCard = new RateCard();
                    newRateCard.setPromotion(savedPromo);
                    newRateCard.setCode(rateCard.getCode());
                    newRateCard.setName(rateCard.getName());
                    newRateCard.setRateCardDollar(Float.valueOf(rateCard.getRate()));
                    //TODO: set proper promo mechanics
                    rateCardRepository.save(newRateCard);
                });

                logger.info("Saving DualMailers.");
                adminPromoDTO.getDualmailers().forEach((dualMailer) -> {
                        DualMailer newDualMailer = new DualMailer();
                        newDualMailer.setPromotion(savedPromo);
                        newDualMailer.setCode(dualMailer.getCode());
                        try {
                            newDualMailer.setStartDate(DateUtil.convertFromStringTODate(dualMailer.getStartDate()));
                            newDualMailer.setEndDate(DateUtil.convertFromStringTODate(dualMailer.getEndDate()));
                            logger.info("Date-ssk-"+DateUtil.convertFromStringTODate(dualMailer.getStartDate()));
                        } catch (ParseException e) {
                            logger.error(e.getMessage());
                        }
                        dualMailerRepository.save(newDualMailer);
                });
            }
        logger.info("<<< saveAdminPromotion");
        return true;
    }

    @Override
    public List<AdminPromoDTO> getPromotionsByStatus(PromotionStatus status) throws ParseException {
        logger.info(">>> getPromotionByStatus");
        Optional<List<Promotion>> promoList = promotionRepository.findAllPromotionByStatus(status);

        if(promoList.isPresent()) {
            List<AdminPromoDTO> promodtoList = new ArrayList<>();
            promoList.get().forEach((promotion -> {
                AdminPromoDTO pdto = new AdminPromoDTO();
                pdto.setName(promotion.getName());
                pdto.setPid(promotion.getId());
                pdto.setPstatus(promotion.getStatus().toString());

                promodtoList.add(pdto);
            }));
            logger.info("<<< getPromotionByStatus");
            return promodtoList;
        } else {
            logger.info("No Active promo present");
            logger.info("<<< getPromotionByStatus");
            return null;
        }
    }

    @Override
    public boolean activatePromotion(Long promoID) {
        logger.info(">>> activatePromotion");
        Optional<Promotion> promo = promotionRepository.findById(promoID);
        if(promo.isPresent()) {
            logger.info("Found promotion with state DRAFT.Activating");
            Promotion currentPromo = promo.get();
            currentPromo.setStatus(PromotionStatus.ACTIVE);
            promotionRepository.save(currentPromo);

            //Create Notification
            Notification notification = new Notification();
            notification.setMessage("Promotion "+currentPromo.getName()+" "+"is active now");
            notificationRepository.save(notification);

            logger.info("notification created");
            logger.info("<<< activatePromotion");
            return true;
        }

        logger.info("<<< activatePromotion");
        return false;
    }
}
