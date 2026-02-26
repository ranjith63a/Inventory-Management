package com.org.invmgm.repository;

import com.org.invmgm.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findById(Long id, Pageable pageable);
    Page<Product> findByProductNameContainingIgnoreCase(String productName, Pageable pageable);
    Page<Product> findByIdEqualsAndProductNameContainingIgnoreCase(Long id, String productName, Pageable pageable);
}
