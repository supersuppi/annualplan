package com.gxh.apserver.service;

import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.dto.*;
import com.gxh.apserver.entity.*;
import com.gxh.apserver.exceptions.ResourceNotFoundException;
import com.gxh.apserver.repository.interfaces.*;
import com.gxh.apserver.service.interfaces.AdminService;
import com.gxh.apserver.service.interfaces.HomeService;
import com.gxh.apserver.util.BudgetCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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
    private AnnualPromotionRepository annualPromotionRepository;
    @Autowired
    private SupplierPromotionBudgetRepository supplierPromotionBudgetRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private PromoCommentRepository promoCommentRepository;
    @Autowired
    private AdminService adminService;

    @Override
    public HomeDTO getUserHomeContent(String emailAddress) throws ResourceNotFoundException,ParseException {
        User user = userRepository.findByEmail(emailAddress);
        List<AdminPromoDTO> adminPromoDTO = adminService.getPromotionsByStatus(PromotionStatus.ACTIVE);

        if(user.getRole().getName().equalsIgnoreCase("VENDOR")) {
            SupplierHomeDTO supHomeDTO = new SupplierHomeDTO();
            supHomeDTO.setUserID(user.getId());
            supHomeDTO.setUserName(user.getEmail());
            supHomeDTO.setRole(user.getRole().getName());
            supHomeDTO.setActivePromotions(adminPromoDTO);

            return getDetailsForSupplier(user,supHomeDTO);
        } else if(user.getRole().getName().equalsIgnoreCase("CM")) {
            ManagerHomeDTO managerHomeDTO = new ManagerHomeDTO();
            managerHomeDTO.setUserID(user.getId());
            managerHomeDTO.setUserName(user.getEmail());
            managerHomeDTO.setRole(user.getRole().getName());
            managerHomeDTO.setActivePromotions(adminPromoDTO);

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
        Optional<List<Promotion>> promotions = annualPromotionRepository.findAllPromotionBySupplierID(supplier);
        Optional<List<PromoComments>> comments = promoCommentRepository.findAllCommentsBySupplierID(supplier);

        supHomeDTO.setSupplierID(supplier.getId());
        supHomeDTO.setSupplierName(supplier.getName());
        supHomeDTO.setSupplierCode(supplier.getVendorAXCode());

        List<PromoYearDetailDTO> promoYears = new ArrayList<PromoYearDetailDTO>();

        promotions.get().forEach(promo -> {
            //Set Active promo
            if (promo.getStatus().equals(PromotionStatus.ACTIVE)){
                supHomeDTO.setActivePromoYear(promo.getCreatedAt().toString());
            }
            // Set Budget
            Optional<SupplierPromotionBudget> promoBudget = supplierPromotionBudgetRepository.findByPromoID(promo);
            promoYears.add(new PromoYearDetailDTO(promo.getCreatedAt().toString(), BudgetCalculator.getBudget(promoBudget.get())));
        });
        supHomeDTO.setPromoYearDetails(promoYears);
        // Set Comments
        if(comments.isPresent()) {
            List<SupManCommentDTO> commentsDTO = new ArrayList<>();

            comments.get().forEach(comment ->
                    commentsDTO.add(new SupManCommentDTO(comment.getManager().getName(),comment.getComment(),comment.getCreatedAt())));
            supHomeDTO.setComments(commentsDTO);
        }
        return supHomeDTO;
    }
}
