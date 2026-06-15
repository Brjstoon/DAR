package com.example.DAR.Service;


import com.example.DAR.Api.ApiException;
import com.example.DAR.DTO.In.PaymentDtoIn;
import com.example.DAR.DTO.Out.PaymentDtoOut;
import com.example.DAR.Model.Payment;
import com.example.DAR.Model.UserSubscription;
import com.example.DAR.Repository.PaymentRepository;
import com.example.DAR.Repository.UserSubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserSubscriptionRepository userSubscriptionRepository;
    private final ModelMapper modelMapper;

    public List<PaymentDtoOut> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        List<PaymentDtoOut> dtoOuts = new ArrayList<>();

        for (Payment payment : payments) {
            dtoOuts.add(convertToDtoOut(payment));
        }

        return dtoOuts;
    }
// Convert Payment entity to PaymentDtoOut manually because
// I  only need return the userSubscription id not full UserSubscription object
private PaymentDtoOut convertToDtoOut(Payment payment) {
    PaymentDtoOut dto = new PaymentDtoOut();

    dto.setId(payment.getId());
    dto.setUserSubscriptionId(payment.getUserSubscription().getId());
    dto.setAmount(payment.getAmount());
    dto.setPaymentMethod(payment.getPaymentMethod());
    dto.setPaymentDate(payment.getPaymentDate());
    dto.setStatus(payment.getStatus());
    dto.setTransactionReference(payment.getTransactionReference());

    return dto;
}
///

public void addPayment(Integer userSubscriptionId, PaymentDtoIn dto) {

    UserSubscription userSubscription =
            userSubscriptionRepository.findUserSubscriptionById(userSubscriptionId);

    if (userSubscription == null) {
        throw new ApiException("User subscription not found");
    }

    Payment payment = new Payment();

    payment.setUserSubscription(userSubscription);
    payment.setAmount(dto.getAmount());
    payment.setPaymentMethod(dto.getPaymentMethod());
    payment.setTransactionReference(dto.getTransactionReference());
    payment.setPaymentDate(LocalDate.now());
    payment.setStatus("PAID");

    paymentRepository.save(payment);

    userSubscription.setPaymentStatus("PAID");
    userSubscriptionRepository.save(userSubscription);
}

    public void updatePayment(Integer paymentId, Integer userSubscriptionId, PaymentDtoIn dto) {

        Payment oldPayment = paymentRepository.findPaymentById(paymentId);

        if (oldPayment == null) {
            throw new ApiException("Payment not found");
        }

        UserSubscription userSubscription =
                userSubscriptionRepository.findUserSubscriptionById(userSubscriptionId);

        if (userSubscription == null) {
            throw new ApiException("User subscription not found");
        }

        oldPayment.setUserSubscription(userSubscription);
        oldPayment.setAmount(dto.getAmount());
        oldPayment.setPaymentMethod(dto.getPaymentMethod());
        oldPayment.setTransactionReference(dto.getTransactionReference());

        paymentRepository.save(oldPayment);
    }

    public void deletePayment(Integer id) {
        Payment payment = paymentRepository.findPaymentById(id);

        if (payment == null) {
            throw new ApiException("Payment not found");
        }

        paymentRepository.delete(payment);
    }

}
