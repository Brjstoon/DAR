package com.example.DAR.Repository;

import com.example.DAR.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment findPaymentById(Integer id);
}
