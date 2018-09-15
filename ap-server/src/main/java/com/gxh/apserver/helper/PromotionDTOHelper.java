package com.gxh.apserver.helper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.gxh.apserver.repository.interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gxh.apserver.constants.AnnualPromotionStatus;
import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.dto.BrandProductDTO;
import com.gxh.apserver.dto.BudgetDTO;
import com.gxh.apserver.dto.DualMailerDTO;
import com.gxh.apserver.dto.ProductDTO;
import com.gxh.apserver.dto.PromoDTO;
import com.gxh.apserver.dto.PromoSKUDTO;
import com.gxh.apserver.dto.RateCardDTO;
import com.gxh.apserver.entity.AnnualPromotion;
import com.gxh.apserver.entity.DualMailer;
import com.gxh.apserver.entity.Product;
import com.gxh.apserver.entity.Promotion;
import com.gxh.apserver.entity.PromotionLevelRateCard;
import com.gxh.apserver.entity.PromotionLevelSKU;
import com.gxh.apserver.entity.RateCard;
import com.gxh.apserver.entity.Supplier;
import com.gxh.apserver.entity.SupplierPromotionBudget;
import com.gxh.apserver.exceptions.InvalidStatusException;
import com.gxh.apserver.util.BudgetCalculator;


@Component
public class PromotionDTOHelper {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RateCardRepository rateCardRepository;
    @Autowired
    private DualMailerRepository dualMailerRepository;
    @Autowired
    private PromotionLevelRateCardRepository promotionLevelRateCardRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AnnualPromotionRepository annualPromotionRepository;
    @Autowired
    private PromotionLevelSKURepository promotionLevelSKURepository;
    @Autowired
    private SupplierPromotionBudgetRepository supplierPromotionBudgetRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private SupplierRepository supplierRepository;

    public PromoDTO getPromoDTO(Supplier supplier, Promotion promo) throws InvalidStatusException {
        logger.info(">>> buildPromoDTO");
        PromoDTO promoDTO = new PromoDTO();
        //Details about this promotion
        promoDTO.setPromoyear(promo.getCreatedAt().toString());
        promoDTO.setUserid(supplier.getId());
        promoDTO.setPromo_id(promo.getId());
        
        Optional<AnnualPromotion> annualPromo = annualPromotionRepository.findPromotionBySupplierAndPromo(supplier,promo);
        
        if(annualPromo.isPresent()) {
        	switch (annualPromo.get().getStatus()) {
		    	case DRAFT:
		            promoDTO.setStatus(AnnualPromotionStatus.DRAFT);
		            promoDTO.setIsEditable(true);
		            return this.createDTO(promoDTO,supplier,promo,annualPromo.get());
		            
		    	case SUBMITTED:
	                promoDTO.setStatus(AnnualPromotionStatus.SUBMITTED);
	                promoDTO.setIsEditable(false);
	                return this.createDTO(promoDTO,supplier,promo,annualPromo.get());
	                
		    	case REJECTED:
	                promoDTO.setStatus(AnnualPromotionStatus.REJECTED);
	                promoDTO.setIsEditable(true);
	                return this.createDTO(promoDTO,supplier,promo,annualPromo.get());

                case ACCEPTED:
                    promoDTO.setStatus(AnnualPromotionStatus.ACCEPTED);
                    promoDTO.setIsEditable(false);
                    return this.createDTO(promoDTO,supplier,promo,annualPromo.get());

		    	default:
	                throw new InvalidStatusException("Invalid Status of the promotion");
	                	
        	}
        } else {
        	//new annual promotion set as draft
        	promoDTO.setStatus(AnnualPromotionStatus.DRAFT);
            promoDTO.setIsEditable(true);
            return this.createDTO(promoDTO,supplier,promo,new AnnualPromotion());
        }

    }

    @Transactional
    private PromoDTO createDTO(PromoDTO promoDTO,Supplier supplier,Promotion promo,AnnualPromotion annualPromo) {
        logger.info(">>> createDTO");
        
        Optional<List<RateCard>> rateCards = rateCardRepository.findAllRateCardBYPromotionID(promo);
        List<DualMailer> dms = dualMailerRepository.findAllDMbyPromotion(promo);
        Optional<List<Product>> products = productRepository.findproductsBySupplierAXCode(supplier.getVendorAXCode());
        Optional<List<PromotionLevelRateCard>> ratecardDms = promotionLevelRateCardRepository.findAllByPromoAndAnnualPromoID(promo.getId(),annualPromo.getId());
        Optional<SupplierPromotionBudget> promoBudget = supplierPromotionBudgetRepository.findByPromotion(promo);

        List<RateCardDTO> rows = new ArrayList<RateCardDTO>();
        Map<String,Integer> rcdmMap = new HashMap<>();

        if(ratecardDms.isPresent()) {
            logger.info("Promotion is present - Getting Level 1 promotion");
            ratecardDms.get().forEach(promotionLevelRateCard ->
                    rcdmMap.put(promotionLevelRateCard.getRateCard().toString()+promotionLevelRateCard.getDualMailer().toString(),promotionLevelRateCard.getValue()));
        }
        
        Map<String, List<ProductDTO>> mapProduct = new HashMap<>();
        List<BrandProductDTO> brandProductDTOS = new ArrayList<>();

        for (Product product : products.get()) {
            List<ProductDTO> productDTOs = mapProduct.get(product.getBrand().getName());
            if(!mapProduct.containsKey(product.getBrand().getName())) {
                productDTOs = new ArrayList<ProductDTO>();
                mapProduct.put(product.getBrand().getName(), productDTOs);
            }
            productDTOs.add(new ProductDTO(product.getId(),product.getGXHID(),product.getMarketingShortName(), product.getBarcode().getBarcode1()));
        }

        //Create BrandproductDTO and set it to promo dto as products
        mapProduct.forEach((brandName,productList)-> brandProductDTOS.add(new BrandProductDTO(brandName,productList)));
        promoDTO.setProducts(brandProductDTOS);

        for (RateCard rateCard : rateCards.get()) {
            RateCardDTO row = new RateCardDTO();
            row.setPname(rateCard.getName());
            row.setPcode(rateCard.getCode());
            row.setPrate(rateCard.getRateCardDollar());
            //TODO: promo mechanis to be added later
//            row.setMax_product(rateCard.getPromoMechanic().getMaxProductAllocation());
//            row.setMax_tile(rateCard.getPromoMechanic().getMaxTileAllocation());
//            row.setMin_tile(rateCard.getPromoMechanic().getMinTileAllocation());
//            row.setNash_rc(rateCard.getPromoMechanic().getTimesOfNash());

            List<DualMailerDTO> dmList = new ArrayList<>();
            for (DualMailer dm : dms) {
                DualMailerDTO dto = null;
                
                dto = new DualMailerDTO();
                dto.setId(dm.getCode());
                if(ratecardDms.isPresent()) {
                    dto.setValue(rcdmMap.get(rateCard.getId().toString() + dm.getId().toString()));
                } else {
                    dto.setValue(0);
                }
                dmList.add(dto);
            }
            row.setDualmailers(dmList);
            //Add dual mailer mapped with ratecard as a row
            rows.add(row);
        }
        //Set rate cards
        promoDTO.setRatecards(rows);
        //Set Budget
        if (promoBudget.isPresent()) {
        	promoDTO.setBudget(BudgetCalculator.getBudget(promoBudget.get()));
        } else {
        	BudgetDTO budgetDTO = new BudgetDTO();

            budgetDTO.setAllocated(0L);
            budgetDTO.setUsed(0L);
            budgetDTO.setDiff(0L);
            
        	promoDTO.setBudget(budgetDTO);
        }
        
        logger.info("<<< createDTO");
        return promoDTO;
    }
    
    public PromoSKUDTO getPromoSKUDTO(Long promoId,Long supplierId, Long dmId, Long rowId, int promoCount)
			throws ParseException {
    	logger.info(">>> getPromoSKUDTO");
        logger.info("Data:"+promoId);
        Optional<Supplier> supplier = supplierRepository.findById(supplierId);
        Optional<Promotion> promotion = promotionRepository.findById(promoId);
        Optional<AnnualPromotion> annualPromotion = annualPromotionRepository.findPromotionBySupplierAndPromo(supplier.get(),promotion.get());

        PromoSKUDTO promoSKUDTO = new PromoSKUDTO();
		List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
		Optional<List<Product>> prodOptionalList = productRepository.findAllSelectedProducts(annualPromotion.get().getId(), dmId, rowId, promoCount);
		Optional<List<PromotionLevelSKU>> optionalPromo = promotionLevelSKURepository.findByRowData(dmId, rowId,
                annualPromotion.get().getId(), promoCount);
		
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
				productDTO.setBarcode(product.getBarcode().getBarcode1());
				
				productDTOList.add(productDTO);
			}); 
		}
		promoSKUDTO.setProducts_selected(productDTOList);
		logger.info("<<< getPromoSKUDTO");
		
		return promoSKUDTO;
    }

}
