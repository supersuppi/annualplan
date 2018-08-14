package com.gxh.apserver.repository.interfaces;

import com.gxh.apserver.entity.PromoComments;
import com.gxh.apserver.entity.PromotionLevelRateCard;
import com.gxh.apserver.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PromoCommentRepository extends JpaRepository<PromoComments,Long> {
    @Query("SELECT c FROM PromoComments c WHERE c.supplier = ?1 ORDER BY c.createdAt DESC")
    Optional<List<PromoComments>> findAllCommentsBySupplierID(Supplier supplier);
}
