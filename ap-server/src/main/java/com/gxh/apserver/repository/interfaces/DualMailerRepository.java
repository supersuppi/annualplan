package com.gxh.apserver.repository.interfaces;

import com.gxh.apserver.entity.DualMailer;
import com.gxh.apserver.entity.Promotion;
import com.gxh.apserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DualMailerRepository extends JpaRepository<DualMailer, Long> {
    @Query("select dm from DualMailer dm where dm.promotion = ?1")
    List<DualMailer> findAllDMbyPromotion(Promotion promo);
}
