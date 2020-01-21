package com.coviam.cartMicroService.repository;

import com.coviam.cartMicroService.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Integer> {
}
