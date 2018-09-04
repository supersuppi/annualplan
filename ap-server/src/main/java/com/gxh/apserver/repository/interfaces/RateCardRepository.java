package com.gxh.apserver.repository.interfaces;

import com.gxh.apserver.entity.Product;
import com.gxh.apserver.entity.Promotion;
import com.gxh.apserver.entity.RateCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RateCardRepository extends JpaRepository<RateCard, Long> {
    @Query("select rc from RateCard rc where rc.promotion = ?1")
    Optional<List<RateCard>> findAllRateCardBYPromotionID(Promotion promo);
    @Query("select rc from RateCard rc where rc.promotion = ?1")
    List<RateCard> findRateCardBYPromotionID(Promotion promo);
}
