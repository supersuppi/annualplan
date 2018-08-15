package com.gxh.apserver.repository.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gxh.apserver.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("select p from Product p where p.vendorAXCode = ?1 order by p.brand")
    Optional<List<Product>> findproductsBySupplierAXCode(Long vendorAXCode);
    
	@Query(" select prd from Product prd, PromotionLevelSKU psku where prd.id = psku.product "
			+ "and  psku.rateCard = ?3 and psku.dualMailer = ?2 and psku.promo = ?1 and psku.promoCount = ?4 ")
	public Optional<List<Product>> findAllSelectedProducts(Long promoId, Long dmId, Long rowId, int promoCount);

}
