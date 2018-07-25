package com.gxh.apserver.repository;

import com.gxh.apserver.entity.Promotion;
import com.gxh.apserver.repository.interfaces.PromotionRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class PromotionRepositoryCustomImpl implements PromotionRepositoryCustom {
//    @PersistenceContext
//    EntityManager entityManager;
//
//    @Override
//    public Promotion findSupplierPromotionByStatus(Long id, String status) {
//        Query query = entityManager.createNativeQuery("SELECT * FROM PROMOTION", Promotion.class);
////        query.setParameter(1, id);
////        query.setParameter(2, status);
//        return (Promotion) query.getSingleResult();
//    }
}
