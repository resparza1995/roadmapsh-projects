package com.fyttaiscoding.paymentsms.repositories;

import java.util.List;
import java.util.Optional;

import com.fyttaiscoding.paymentsms.models.Payment;

public interface IPaymentRepository {
    void save(Payment payment);
    Optional<Payment> findById(Long id);
    List<Payment> findAll();
    void deleteById(Long id);
}
