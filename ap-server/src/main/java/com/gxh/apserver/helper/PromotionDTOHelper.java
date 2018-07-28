package com.gxh.apserver.helper;

import com.gxh.apserver.dto.*;
import com.gxh.apserver.entity.*;
import com.gxh.apserver.repository.interfaces.DualMailerRepository;
import com.gxh.apserver.repository.interfaces.ProductRepository;
import com.gxh.apserver.repository.interfaces.PromotionLevelRateCardRepository;
import com.gxh.apserver.repository.interfaces.RateCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PromotionDTOHelper {

    @Autowired
    private RateCardRepository rateCardRepository;

    @Autowired
    private DualMailerRepository dualMailerRepository;

    @Autowired
    private PromotionLevelRateCardRepository promotionLevelRateCardRepository;

    @Autowired
    private ProductRepository productRepository;


    public PromoDTO buildExistingPromoDTO(Supplier supplier, Promotion promo) {
        System.out.println("build from saved promo");
        PromoDTO promoDTO = new PromoDTO();
        //Details about this promotion
        promoDTO.setIsEditable(true);
        promoDTO.setPromoyear("2018");
        promoDTO.setVersion(1);
        promoDTO.setUserid(supplier.getId());

        List<RateCard> rateCards = rateCardRepository.findAll();
        List<DualMailer> dms = dualMailerRepository.findAll();
        Optional<List<Product>> products = productRepository.findproductsBySupplierAXCode(supplier.getVendorAXCode());
        Optional<List<PromotionLevelRateCard>> ratecardDms = promotionLevelRateCardRepository.findAllByPromoID(promo.getId());

        List<RateCardDTO> rows = new ArrayList<RateCardDTO>();
        List<ProductDTO> skus = new ArrayList<ProductDTO>();
        Map<String,Integer> rcdmMap = new HashMap<>();

        for (PromotionLevelRateCard rcdm : ratecardDms.get()) {
            //(K,V) = (rateCardID+DuailmailerID, value)
            rcdmMap.put(rcdm.getRateCard().toString()+rcdm.getDualMailer().toString(),rcdm.getValue());
        }

        for (Product product : products.get()) {
            skus.add(new ProductDTO(product.getGXHID(),product.getMarketingShortName()));
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
        promoDTO.setProducts(skus);
        //Set rate cards
        promoDTO.setRatecards(rows);

        return promoDTO;
    }

    public PromoDTO buildNewPromoDTO(Supplier supplier) {
        System.out.println("building new promo");
        PromoDTO promoDTO = new PromoDTO();
        //Details about this promotion
        promoDTO.setIsEditable(true);
        promoDTO.setPromoyear("2018");
        promoDTO.setVersion(1);
        promoDTO.setUserid(supplier.getId());

        List<RateCard> rateCards = rateCardRepository.findAll();
        List<DualMailer> dms = dualMailerRepository.findAll();
        Optional<List<Product>> products = productRepository.findproductsBySupplierAXCode(supplier.getVendorAXCode());

        List<RateCardDTO> rows = new ArrayList<RateCardDTO>();
        List<ProductDTO> skus = new ArrayList<ProductDTO>();

        for (Product product : products.get()) {
            skus.add(new ProductDTO(product.getGXHID(),product.getMarketingShortName()));
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
        promoDTO.setProducts(skus);
        //Set rate cards
        promoDTO.setRatecards(rows);

        return promoDTO;

    }
}
