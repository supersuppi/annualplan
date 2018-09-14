package com.gxh.apserver.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.dto.AdminPromoDTO;
import com.gxh.apserver.dto.HomeDTO;
import com.gxh.apserver.dto.ManagerDTO;
import com.gxh.apserver.dto.ManagerHomeDTO;
import com.gxh.apserver.dto.SupManCommentDTO;
import com.gxh.apserver.dto.SupplierDTO;
import com.gxh.apserver.dto.SupplierHomeDTO;
import com.gxh.apserver.entity.Manager;
import com.gxh.apserver.entity.Notification;
import com.gxh.apserver.entity.PromoComments;
import com.gxh.apserver.entity.Promotion;
import com.gxh.apserver.entity.Supplier;
import com.gxh.apserver.entity.User;
import com.gxh.apserver.exceptions.ResourceNotFoundException;
import com.gxh.apserver.repository.interfaces.AnnualPromotionRepository;
import com.gxh.apserver.repository.interfaces.ManagerRepository;
import com.gxh.apserver.repository.interfaces.NotificationRepository;
import com.gxh.apserver.repository.interfaces.PromoCommentRepository;
import com.gxh.apserver.repository.interfaces.PromotionRepository;
import com.gxh.apserver.repository.interfaces.SupplierRepository;
import com.gxh.apserver.repository.interfaces.UserRepository;
import com.gxh.apserver.service.interfaces.AdminService;
import com.gxh.apserver.service.interfaces.HomeService;

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
    private ManagerRepository managerRepository;
    @Autowired
    private PromoCommentRepository promoCommentRepository;
    @Autowired
    private AdminService adminService;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public HomeDTO getUserHomeContent(String emailAddress) throws ResourceNotFoundException,ParseException {
        logger.info(">>> getUserHomeContent");
        User user = userRepository.findByEmail(emailAddress);
        List<AdminPromoDTO> adminPromoDTO = adminService.getPromotionsByStatus(PromotionStatus.ACTIVE);
        List<Notification> notifications = notificationRepository.findAll();

        if(user.getRole().getName().equalsIgnoreCase("VENDOR")) {
            SupplierHomeDTO supHomeDTO = new SupplierHomeDTO();
            supHomeDTO.setUserID(user.getId());
            supHomeDTO.setUserName(user.getEmail());
            supHomeDTO.setRole(user.getRole().getName());
            supHomeDTO.setActivePromotions(adminPromoDTO);
            supHomeDTO.setNotifications(notifications);

            return getDetailsForSupplier(user,supHomeDTO);
        } else if(user.getRole().getName().equalsIgnoreCase("CM")) {
            ManagerHomeDTO managerHomeDTO = new ManagerHomeDTO();
            managerHomeDTO.setUserID(user.getId());
            managerHomeDTO.setUserName(user.getEmail());
            managerHomeDTO.setRole(user.getRole().getName());
            managerHomeDTO.setNotifications(notifications);

            return getDetailsForManager(user,managerHomeDTO,adminPromoDTO);
        } else {
            throw new ResourceNotFoundException("No user details found");
        }
    }

    protected SupplierHomeDTO getDetailsForSupplier(User user,SupplierHomeDTO supHomeDTO) {
        logger.info(">>> getDetailsForSupplier");
        Optional<Supplier> supplier = supplierRepository.findSupplierByUserID(user);
        Set<Manager> managers = supplier.get().getManagers();

        List<ManagerDTO> managersDTO = new ArrayList<>();
        managers.forEach(manager -> managersDTO.add(new ManagerDTO(manager.getId(),manager.getName(),manager.getType())));
        supHomeDTO.setManagers(managersDTO);

        return getSupplierDetails(supplier.get(),supHomeDTO);
    }

    protected ManagerHomeDTO getDetailsForManager(User user,ManagerHomeDTO managerHomeDTO,List<AdminPromoDTO> adminPromoDTO) {
        logger.info(">>> getDetailsForManager");
        Optional<Manager> manager = managerRepository.findManagerByUserID(user);
        managerHomeDTO.setMamangerName(manager.get().getName());
        managerHomeDTO.setManagerID(manager.get().getId());

        List<SupplierHomeDTO> suppliersHomeDTO = new ArrayList<>();
        Set<Supplier> suppliers = manager.get().getSuppliers();
        suppliers.forEach(supplier -> {
            suppliersHomeDTO.add(getSupplierDetails(supplier,new SupplierHomeDTO()));
        });

        //Find all suppliers by promotion and add to promodto
        adminPromoDTO.forEach((promodto)-> {
            Optional<Promotion> promo = promotionRepository.findById(promodto.getPid());
            List<Supplier> allSuppliersByPromotion = annualPromotionRepository.findAllSuppliersByPromotion(promo.get());

            if(allSuppliersByPromotion.size()!=0){
                List<SupplierDTO> supList = new ArrayList<>();
                allSuppliersByPromotion.forEach(supplier -> supList.add(new SupplierDTO(supplier.getName(),supplier.getVendorAXCode(),supplier.getId())));
                promodto.setSuppliers(supList);
            }
        });

        managerHomeDTO.setActivePromotions(adminPromoDTO);
        managerHomeDTO.setSuppliers(suppliersHomeDTO);
        return managerHomeDTO;
    }

    protected SupplierHomeDTO getSupplierDetails(Supplier supplier,SupplierHomeDTO supHomeDTO){
        logger.info(">>> getSupplierDetails");
        Optional<List<PromoComments>> comments = promoCommentRepository.findAllCommentsBySupplierID(supplier);

        supHomeDTO.setSupplierID(supplier.getId());
        supHomeDTO.setSupplierName(supplier.getName());
        supHomeDTO.setSupplierCode(supplier.getVendorAXCode());

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
