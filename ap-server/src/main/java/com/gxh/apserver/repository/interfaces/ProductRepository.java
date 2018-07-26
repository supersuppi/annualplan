package com.gxh.apserver.repository.interfaces;

import com.gxh.apserver.entity.Product;
import com.gxh.apserver.entity.Promotion;
import com.gxh.apserver.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("select p from Product p where p.vendorAXCode = ?1")
    Optional<List<Product>> findproductsBySupplierAXCode(Long vendorAXCode);
}
