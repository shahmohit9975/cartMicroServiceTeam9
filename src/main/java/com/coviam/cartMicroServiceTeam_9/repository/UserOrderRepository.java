package com.coviam.cartMicroServiceTeam_9.repository;

import com.coviam.cartMicroServiceTeam_9.dto.UserOrderDTO;
import com.coviam.cartMicroServiceTeam_9.entity.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Integer> {
    List<UserOrder> findAllByUserEmail(String userEmail);
}
