package com.gxh.apserver.service;

import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.dto.*;
import com.gxh.apserver.entity.*;
import com.gxh.apserver.exceptions.ResourceNotFoundException;
import com.gxh.apserver.repository.interfaces.*;
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
import java.util.Set;

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
    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public HomeDTO getUserHomeContent(String emailAddress) throws ResourceNotFoundException {
        User user = userRepository.findByEmail(emailAddress);

        if(user.getRole().getName().equalsIgnoreCase("VENDOR")) {
            SupplierHomeDTO supHomeDTO = new SupplierHomeDTO();
            supHomeDTO.setUserID(user.getId());
            supHomeDTO.setUserName(user.getEmail());
            return getDetailsForSupplier(user,supHomeDTO);
        } else if(user.getRole().getName().equalsIgnoreCase("CM")) {
            ManagerHomeDTO managerHomeDTO = new ManagerHomeDTO();
            managerHomeDTO.setUserID(user.getId());
            managerHomeDTO.setUserName(user.getEmail());
            return getDetailsForManager(user,managerHomeDTO);
        } else {
            throw new ResourceNotFoundException("No user details found");
        }
    }

    protected SupplierHomeDTO getDetailsForSupplier(User user,SupplierHomeDTO supHomeDTO) {
        Optional<Supplier> supplier = supplierRepository.findSupplierByUserID(user);
        Set<Manager> managers = supplier.get().getManagers();

        List<ManagerDTO> managersDTO = new ArrayList<>();
        managers.forEach(manager -> managersDTO.add(new ManagerDTO(manager.getId(),manager.getName(),manager.getType())));
        supHomeDTO.setManagers(managersDTO);

        return getSupplierDetails(supplier.get(),supHomeDTO);
    }

    protected ManagerHomeDTO getDetailsForManager(User user,ManagerHomeDTO managerHomeDTO) {
        Optional<Manager> manager = managerRepository.findManagerByUserID(user);
        managerHomeDTO.setMamangerName(manager.get().getName());
        managerHomeDTO.setManagerID(manager.get().getId());

        List<SupplierHomeDTO> suppliersDTO = new ArrayList<>();
        Set<Supplier> suppliers = manager.get().getSuppliers();
        suppliers.forEach(supplier -> {
            suppliersDTO.add(getSupplierDetails(supplier,new SupplierHomeDTO()));
        });

        managerHomeDTO.setSuppliers(suppliersDTO);
        return managerHomeDTO;
    }

    protected SupplierHomeDTO getSupplierDetails(Supplier supplier,SupplierHomeDTO supHomeDTO){
        Optional<List<Promotion>> promotions = promotionRepository.findAllPromotionBySupplierID(supplier);

        supHomeDTO.setSupplierID(supplier.getId());
        supHomeDTO.setSupplierName(supplier.getName());
        supHomeDTO.setSupplierCode(supplier.getVendorAXCode());

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
}
