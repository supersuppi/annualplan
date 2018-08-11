package com.gxh.apserver.repository.interfaces;

import com.gxh.apserver.entity.Promotion;
import com.gxh.apserver.entity.Supplier;
import com.gxh.apserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    @Query("select s from Supplier s where s.supplier = ?1")
    Optional<Supplier> findSupplierByUserID(User userID);
}
