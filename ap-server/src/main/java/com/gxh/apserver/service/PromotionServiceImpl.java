package com.gxh.apserver.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxh.apserver.constants.AnnualPromotionStatus;
import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.dto.AddOrRemoveProductRequestDTO;
import com.gxh.apserver.dto.AdminPromoDTO;
import com.gxh.apserver.dto.PromoCommentDTO;
import com.gxh.apserver.dto.PromoDTO;
import com.gxh.apserver.dto.PromoSKUDTO;
import com.gxh.apserver.dto.StatusChangeDTO;
import com.gxh.apserver.entity.AnnualPromotion;
import com.gxh.apserver.entity.DualMailer;
import com.gxh.apserver.entity.Manager;
import com.gxh.apserver.entity.PromoComments;
import com.gxh.apserver.entity.Promotion;
import com.gxh.apserver.entity.PromotionLevelRateCard;
import com.gxh.apserver.entity.PromotionLevelSKU;
import com.gxh.apserver.entity.RateCard;
import com.gxh.apserver.entity.Supplier;
import com.gxh.apserver.entity.SupplierPromotionBudget;
import com.gxh.apserver.exceptions.InvalidStatusException;
import com.gxh.apserver.exceptions.ResourceNotFoundException;
import com.gxh.apserver.helper.PromotionDTOHelper;
import com.gxh.apserver.repository.interfaces.AnnualPromotionRepository;
import com.gxh.apserver.repository.interfaces.DualMailerRepository;
import com.gxh.apserver.repository.interfaces.ManagerRepository;
import com.gxh.apserver.repository.interfaces.PromoCommentRepository;
import com.gxh.apserver.repository.interfaces.PromotionLevelRateCardRepository;
import com.gxh.apserver.repository.interfaces.PromotionLevelSKURepository;
import com.gxh.apserver.repository.interfaces.PromotionRepository;
import com.gxh.apserver.repository.interfaces.RateCardRepository;
import com.gxh.apserver.repository.interfaces.SupplierPromotionBudgetRepository;
import com.gxh.apserver.repository.interfaces.SupplierRepository;
import com.gxh.apserver.service.interfaces.BudgetService;
import com.gxh.apserver.service.interfaces.PromotionService;

@Service(value = "promotionService")
public class PromotionServiceImpl implements PromotionService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RateCardRepository rateCardRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private DualMailerRepository dualMailerRepository;
    @Autowired
    private AnnualPromotionRepository annualPromotionRepository;
    @Autowired
    private PromotionLevelRateCardRepository promotionLevelRateCardRepository;
    @Autowired
    private PromotionDTOHelper promotionDTOHelper;
    @Autowired
    private PromoCommentRepository promoCommentRepository;
    @Autowired
    private PromotionLevelSKURepository promotionLevelSKURepository;
    @Autowired
    private SupplierPromotionBudgetRepository supplierPromotionBudgetRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private BudgetService budgetService;

    @Override
    public PromoDTO getSupplierPromo(Long supplierID,Long promoID) throws ResourceNotFoundException,InvalidStatusException,ParseException {
        logger.info(">>> getSupplierPromo");
        logger.debug(">>> data -"+supplierID+"=="+promoID);
        Optional<Supplier> supplier = supplierRepository.findById(supplierID);

        if(supplier.isPresent()) {
            Optional<Promotion> promo = promotionRepository.findById(promoID);

            if(promo.isPresent()) {
                logger.info("Promotion is present");
                return promotionDTOHelper.getPromoDTO(supplier.get(),promo.get());

            } else {
                logger.info("Promotion is not present");
                throw new ResourceNotFoundException("Promotion is not present for supplier with ID:"+supplierID);
            }
        } else {
            throw new ResourceNotFoundException("Supplier not found with ID:"+supplierID);
        }
    }

    @Override
    public List<PromoDTO> getAllSupplierPromo(Long supplierID) throws ResourceNotFoundException, InvalidStatusException {
        return null;
    }

    @Override
    @Transactional
    public boolean saveSupplierPromo(final PromoDTO promoDTO) throws ParseException {
        logger.info(">>> saveSupplierPromo");

        Optional<Supplier> supplier = supplierRepository.findById(promoDTO.getUserid());
        Optional<Promotion> currentPromotion = promotionRepository.findById(promoDTO.getPromo_id());
        Optional<AnnualPromotion> currentAnnualPromotion = annualPromotionRepository.findPromotionBySupplierAndPromo(supplier.get(), currentPromotion.get());
        Optional<List<RateCard>> rateCards = rateCardRepository.findAllRateCardBYPromotionID(currentPromotion.get());
        List<DualMailer> dualMailers = dualMailerRepository.findAllDMbyPromotion(currentPromotion.get());
        Optional<List<PromotionLevelRateCard>> ratecardDms = promotionLevelRateCardRepository.findAllByPromoID(currentPromotion.get().getId());
        Optional<SupplierPromotionBudget> promoBudget = supplierPromotionBudgetRepository.findByPromotion(currentPromotion.get());
//        Optional<SupplierPromotionBudget> promoBudget = supplierPromotionBudgetRepository.findBySupplierID(supplier.get());

        int totalBudget = 0;
        Map<String,PromotionLevelRateCard> rcdmMap = new HashMap<>();
        if(currentAnnualPromotion.isPresent() && ratecardDms.isPresent()) {
            logger.info("Updating Current Annual Promotion");
            for (PromotionLevelRateCard rcdm : ratecardDms.get()) {
                //(K,V) = (rateCardID+DuailmailerID, PromotionLevelRateCard object)
                rcdmMap.put(rcdm.getRateCard().toString()+rcdm.getDualMailer().toString(),rcdm);
            }
            //loop DC:Rate card
            for(int i=0;i<rateCards.get().size();i++) {
                RateCard rateCard = rateCards.get().get(i);
                 for(int j=0;j<dualMailers.size();j++) {
                    DualMailer dm = dualMailers.get(j);

                    PromotionLevelRateCard prc = null;
                    prc = rcdmMap.get(rateCard.getId().toString()+dm.getId().toString());

                    prc.setPromo(currentPromotion.get().getId());
                    prc.setAnnualpromo(currentAnnualPromotion.get().getId());
                    prc.setRateCard(rateCard.getId());
                    prc.setDualMailer(dm.getId());
                    prc.setValue(promoDTO.getRatecards().get(i).getDualmailers().get(j).getValue());
                     //Calculate budget ( Rateplan cost x number of tiles
                    totalBudget += rateCard.getRateCardDollar() * promoDTO.getRatecards().get(i).getDualmailers().get(j).getValue();
                    
                    promoDTO.getRatecards().get(i).getDualmailers().get(j).getPromosku();
                    
                    promotionLevelRateCardRepository.save(prc);
                    
                }
            }
            logger.info("total_budget:"+totalBudget);

            //Update Budget
            SupplierPromotionBudget CurrentPromoBudget = promoBudget.get();
            CurrentPromoBudget.setTotalBudget(Long.valueOf(totalBudget));
            supplierPromotionBudgetRepository.save(CurrentPromoBudget);
            logger.info("Updating Current Annual Promotion Done");

        } else {
            logger.info("Saving New Annual Promotion");
            //Create annual promotion
            AnnualPromotion ap = new AnnualPromotion();
            ap.setCreatedByUser(supplier.get().getSupplierAppUser());
            //TODO: modifiedby to br read from dto
            ap.setModifiedByUser(supplier.get().getSupplierAppUser());
            ap.setPromo(currentPromotion.get());
            ap.setSupplier(supplier.get());
            ap.setStatus(AnnualPromotionStatus.DRAFT);
            
            AnnualPromotion savedAP = annualPromotionRepository.saveAndFlush(ap);
            
            //loop DC:Rate card
            for(int i=0;i<rateCards.get().size();i++) {
                RateCard rateCard = rateCards.get().get(i);
                for(int j=0;j<dualMailers.size();j++) {
                    DualMailer dm = dualMailers.get(j);

                    PromotionLevelRateCard prc = null;
                    
                    prc = new PromotionLevelRateCard();
                    prc.setAnnualpromo(savedAP.getId());
                    prc.setPromo(currentPromotion.get().getId());
                    prc.setRateCard(rateCard.getId());
                    prc.setDualMailer(dm.getId());
                    prc.setValue(promoDTO.getRatecards().get(i).getDualmailers().get(j).getValue());
                    
                    //Calculate budget ( Rateplan cost x number of tiles
                    totalBudget += rateCard.getRateCardDollar() * promoDTO.getRatecards().get(i).getDualmailers().get(j).getValue();

                    promotionLevelRateCardRepository.save(prc);
                }
            }
            //Update Budget
            SupplierPromotionBudget CurrentPromoBudget = promoBudget.get();
            CurrentPromoBudget.setTotalBudget(Long.valueOf(totalBudget));
            supplierPromotionBudgetRepository.save(CurrentPromoBudget);
            logger.info("Saving New Annual Promotion Done");
        }

        return new Boolean(true);
    }

    @Override
    public boolean saveManagerComment(PromoCommentDTO promoCommentDTO) throws ParseException {
        logger.info(">>> saveManagerComment");
        Optional<Supplier> supplier = supplierRepository.findById(promoCommentDTO.getSupplierid());
        Optional<Manager> manager = managerRepository.findById(promoCommentDTO.getManagerid());

        Optional<Promotion> currentPromotion = promotionRepository.findById(promoCommentDTO.getPromoID());
        Optional<AnnualPromotion> currentAnnualPromotion = annualPromotionRepository.findPromotionBySupplierAndPromo(supplier.get(), currentPromotion.get());

        //save comment
        PromoComments newComment = new PromoComments();
        newComment.setAnnualpromotion(currentAnnualPromotion.get());
        newComment.setSupplier(supplier.get());
        newComment.setManager(manager.get());
        newComment.setComment(promoCommentDTO.getComment());

        promoCommentRepository.save(newComment);

        AnnualPromotion promo = currentAnnualPromotion.get();
        promo.setStatus(AnnualPromotionStatus.REJECTED);

        annualPromotionRepository.save(promo);

        return true;
    }

    @Override
    public PromoDTO getSupplierPromoForManager(Long supplierID, Long promoID) throws ResourceNotFoundException,InvalidStatusException,ParseException {
        logger.info(">>> getSupplierPromoForManager");
        logger.info(">>> data -"+supplierID+"=="+promoID);
        Optional<Supplier> supplier = supplierRepository.findById(supplierID);

        if(supplier.isPresent()) {
        	Optional<Promotion> currentPromotion = promotionRepository.findById(promoID);
            Optional<AnnualPromotion> currentAnnualPromotion = annualPromotionRepository.findPromotionBySupplierAndPromo(supplier.get(),currentPromotion.get());

            if(currentAnnualPromotion.isPresent()) {
                logger.info("Promotion is present");
                return promotionDTOHelper.getPromoDTO(supplier.get(),currentPromotion.get());

            } else {
                logger.info("Promotion is not present");
                throw new ResourceNotFoundException("Suppiler Does not have any active promo");
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
            Optional<Promotion> promotion = promotionRepository.findById(statusDTO.getPromoid());
            Optional<AnnualPromotion> currentAnnualPromotion = annualPromotionRepository.findPromotionBySupplierAndPromo(supplier.get(), promotion.get());

            AnnualPromotion currentAP = currentAnnualPromotion.get();
            currentAP.setStatus(AnnualPromotionStatus.valueOf(statusDTO.getStatusChangeTo()));

            annualPromotionRepository.save(currentAP);
            return true;
		} else {
			return false;	
		}
	}

	/**
	 * Save or delete products for a promo id and supplier id.
	 */
	@Transactional
	@Override
	public void saveOrRemoveSelectedProducts(AddOrRemoveProductRequestDTO requestBody) throws ParseException {
		logger.info(" Inside saveOrRemoveSelectedProducts");
		PromotionLevelSKU levelSKU;
        Optional<Supplier> supplier = supplierRepository.findById(requestBody.getSupplierId());
        Optional<Promotion> promotion = promotionRepository.findById(requestBody.getPromoId());
        Optional<AnnualPromotion> annualPromotion = annualPromotionRepository.findPromotionBySupplierAndPromo(supplier.get(),promotion.get());

        if(annualPromotion.isPresent()) {
            logger.info(" AnnualPromotion present");
            for ( int i = 0; i < requestBody.getProductsSelected().size(); i++ ) {
                levelSKU = new PromotionLevelSKU();
                levelSKU.setDualMailer(requestBody.getDmId());
                levelSKU.setProduct(requestBody.getProductsSelected().get(i).getId());
                levelSKU.setPromo(annualPromotion.get().getId());
                levelSKU.setRateCard(requestBody.getRcId());
                levelSKU.setPromoCount(requestBody.getPromoCount());
                levelSKU.setPromoName(requestBody.getPromoName());
                levelSKU.setPromoType(requestBody.getPromoType());

                promotionLevelSKURepository.save(levelSKU);
            }
            for ( int i = 0; i < requestBody.getProductsDeselected().size(); i++ ) {
                promotionLevelSKURepository.deleteByRowData(requestBody.getDmId(), requestBody.getRcId(),
                        annualPromotion.get().getId(), requestBody.getProductsDeselected().get(i).getId(),
                        requestBody.getPromoCount());
            }
        } else {
            logger.info(" AnnualPromotion not present.adding");
            //Create annual promotion
            AnnualPromotion ap = new AnnualPromotion();
            ap.setCreatedByUser(supplier.get().getSupplierAppUser());
            //TODO: modifiedby to br read from dto
            ap.setModifiedByUser(supplier.get().getSupplierAppUser());
            ap.setPromo(promotion.get());
            ap.setSupplier(supplier.get());
            ap.setStatus(AnnualPromotionStatus.DRAFT);

            AnnualPromotion savedAP = annualPromotionRepository.saveAndFlush(ap);

            for ( int i = 0; i < requestBody.getProductsSelected().size(); i++ ) {
                levelSKU = new PromotionLevelSKU();
                levelSKU.setDualMailer(requestBody.getDmId());
                levelSKU.setProduct(requestBody.getProductsSelected().get(i).getId());
                levelSKU.setPromo(savedAP.getId());
                levelSKU.setRateCard(requestBody.getRcId());
                levelSKU.setPromoCount(requestBody.getPromoCount());
                levelSKU.setPromoName(requestBody.getPromoName());
                levelSKU.setPromoType(requestBody.getPromoType());

                promotionLevelSKURepository.save(levelSKU);
            }
            for ( int i = 0; i < requestBody.getProductsDeselected().size(); i++ ) {
                promotionLevelSKURepository.deleteByRowData(requestBody.getDmId(), requestBody.getRcId(),
                        savedAP.getId(), requestBody.getProductsDeselected().get(i).getId(),
                        requestBody.getPromoCount());
            }

        }
	}

	/**
	 * Fetch the saved products for the given promo id.
	 */
	@Override
	public PromoSKUDTO getSavedProductsForPromoCount(Long promoId,Long supplierId, Long dmId, Long rowId, int promoCount)
			throws ParseException {	
		return promotionDTOHelper.getPromoSKUDTO(promoId,supplierId, dmId, rowId, promoCount);
	}

	/**
	 * Get the promotions in active status , and the annual promotions are in 'rejected' 
	 *  or 'draft' to allow the supplier to allocate a budget.
	 */
	@Override
	public List<AdminPromoDTO> getAllActivePromotionsForSupplier(Long supplierId) throws ResourceNotFoundException,
		ParseException{
        logger.info("getAllActivePromotionsForSupplier");
		return budgetService.getPromosbyStatus(PromotionStatus.ACTIVE);
	}
}
