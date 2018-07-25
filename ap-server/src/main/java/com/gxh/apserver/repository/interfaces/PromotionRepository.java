package com.gxh.apserver.repository.interfaces;

import com.gxh.apserver.entity.Promotion;
import com.gxh.apserver.repository.PromotionRepositoryCustomImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion,Long>,PromotionRepositoryCustom {
}
