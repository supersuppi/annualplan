package com.gxh.apserver.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.dto.PromoDTO;
import com.gxh.apserver.dto.StatusChangeDTO;
import com.gxh.apserver.entity.DualMailer;
import com.gxh.apserver.entity.Promotion;
import com.gxh.apserver.entity.PromotionLevelRateCard;
import com.gxh.apserver.entity.RateCard;
import com.gxh.apserver.entity.Supplier;
import com.gxh.apserver.exceptions.InvalidStatusException;
import com.gxh.apserver.exceptions.ResourceNotFoundException;
import com.gxh.apserver.helper.PromotionDTOHelper;
import com.gxh.apserver.repository.interfaces.DualMailerRepository;
import com.gxh.apserver.repository.interfaces.ProductRepository;
import com.gxh.apserver.repository.interfaces.PromotionLevelRateCardRepository;
import com.gxh.apserver.repository.interfaces.PromotionRepository;
import com.gxh.apserver.repository.interfaces.RateCardRepository;
import com.gxh.apserver.repository.interfaces.SupplierRepository;
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

    @Override
    public PromoDTO getSupplierPromo(Long supplierID,Date promoYear) throws ResourceNotFoundException,InvalidStatusException {

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
    public Boolean saveSupplierPromo(final PromoDTO promoDTO) throws ParseException {

        Optional<Supplier> supplier = supplierRepository.findById(promoDTO.getUserid());

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

        return new Boolean(true);
    }

    @Override
    public PromoDTO getSupplierPromoForManager(Long supplierID, Date promoYear) throws ResourceNotFoundException, InvalidStatusException {

        Optional<Supplier> supplier = supplierRepository.findById(supplierID);

        if(supplier.isPresent()) {
            Optional<Promotion> promo = promotionRepository.findSupplierActivePromotionForManagerByYear(supplier.get(),promoYear,PromotionStatus.SUBMITED);

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
	public Boolean changePromotionStatus(StatusChangeDTO statusDTO) {
		Optional<Supplier> supplier = supplierRepository.findById(statusDTO.getSupplierid());
		
		if(supplier.isPresent()) {
			Optional<Promotion> promo = promotionRepository.findSupplierActivePromotionForManagerByYear(supplier.get(),statusDTO.getPromoYear(),PromotionStatus.ACTIVE);
			
			Promotion currentPromo = promo.get();

			 if(currentPromo.getStatus().equals(PromotionStatus.SUBMITED)) {
				 currentPromo.setStatus(PromotionStatus.valueOf(statusDTO.getStatusChangeTo()));
				 promotionRepository.save(currentPromo);
				 return true;
			 } else {
				 return false;
			 }
		} else {
			return false;	
		}
	}
}
