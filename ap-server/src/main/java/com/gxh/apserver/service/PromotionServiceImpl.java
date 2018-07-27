package com.gxh.apserver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxh.apserver.dto.BrandProductDTO;
import com.gxh.apserver.dto.DualMailerDTO;
import com.gxh.apserver.dto.ProductDTO;
import com.gxh.apserver.dto.PromoDTO;
import com.gxh.apserver.dto.RateCardDTO;
import com.gxh.apserver.entity.DualMailer;
import com.gxh.apserver.entity.Product;
import com.gxh.apserver.entity.Promotion;
import com.gxh.apserver.entity.RateCard;
import com.gxh.apserver.entity.Supplier;
import com.gxh.apserver.repository.interfaces.DualMailerRepository;
import com.gxh.apserver.repository.interfaces.ProductRepository;
import com.gxh.apserver.repository.interfaces.PromotionRepository;
import com.gxh.apserver.repository.interfaces.RateCardRepository;
import com.gxh.apserver.repository.interfaces.SupplierRepository;
import com.gxh.apserver.service.interfaces.PromotionService;

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

    @Override
    public PromoDTO getSupplierActivePromo(Long supplierID) {

        Optional<Supplier> supplier = supplierRepository.findById(supplierID);

        if(supplier.isPresent()) {
            Optional<Promotion> promo = promotionRepository.findSupplierPromotionByStatus(supplier.get(),"ACTIVE");
            Optional<List<Product>> products = productRepository.findproductsBySupplierAXCode(supplier.get().getVendorAXCode());

            if(promo.isPresent()) {
                System.out.println("Promo is present");
            } else {
                System.out.println("promo is not present,create new");
                PromoDTO promoDTO = new PromoDTO();
                //Details about this promotion
                promoDTO.setIsEditable(true);
                promoDTO.setPromoyear("2018");
                promoDTO.setVersion(1);

                List<RateCard> rateCards = rateCardRepository.findAll();
                List<DualMailer> dms = dualMailerRepository.findAll();

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
        } else {
            System.out.println("supplier is null");
        }
        return null;
    }
}
