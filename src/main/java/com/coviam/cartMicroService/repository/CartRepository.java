package com.coviam.cartMicroService.repository;

import com.coviam.cartMicroService.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query(value = "SELECT * FROM cart as c WHERE c.user_email =?1 and c.merchant_and_product_id=?2 limit 1", nativeQuery = true)
    Cart findCart(String userEmail, String merchantAndProductId);

    List<Cart> findByUserEmail(String userEmail);
}