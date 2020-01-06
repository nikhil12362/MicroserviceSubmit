package com.nikhil.cloth;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ShirtRepo extends JpaRepository<Shirt, Integer> {

	@Query("SELECT p FROM Shirt p WHERE LOWER(p.brandName) = LOWER(:brandName)")
	public List<Shirt> groupByBrandName(@Param("brandName") String brandName);

	@Query("SELECT p FROM Shirt p WHERE LOWER(p.skuId) = LOWER(:skuId)")
	public List<Shirt> findBySkuId(@Param("skuId") String skuId);

	@Query("SELECT p FROM Shirt p WHERE p.price < :price")
	public List<Shirt> groupByPrice(@Param("price") double price);

	@Query("SELECT p FROM Shirt p WHERE p.size = :size")
	public List<Shirt> groupBySize(@Param("size") int size);

	@Query("SELECT p FROM Shirt p WHERE p.totalQuantity >=  :totalQuantity")
	public List<Shirt> groupByQuantity(@Param("totalQuantity") int totalQuantity);

	@Modifying
	@Query("UPDATE Shirt p set p.totalQuantity = :totalQuantity WHERE LOWER(p.skuId) = LOWER(:skuId)")
	@Transactional
	public void updateQuantity(@Param("totalQuantity") int totalQuantity, @Param("skuId") String skuId);

}
