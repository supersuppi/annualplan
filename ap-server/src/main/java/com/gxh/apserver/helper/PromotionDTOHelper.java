package com.gxh.apserver.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.gxh.apserver.dto.*;
import com.gxh.apserver.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.exceptions.InvalidStatusException;
import com.gxh.apserver.repository.interfaces.*;
import javax.transaction.Transactional;


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
        Optional<List<PromotionLevelSKU>> promoskus = promotionLevelSKURepository.findAllByPromoID(promo.getId());

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
            productDTOs.add(new ProductDTO(product.getId(),product.getGXHID(),product.getMarketingShortName()));
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

                dto = new DualMailerDTO();
                dto.setId(row.getPcode()+dm.getCode());
                if(ratecardDms.isPresent()) {
                    dto.setValue(rcdmMap.get(rateCard.getId().toString() + dm.getId().toString()));
                } else {
                    dto.setValue(0);
                }
                if(promoskus.isPresent()) {
                   //TODO:Loop and set values
                } else {
                    dto.setPromosku(new ArrayList<PromoSKUDTO>());
                }
                dmList.add(dto);
            }
            row.setDualmailers(dmList);
            //Add dual mailer mapped with ratecard as a row
            rows.add(row);
        }
        //Set rate cards
        promoDTO.setRatecards(rows);

        return promoDTO;
    }

}
