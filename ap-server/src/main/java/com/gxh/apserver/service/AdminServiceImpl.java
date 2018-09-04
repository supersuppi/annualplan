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

    @Override
    @Transactional
    public boolean savePromotion(AdminPromoDTO adminPromoDTO) {
        logger.info(">>> saveAdminPromotion");
        final User user = userRepository.findByEmail(adminPromoDTO.getUserName());

        if(user != null) {
            logger.info("User Found.");
            Optional<Promotion> mainPromo = promotionRepository.findPromotionByUser(user);

            if(mainPromo.isPresent()){
                logger.info("Promotion Present.Updating.");
                //TODO: update existing promo
            } else {
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
                    //Don't save empty values
                    if(dualMailer.getEndDate()!= null || dualMailer.getStartDate()!=null){
                        DualMailer newDualMailer = new DualMailer();
                        newDualMailer.setPromotion(savedPromo);
                        newDualMailer.setCode(dualMailer.getCode());
                        try {
                            newDualMailer.setStartDate(DateUtil.convertFromStringTODate(dualMailer.getStartDate()));
                            newDualMailer.setEndDate(DateUtil.convertFromStringTODate(dualMailer.getEndDate()));
                        } catch (ParseException e) {
                            logger.error(e.getMessage());
                        }
                        dualMailerRepository.save(newDualMailer);
                    }
                });
            }
        } else {
            throw new ResourceNotFoundException("User didn't find:"+adminPromoDTO.getUserName());
        }
        logger.info("<<< saveAdminPromotion");
        return true;
    }

    @Override
    public AdminPromoDTO getPromotionByStatus(PromotionStatus status) throws ParseException {
        logger.info(">>> getPromotionByStatus");
        Optional<Promotion> promo = promotionRepository.findPromotionByStatus(status);
        if(promo.isPresent()) {

        }

        return null;
    }

    @Override
    public boolean activatePromotion() {
        logger.info(">>> activatePromotion");
        Optional<Promotion> promo = promotionRepository.findPromotionByStatus(PromotionStatus.DRAFT);
        if(promo.isPresent()) {
            logger.info("Found promotion with state DRAFT.Activating");
            Promotion currentPromo = promo.get();
            currentPromo.setStatus(PromotionStatus.ACTIVE);
            promotionRepository.save(currentPromo);
            logger.info("<<< activatePromotion");
            return true;
        }
        logger.info("<<< activatePromotion");
        return false;
    }
}
