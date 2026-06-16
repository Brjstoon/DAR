package com.example.DAR.Repository;

import com.example.DAR.Model.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Integer> {
    UserSubscription findUserSubscriptionById(Integer id);
}
