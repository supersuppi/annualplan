package com.gxh.apserver.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.dto.BrandProductDTO;
import com.gxh.apserver.dto.DualMailerDTO;
import com.gxh.apserver.dto.ProductDTO;
import com.gxh.apserver.dto.PromoDTO;
import com.gxh.apserver.dto.RateCardDTO;
import com.gxh.apserver.entity.DualMailer;
import com.gxh.apserver.entity.Product;
import com.gxh.apserver.entity.Promotion;
import com.gxh.apserver.entity.PromotionLevelRateCard;
import com.gxh.apserver.entity.RateCard;
import com.gxh.apserver.entity.Supplier;
import com.gxh.apserver.exceptions.InvalidStatusException;
import com.gxh.apserver.repository.interfaces.DualMailerRepository;
import com.gxh.apserver.repository.interfaces.ProductRepository;
import com.gxh.apserver.repository.interfaces.PromotionLevelRateCardRepository;
import com.gxh.apserver.repository.interfaces.RateCardRepository;
import com.gxh.apserver.util.DateUtil;

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


    public PromoDTO buildExistingPromoDTO(Supplier supplier, Promotion promo) throws InvalidStatusException {
        System.out.println("build from saved promo");
        PromoDTO promoDTO = new PromoDTO();
        //Details about this promotion
        promoDTO.setPromoyear(promo.getYear().toString());
        promoDTO.setVersion(1);
        promoDTO.setUserid(supplier.getId());

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

    private PromoDTO createDTO(PromoDTO promoDTO,Supplier supplier,Promotion promo) {
        List<RateCard> rateCards = rateCardRepository.findAll();
        List<DualMailer> dms = dualMailerRepository.findAll();
        Optional<List<Product>> products = productRepository.findproductsBySupplierAXCode(supplier.getVendorAXCode());
        Optional<List<PromotionLevelRateCard>> ratecardDms = promotionLevelRateCardRepository.findAllByPromoID(promo.getId());

        List<RateCardDTO> rows = new ArrayList<RateCardDTO>();
        Map<String,Integer> rcdmMap = new HashMap<>();
        Map<String, List<ProductDTO>> mapProduct = new HashMap<>(); 

        for (PromotionLevelRateCard rcdm : ratecardDms.get()) {
            //(K,V) = (rateCardID+DuailmailerID, value)
            rcdmMap.put(rcdm.getRateCard().toString()+rcdm.getDualMailer().toString(),rcdm.getValue());
        }

        // Returns a map of Brand and list of skus within that brand.
        for (Product product : products.get()) {
        	List<ProductDTO> tempList = mapProduct.get(product.getBrand().getName());
        	if(!mapProduct.containsKey(product.getBrand().getName())) {
        		tempList = new ArrayList<>();
        		mapProduct.put(product.getBrand().getName(), tempList);
        	}
        	tempList.add(new ProductDTO(product.getGXHID(),product.getMarketingShortName(), product.getId()));
        }

        for (RateCard rateCard : rateCards) {
            RateCardDTO row = new RateCardDTO();
            row.setPname(rateCard.getName());
            row.setPcode(rateCard.getCode());
            row.setPrate(rateCard.getRateCardDollar());

            List<DualMailerDTO> dmList = new ArrayList<>();

            for (DualMailer dm : dms) {
                DualMailerDTO dto = null;
                BrandProductDTO bpDto = null;

                dto = new DualMailerDTO();
                bpDto = new BrandProductDTO();
                bpDto.setPromosku(new ArrayList<ProductDTO>());

                dto.setId(row.getPcode()+dm.getCode());
                dto.setValue(rcdmMap.get(rateCard.getId().toString()+dm.getId().toString()));
                dto.setPromosku(new ArrayList<ProductDTO>());
                dto.setPromoBrandSku(bpDto);

                dmList.add(dto);
            }
            row.setDualmailers(dmList);
            //Add dual mailer mapped with ratecard as a row
            rows.add(row);
        }
        //Set products
        promoDTO.setMapOfProducts(mapProduct);
        //Set rate cards
        promoDTO.setRatecards(rows);

        return promoDTO;
    }

    public PromoDTO buildNewPromoDTO(Supplier supplier) {
        System.out.println("building new promo");
        PromoDTO promoDTO = new PromoDTO();
        //Details about this promotion
        promoDTO.setIsEditable(true);
        promoDTO.setPromoyear(DateUtil.getNowDateInString());
        promoDTO.setVersion(1);
        promoDTO.setUserid(supplier.getId());
        promoDTO.setStatus(PromotionStatus.ACTIVE);

        List<RateCard> rateCards = rateCardRepository.findAll();
        List<DualMailer> dms = dualMailerRepository.findAll();
        Optional<List<Product>> products = productRepository.findproductsBySupplierAXCode(supplier.getVendorAXCode());

        List<RateCardDTO> rows = new ArrayList<RateCardDTO>();
        Map<String, List<ProductDTO>> mapProduct = new HashMap<>(); 
        
        // Returns a map of Brand and list of skus within that brand.
        for (Product product : products.get()) {
        	List<ProductDTO> tempList = mapProduct.get(product.getBrand().getName());
        	if(!mapProduct.containsKey(product.getBrand().getName())) {
        		tempList = new ArrayList<>();
        		mapProduct.put(product.getBrand().getName(), tempList);
        	}
        	tempList.add(new ProductDTO(product.getGXHID(),product.getMarketingShortName(), product.getId()));
        }

        for (RateCard rateCard : rateCards) {
            RateCardDTO row = new RateCardDTO();
            row.setPname(rateCard.getName());
            row.setPcode(rateCard.getCode());
            row.setPrate(rateCard.getRateCardDollar());

            List<DualMailerDTO> dmList = new ArrayList<>();

            for (DualMailer dm : dms) {
                DualMailerDTO dto = null;
                BrandProductDTO bpDto = null;

                dto = new DualMailerDTO();
                bpDto = new BrandProductDTO();
                bpDto.setPromosku(new ArrayList<ProductDTO>());

                dto.setId(row.getPcode()+dm.getCode());
                dto.setValue(0);
                dto.setPromosku(new ArrayList<ProductDTO>());
                dto.setPromoBrandSku(bpDto);

                dmList.add(dto);
            }
            row.setDualmailers(dmList);
            //Add dual mailer mapped with ratecard as a row
            rows.add(row);
        }
        //Set products
        promoDTO.setMapOfProducts(mapProduct);
        //Set rate cards
        promoDTO.setRatecards(rows);

        return promoDTO;

    }
}
