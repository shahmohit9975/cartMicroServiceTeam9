package com.coviam.cartMicroServiceTeam_9.repository;

import com.coviam.cartMicroServiceTeam_9.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Integer> {
}
