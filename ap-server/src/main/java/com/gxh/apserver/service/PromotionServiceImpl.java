package com.gxh.apserver.service;

import com.gxh.apserver.dto.DualMailerDTO;
import com.gxh.apserver.dto.PromoDTO;
import com.gxh.apserver.entity.DualMailer;
import com.gxh.apserver.entity.RateCard;
import com.gxh.apserver.repository.interfaces.DualMailerRepository;
import com.gxh.apserver.repository.interfaces.PromotionRepository;
import com.gxh.apserver.repository.interfaces.RateCardRepository;
import com.gxh.apserver.service.interfaces.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "promotionService")
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private RateCardRepository rateCardRepository;

    @Autowired
    private DualMailerRepository dualMailerRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Override
    public List<PromoDTO> getSupplierActivePromo(Long supplierID) {

        //Promotion promo = promotionRepository.findSupplierPromotionByStatus(supplierID,"ACTIVE");
        //Promotion promo = promotionRepository.findById(new Long(1));
        List<RateCard> rateCards = rateCardRepository.findAll();
        List<DualMailer> dms = dualMailerRepository.findAll();

        if (true) {
            List<PromoDTO> rows = new ArrayList<PromoDTO>();

            for (RateCard rateCard : rateCards) {
                PromoDTO row = new PromoDTO();
                row.setPname(rateCard.getName());
                row.setPcode(rateCard.getCode());
                row.setPrate(rateCard.getRateCardDollar());
                row.setIsEditable(true);
                List<DualMailerDTO> dmList = new ArrayList<>();

                for (DualMailer dm : dms) {
                    dmList.add(new DualMailerDTO(row.getPcode()+dm.getCode(),0));
                }
                row.setDualmailers(dmList);

                rows.add(row);

            }

            return rows;
        }

        return null;
    }
}
