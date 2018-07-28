package com.gxh.apserver.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.gxh.apserver.entity.*;
import com.gxh.apserver.helper.PromotionDTOHelper;
import com.gxh.apserver.repository.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxh.apserver.dto.BrandProductDTO;
import com.gxh.apserver.dto.DualMailerDTO;
import com.gxh.apserver.dto.ProductDTO;
import com.gxh.apserver.dto.PromoDTO;
import com.gxh.apserver.dto.RateCardDTO;
import com.gxh.apserver.service.interfaces.PromotionService;

import javax.xml.crypto.Data;

@Service(value = "promotionService")
public class PromotionServiceImpl implements PromotionService {

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
    public PromoDTO getSupplierActivePromo(Long supplierID) {

        Optional<Supplier> supplier = supplierRepository.findById(supplierID);

        if(supplier.isPresent()) {
            Optional<Promotion> promo = promotionRepository.findSupplierPromotionByStatus(supplier.get(),"ACTIVE");

            if(promo.isPresent()) {
                System.out.println("Promo is present");
                return promotionDTOHelper.buildExistingPromoDTO(supplier.get(),promo.get());

            } else {
                System.out.println("Promo is not present");
                return promotionDTOHelper.buildNewPromoDTO(supplier.get());
            }
        } else {
            System.out.println("supplier is null");
        }
        return null;
    }

    @Override
    public Boolean saveSupplierPromo(final PromoDTO promoDTO) throws ParseException {

        Optional<Supplier> supplier = supplierRepository.findById(promoDTO.getUserid());

        //Create promo
        Promotion promo = new Promotion();
        promo.setStatus("ACTIVE");
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
}
