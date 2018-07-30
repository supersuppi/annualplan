package com.gxh.apserver.repository.interfaces;

import com.gxh.apserver.constants.PromotionStatus;
import com.gxh.apserver.entity.Promotion;
import com.gxh.apserver.entity.Supplier;
import com.gxh.apserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion,Long> {

    @Query("select p from Promotion p where p.supplier = ?1 and p.status = ?2")
    Optional<Promotion> findSupplierPromotionByStatus(Supplier supplier,PromotionStatus status);

    @Query("select p from Promotion p where p.supplier = ?1")
    Optional<Promotion> findSupplierPromotionByID(Supplier supplier);

    @Query("select p from Promotion p where p.supplier = ?1 and p.year = ?2")
    Optional<Promotion> findSupplierPromotionByYear(Supplier supplier,Date date);

    @Query("select p from Promotion p where p.supplier = ?1 and p.year = ?2")
    Optional<Promotion> findSupplierPromotionForManagerByYear(Supplier supplier,Date date);

}
