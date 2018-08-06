package com.gxh.apserver.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.gxh.apserver.dto.PromoCommentDTO;
import com.gxh.apserver.entity.*;
import com.gxh.apserver.repository.interfaces.*;
import com.gxh.apserver.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.dto.PromoDTO;
import com.gxh.apserver.dto.StatusChangeDTO;
import com.gxh.apserver.exceptions.InvalidStatusException;
import com.gxh.apserver.exceptions.ResourceNotFoundException;
import com.gxh.apserver.helper.PromotionDTOHelper;
import com.gxh.apserver.service.interfaces.PromotionService;

@Service(value = "promotionService")
public class PromotionServiceImpl implements PromotionService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RateCardRepository rateCardRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private DualMailerRepository dualMailerRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PromotionLevelRateCardRepository promotionLevelRateCardRepository;
    @Autowired
    private PromotionDTOHelper promotionDTOHelper;
    @Autowired
    private PromoCommentRepository promoCommentRepository;

    @Override
    public PromoDTO getSupplierPromo(Long supplierID,Date promoYear) throws ResourceNotFoundException,InvalidStatusException {
        logger.info(">>> getSupplierPromo");
        Optional<Supplier> supplier = supplierRepository.findById(supplierID);

        if(supplier.isPresent()) {
            Optional<Promotion> promo = promotionRepository.findSupplierPromotionByYear(supplier.get(),promoYear);

            if(promo.isPresent()) {
                logger.info("Promo is present");
                return promotionDTOHelper.buildExistingPromoDTO(supplier.get(),promo.get());

            } else {
                logger.info("Promo is not present");
                return promotionDTOHelper.buildNewPromoDTO(supplier.get());
            }
        } else {
            throw new ResourceNotFoundException("Supplier not found with ID:"+supplierID);
        }
    }

    @Override
    public boolean saveSupplierPromo(final PromoDTO promoDTO) throws ParseException {
        logger.info(">>> saveSupplierPromo");
        Optional<Supplier> supplier = supplierRepository.findById(promoDTO.getUserid());
        Optional<Promotion> currentPromotion = promotionRepository.findSupplierPromotionByID(supplier.get());

        if(currentPromotion.isPresent()) {
            logger.info("Promo is present..updating");
            Optional<List<PromotionLevelRateCard>> ratecardDms = promotionLevelRateCardRepository.findAllByPromoID(currentPromotion.get().getId());

            //Get rate card for dual mailers
            List<RateCard> rateCards = rateCardRepository.findAll();
            List<DualMailer> dms = dualMailerRepository.findAll();
            Map<String,PromotionLevelRateCard> rcdmMap = new HashMap<>();

            for (PromotionLevelRateCard rcdm : ratecardDms.get()) {
                //(K,V) = (rateCardID+DuailmailerID, PromotionLevelRateCard object)
                rcdmMap.put(rcdm.getRateCard().toString()+rcdm.getDualMailer().toString(),rcdm);
            }

            //loop DC:Rate card
            for(int i=0;i<rateCards.size();i++) {
                RateCard rateCard = rateCards.get(i);
                for(int j=0;j<rateCards.size();j++) {
                    DualMailer dm = dms.get(j);

                    PromotionLevelRateCard prc = null;
                    prc = rcdmMap.get(rateCard.getId().toString()+dm.getId().toString());

                    prc.setPromo(currentPromotion.get().getId());
                    prc.setRateCard(rateCard.getId());
                    prc.setDualMailer(dm.getId());
                    prc.setValue(promoDTO.getRatecards().get(i).getDualmailers().get(j).getValue());

                    promotionLevelRateCardRepository.save(prc);

                }
            }
        } else {
            logger.info("Promo is not present.Saving");
            //Create promo
            Promotion promo = new Promotion();
            promo.setStatus(PromotionStatus.ACTIVE);
            promo.setSupplier(supplier.get());

            String pattern = "yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date date = simpleDateFormat.parse(promoDTO.getPromoyear());

            promo.setYear((date));

            Promotion savedPromo =  promotionRepository.saveAndFlush(promo);

            //Save rate card for dual mailers
            List<RateCard> rateCards = rateCardRepository.findAll();
            List<DualMailer> dms = dualMailerRepository.findAll();
            //loop DC:Rate card
            for(int i=0;i<rateCards.size();i++) {
                RateCard rateCard = rateCards.get(i);
                for(int j=0;j<rateCards.size();j++) {
                    DualMailer dm = dms.get(j);

                    PromotionLevelRateCard prc = null;
                    prc = new PromotionLevelRateCard();

                    prc.setPromo(savedPromo.getId());
                    prc.setRateCard(rateCard.getId());
                    prc.setDualMailer(dm.getId());
                    prc.setValue(promoDTO.getRatecards().get(i).getDualmailers().get(j).getValue());

                    promotionLevelRateCardRepository.save(prc);

                }
            }
        }

        return new Boolean(true);
    }

    @Override
    public boolean saveManagerComment(PromoCommentDTO promoCommentDTO) throws ParseException {
        logger.info(">>> saveManagerComment");
        Optional<Supplier> supplier = supplierRepository.findById(promoCommentDTO.getSupplierid());

        Date promoDate = DateUtil.convertFromStringTODate(promoCommentDTO.getPromoYear());
        Optional<Promotion> currentPromotion = promotionRepository.findSupplierPromotionByYear(supplier.get(),promoDate);

        //save comment
        PromoComments newComment = new PromoComments();
        newComment.setPromotion(currentPromotion.get());
        newComment.setComment(promoCommentDTO.getComment());

        promoCommentRepository.save(newComment);

        Promotion promo = currentPromotion.get();
        promo.setStatus(PromotionStatus.REJECTED);

        promotionRepository.save(promo);

        return true;
    }

    @Override
    public PromoDTO getSupplierPromoForManager(Long supplierID, Date promoYear) throws ResourceNotFoundException, InvalidStatusException {
        logger.info(">>> getSupplierPromoForManager");
        Optional<Supplier> supplier = supplierRepository.findById(supplierID);

        if(supplier.isPresent()) {
            Optional<Promotion> promo = promotionRepository.findSupplierPromotionByYearAndStatus(supplier.get(),promoYear,PromotionStatus.SUBMITTED);

            if(promo.isPresent()) {
                logger.info("Promo is present");
                return promotionDTOHelper.buildExistingPromoDTO(supplier.get(),promo.get());

            } else {
                logger.info("Promo is not present");
                PromoDTO promoDTO = new PromoDTO();
                promoDTO.setHasError(true);
                promoDTO.setErrorMessage("Suppiler Doenot have any active promo");
                return promoDTO;
            }
        } else {
            throw new ResourceNotFoundException("Supplier not found with ID:"+supplierID);
        }
    }

	@Override
	public boolean changePromotionStatus(StatusChangeDTO statusDTO) throws ParseException {
        logger.info(">>> changePromotionStatus");
		Optional<Supplier> supplier = supplierRepository.findById(statusDTO.getSupplierid());
		
		if(supplier.isPresent()) {
            Date promoDate = DateUtil.convertFromStringTODate(statusDTO.getPromoYear());
			Optional<Promotion> promo = promotionRepository.findSupplierPromotionByYearAndStatus(supplier.get(),promoDate,PromotionStatus.valueOf(statusDTO.getCurrentStatus()));

			Promotion currentPromo = promo.get();
            currentPromo.setStatus(PromotionStatus.valueOf(statusDTO.getStatusChangeTo()));

            promotionRepository.save(currentPromo);
            return true;
		} else {
			return false;	
		}
	}
}
