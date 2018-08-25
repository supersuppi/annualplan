package com.gxh.apserver.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.gxh.apserver.entity.*;
import com.gxh.apserver.repository.interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.dto.AddOrRemoveProductRequestDTO;
import com.gxh.apserver.dto.ProductDTO;
import com.gxh.apserver.dto.PromoCommentDTO;
import com.gxh.apserver.dto.PromoDTO;
import com.gxh.apserver.dto.PromoSKUDTO;
import com.gxh.apserver.dto.StatusChangeDTO;
import com.gxh.apserver.exceptions.InvalidStatusException;
import com.gxh.apserver.exceptions.ResourceNotFoundException;
import com.gxh.apserver.helper.PromotionDTOHelper;
import com.gxh.apserver.service.interfaces.PromotionService;
import com.gxh.apserver.util.DateUtil;

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
    private PromotionRepository promotionRepository;
    @Autowired
    private ProductRepository productRepository;
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

    @Override
    public PromoDTO getSupplierPromo(Long supplierID,String promoYear) throws ResourceNotFoundException,InvalidStatusException,ParseException {
        logger.info(">>> getSupplierPromo");
        logger.info(">>> data -"+supplierID+"=="+promoYear);
        Optional<Supplier> supplier = supplierRepository.findById(supplierID);

        if(supplier.isPresent()) {
            Optional<Promotion> promo = promotionRepository.findSupplierPromotionByYear(supplier.get(),DateUtil.convertFromStringTODate(promoYear));

            if(promo.isPresent()) {
                logger.info("Promo is present");
                return promotionDTOHelper.getPromoDTO(supplier.get(),promo.get());

            } else {
                logger.info("Promo is not present");
                throw new ResourceNotFoundException("Promo is not present for supplier with ID:"+supplierID);
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
        Optional<Promotion> currentPromotion = promotionRepository.findSupplierPromotionByYear(supplier.get(),DateUtil.convertFromStringTODate(promoDTO.getPromoyear()));
        Optional<List<RateCard>> rateCards = rateCardRepository.findRateCardBYPromotionID(currentPromotion.get());
        List<DualMailer> dms = dualMailerRepository.findAll();
        Optional<List<PromotionLevelRateCard>> ratecardDms = promotionLevelRateCardRepository.findAllByPromoID(currentPromotion.get().getId());
        Optional<SupplierPromotionBudget> promoBudget = supplierPromotionBudgetRepository.findByPromoID(currentPromotion.get());

        int totalBudget = 0;
        Map<String,PromotionLevelRateCard> rcdmMap = new HashMap<>();
        if(ratecardDms.isPresent()) {
            logger.info("Updating Promo");
            for (PromotionLevelRateCard rcdm : ratecardDms.get()) {
                //(K,V) = (rateCardID+DuailmailerID, PromotionLevelRateCard object)
                rcdmMap.put(rcdm.getRateCard().toString()+rcdm.getDualMailer().toString(),rcdm);
            }

            //loop DC:Rate card
            for(int i=0;i<rateCards.get().size();i++) {
                RateCard rateCard = rateCards.get().get(i);
                 for(int j=0;j<rateCards.get().size();j++) {
                    DualMailer dm = dms.get(j);

                    PromotionLevelRateCard prc = null;
                    prc = rcdmMap.get(rateCard.getId().toString()+dm.getId().toString());

                    prc.setPromo(currentPromotion.get().getId());
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

        } else {
            logger.info("Saving new Promo");
            //loop DC:Rate card
            for(int i=0;i<rateCards.get().size();i++) {
                RateCard rateCard = rateCards.get().get(i);
                for(int j=0;j<rateCards.get().size();j++) {
                    DualMailer dm = dms.get(j);

                    PromotionLevelRateCard prc = null;
                    prc = new PromotionLevelRateCard();

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

        }

        return new Boolean(true);
    }

    @Override
    public boolean saveManagerComment(PromoCommentDTO promoCommentDTO) throws ParseException {
        logger.info(">>> saveManagerComment");
        Optional<Supplier> supplier = supplierRepository.findById(promoCommentDTO.getSupplierid());
        Optional<Manager> manager = managerRepository.findById(promoCommentDTO.getManagerid());

        Date promoDate = DateUtil.convertFromStringTODate(promoCommentDTO.getPromoYear());
        Optional<Promotion> currentPromotion = promotionRepository.findSupplierPromotionByYear(supplier.get(),promoDate);

        //save comment
        PromoComments newComment = new PromoComments();
        newComment.setPromotion(currentPromotion.get());
        newComment.setSupplier(supplier.get());
        newComment.setManager(manager.get());
        newComment.setComment(promoCommentDTO.getComment());

        promoCommentRepository.save(newComment);

        Promotion promo = currentPromotion.get();
        promo.setStatus(PromotionStatus.REJECTED);

        promotionRepository.save(promo);

        return true;
    }

    @Override
    public PromoDTO getSupplierPromoForManager(Long supplierID, String promoYear) throws ResourceNotFoundException,InvalidStatusException,ParseException {
        logger.info(">>> getSupplierPromoForManager");
        logger.info(">>> data -"+supplierID+"=="+promoYear);
        Optional<Supplier> supplier = supplierRepository.findById(supplierID);

        if(supplier.isPresent()) {
            Optional<Promotion> promo = promotionRepository.findSupplierPromotionByYear(supplier.get(),DateUtil.convertFromStringTODate(promoYear));

            if(promo.isPresent()) {
                logger.info("Promo is present");
                return promotionDTOHelper.getPromoDTO(supplier.get(),promo.get());

            } else {
                logger.info("Promo is not present");
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
            Date promoDate = DateUtil.convertFromStringTODate(statusDTO.getPromoYear());
			Optional<Promotion> promo = promotionRepository.findSupplierPromotionByYearAndStatus(supplier.get(),promoDate,PromotionStatus.valueOf(statusDTO.getCurrentStatus()));

			Promotion currentPromotion = promo.get();
            currentPromotion.setStatus(PromotionStatus.valueOf(statusDTO.getStatusChangeTo()));

            promotionRepository.save(currentPromotion);
            return true;
		} else {
			return false;	
		}
	}

	@Transactional
	@Override
	public void saveOrRemoveSelectedProducts(AddOrRemoveProductRequestDTO requestBody) throws ParseException {
		
		logger.info(" Inside saveOrRemoveSelectedProducts ");
		
		PromotionLevelSKU levelSKU;
		
		for ( int i = 0; i < requestBody.getProductsSelected().size(); i++ ) {
			levelSKU = new PromotionLevelSKU();
			
			levelSKU.setDualMailer(requestBody.getDmId());
			levelSKU.setProduct(requestBody.getProductsSelected().get(i).getId());
			levelSKU.setPromo(requestBody.getPromoId());
			levelSKU.setRateCard(requestBody.getRcId());
			levelSKU.setPromoCount(requestBody.getPromoCount());
			levelSKU.setPromoName(requestBody.getPromoName());
			levelSKU.setPromoType(requestBody.getPromoType());
			
			promotionLevelSKURepository.save(levelSKU);
		}
		
		for ( int i = 0; i < requestBody.getProductsDeselected().size(); i++ ) {

			promotionLevelSKURepository.deleteByRowData(requestBody.getDmId(), requestBody.getRcId(),
					requestBody.getPromoId(), requestBody.getProductsDeselected().get(i).getId(),
					requestBody.getPromoCount());
			
		}
		
	}

	@Override
	public PromoSKUDTO getSavedProductsForPromoCount(Long promoId, Long dmId, Long rowId, int promoCount)
			throws ParseException {
		
		PromoSKUDTO promoSKUDTO = new PromoSKUDTO();
		List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
		Optional<List<Product>> prodOptionalList = productRepository.findAllSelectedProducts(promoId, dmId, rowId, promoCount);
		Optional<List<PromotionLevelSKU>> optionalPromo = promotionLevelSKURepository.findByRowData(dmId, rowId,
				promoId, promoCount);
		
		if (optionalPromo.isPresent()) {
			List<PromotionLevelSKU> products = optionalPromo.get().stream().limit(1).collect(Collectors.toList());
			products.forEach( promoLevelSku -> {
				promoSKUDTO.setPromoName(promoLevelSku.getPromoName());
				promoSKUDTO.setPromoType(promoLevelSku.getPromoType());
			});
		}
		
		if( prodOptionalList.isPresent() ) {
			prodOptionalList.get().forEach( product -> {
				ProductDTO productDTO = new ProductDTO();
				
				productDTO.setId(product.getId());
				productDTO.setName(product.getMarketingShortName());
				productDTO.setSku(product.getGXHID());
				
				productDTOList.add(productDTO);
			}); 
//			return productDTOList;
		}
		
		promoSKUDTO.setProducts_selected(productDTOList);
		
		return promoSKUDTO;
		
	}
}
