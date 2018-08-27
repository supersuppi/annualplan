package com.gxh.apserver.helper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.dto.BrandProductDTO;
import com.gxh.apserver.dto.DualMailerDTO;
import com.gxh.apserver.dto.ProductDTO;
import com.gxh.apserver.dto.PromoDTO;
import com.gxh.apserver.dto.PromoSKUDTO;
import com.gxh.apserver.dto.RateCardDTO;
import com.gxh.apserver.entity.DualMailer;
import com.gxh.apserver.entity.Product;
import com.gxh.apserver.entity.Promotion;
import com.gxh.apserver.entity.PromotionLevelRateCard;
import com.gxh.apserver.entity.PromotionLevelSKU;
import com.gxh.apserver.entity.RateCard;
import com.gxh.apserver.entity.Supplier;
import com.gxh.apserver.entity.SupplierPromotionBudget;
import com.gxh.apserver.exceptions.InvalidStatusException;
import com.gxh.apserver.repository.interfaces.DualMailerRepository;
import com.gxh.apserver.repository.interfaces.ProductRepository;
import com.gxh.apserver.repository.interfaces.PromotionLevelRateCardRepository;
import com.gxh.apserver.repository.interfaces.PromotionLevelSKURepository;
import com.gxh.apserver.repository.interfaces.PromotionRepository;
import com.gxh.apserver.repository.interfaces.RateCardRepository;
import com.gxh.apserver.repository.interfaces.SupplierPromotionBudgetRepository;
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
    private PromotionRepository promotionRepository;
    @Autowired
    private PromotionLevelSKURepository promotionLevelSKURepository;
    @Autowired
    private SupplierPromotionBudgetRepository supplierPromotionBudgetRepository;

    public PromoDTO getPromoDTO(Supplier supplier, Promotion promo) throws InvalidStatusException {
        logger.info(">>> buildPromoDTO");
        PromoDTO promoDTO = new PromoDTO();
        //Details about this promotion
        promoDTO.setPromoyear(promo.getYear().toString());
        promoDTO.setUserid(supplier.getId());
        promoDTO.setPromo_id(promo.getId());

        switch (promo.getStatus()) {
            case ACTIVE:
                promoDTO.setStatus(PromotionStatus.ACTIVE);
                promoDTO.setIsEditable(true);
                return this.createDTO(promoDTO,supplier,promo);

            case SUBMITTED:
                promoDTO.setStatus(PromotionStatus.SUBMITTED);
                promoDTO.setIsEditable(false);
                return this.createDTO(promoDTO,supplier,promo);

            case REJECTED:
                promoDTO.setStatus(PromotionStatus.REJECTED);
                promoDTO.setIsEditable(true);
                return this.createDTO(promoDTO,supplier,promo);

            case ACCEPTED:
                promoDTO.setStatus(PromotionStatus.ACCEPTED);
                promoDTO.setIsEditable(false);
                return this.createDTO(promoDTO,supplier,promo);

            default:
                throw new InvalidStatusException("Invalid Status of the user");
        }
    }

    @Transactional
    private PromoDTO createDTO(PromoDTO promoDTO,Supplier supplier,Promotion promo) {
        logger.info(">>> createDTO");
        Optional<List<RateCard>> rateCards = rateCardRepository.findRateCardBYPromotionID(promo);
        List<DualMailer> dms = dualMailerRepository.findAll();
        Optional<List<Product>> products = productRepository.findproductsBySupplierAXCode(supplier.getVendorAXCode());
        Optional<List<PromotionLevelRateCard>> ratecardDms = promotionLevelRateCardRepository.findAllByPromoID(promo.getId());
        Optional<SupplierPromotionBudget> promoBudget = supplierPromotionBudgetRepository.findByPromoID(promo);
        Optional<List<Integer>> promoLevelSKUPromoCounts = promotionLevelSKURepository.findAllPromoCountByPromoID(promo.getId());

        List<RateCardDTO> rows = new ArrayList<RateCardDTO>();
        Map<String,Integer> rcdmMap = new HashMap<>();

        if(ratecardDms.isPresent()) {
            logger.info("Promo is present - Getting Level 1 promotion");
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
            row.setMax_product(rateCard.getPromoMechanic().getMaxProductAllocation());
            row.setMax_tile(rateCard.getPromoMechanic().getMaxTileAllocation());
            row.setMin_tile(rateCard.getPromoMechanic().getMinTileAllocation());
            row.setNash_rc(rateCard.getPromoMechanic().getTimesOfNash());

            List<DualMailerDTO> dmList = new ArrayList<>();
            for (DualMailer dm : dms) {
                DualMailerDTO dto = null;
                List<PromoSKUDTO> selectedProductList = new ArrayList<>();
                
                dto = new DualMailerDTO();
                dto.setId(row.getPcode()+dm.getCode());
                if(ratecardDms.isPresent()) {
                    dto.setValue(rcdmMap.get(rateCard.getId().toString() + dm.getId().toString()));
                } else {
                    dto.setValue(0);
                }
                //Add selected products for promo
                if(promoLevelSKUPromoCounts.isPresent()) {
               	 for (Integer promoCount : promoLevelSKUPromoCounts.get()) {
               		PromoSKUDTO pskuDTO = null;
					try {
						pskuDTO = getPromoSKUDTO(promo.getId(),dm.getId(),rateCard.getId(),promoCount);
					} catch (ParseException e) {
						logger.error(e.getMessage());
					}
               		selectedProductList.add(pskuDTO);
               	 }
                }
                dto.setPromosku(selectedProductList);
                
                dmList.add(dto);
            }
            row.setDualmailers(dmList);
            //Add dual mailer mapped with ratecard as a row
            rows.add(row);
        }
        //Set rate cards
        promoDTO.setRatecards(rows);
        //Set Budget
        promoDTO.setBudget(BudgetCalculator.getBudget(promoBudget.get()));
        logger.info("<<< createDTO");
        return promoDTO;
    }
    
    public PromoSKUDTO getPromoSKUDTO(Long promoId, Long dmId, Long rowId, int promoCount)
			throws ParseException {
    	logger.info(">>> getPromoSKUDTO");
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
				productDTO.setBarcode(product.getBarcode().getBarcode1());
				
				productDTOList.add(productDTO);
			}); 
		}
		promoSKUDTO.setProducts_selected(productDTOList);
		logger.info("<<< getPromoSKUDTO");
		
		return promoSKUDTO;
    }

}
