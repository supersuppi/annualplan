package com.gxh.apserver.service;

import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.dto.*;
import com.gxh.apserver.entity.Promotion;
import com.gxh.apserver.entity.Supplier;
import com.gxh.apserver.entity.SupplierPromotionBudget;
import com.gxh.apserver.entity.User;
import com.gxh.apserver.exceptions.ResourceNotFoundException;
import com.gxh.apserver.repository.interfaces.PromotionRepository;
import com.gxh.apserver.repository.interfaces.SupplierPromotionBudgetRepository;
import com.gxh.apserver.repository.interfaces.SupplierRepository;
import com.gxh.apserver.repository.interfaces.UserRepository;
import com.gxh.apserver.service.interfaces.HomeService;
import com.gxh.apserver.util.BudgetCalculator;
import com.gxh.apserver.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HomeServiceImpl implements HomeService {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private SupplierPromotionBudgetRepository supplierPromotionBudgetRepository;

    @Override
    public HomeDTO getUserHomeContent(String emailAddress) throws ResourceNotFoundException {
        User user = userRepository.findByEmail(emailAddress);

        if(user.getRole().getName().equalsIgnoreCase("VENDOR")) {
            SupplierHomeDTO supHomeDTO = new SupplierHomeDTO();
            supHomeDTO.setUserID(user.getId());
            supHomeDTO.setUserName(user.getEmail());
            return getDetailsForSupplier(user,supHomeDTO);
        } else if(user.getRole().getName().equalsIgnoreCase("CM")) {
            return getDetailsForManager(user);
        } else {
            throw new ResourceNotFoundException("No user details found");
        }
    }

    protected SupplierHomeDTO getDetailsForSupplier(User user,SupplierHomeDTO supHomeDTO) {
        Optional<Supplier> supplier = supplierRepository.findSupplierByUserID(user);
        Optional<List<Promotion>> promotions = promotionRepository.findAllPromotionBySupplierID(supplier.get());

        supHomeDTO.setSupplierID(supplier.get().getId());
        supHomeDTO.setSupplierName(supplier.get().getName());
        supHomeDTO.setSupplierCode(supplier.get().getVendorAXCode());

        List<PromoYearDetailDTO> promoYears = new ArrayList<PromoYearDetailDTO>();

        promotions.get().forEach(promo -> {
            //Set Active promo
            if (promo.getStatus().equals(PromotionStatus.ACTIVE)){
                supHomeDTO.setActivePromoYear(promo.getYear().toString());
            }
            // Set Budget
            Optional<SupplierPromotionBudget> promoBudget = supplierPromotionBudgetRepository.findByPromoID(promo);
            promoYears.add(new PromoYearDetailDTO(promo.getYear().toString(), BudgetCalculator.getBudget(promoBudget.get())));
        });
        supHomeDTO.setPromoYearDetails(promoYears);

        return supHomeDTO;
    }

    protected ManagerHomeDTO getDetailsForManager(User user) {

        return null;
    }
}
