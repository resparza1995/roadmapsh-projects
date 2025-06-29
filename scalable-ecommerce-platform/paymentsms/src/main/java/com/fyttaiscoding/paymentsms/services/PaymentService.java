package com.fyttaiscoding.paymentsms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fyttaiscoding.paymentsms.models.Payment;
import com.fyttaiscoding.paymentsms.repositories.IPaymentRepository;
import com.fyttaiscoding.paymentsms.repositories.InMemoryPaymentRepository;

@Service
public class PaymentService {

    private final IPaymentRepository repository;

    public PaymentService(InMemoryPaymentRepository repository) {
        this.repository = repository;
    }

    public Payment createPayment(Payment payment) {
        payment.setStatus(payment.getStatus() != null ? payment.getStatus() : "PENDING");
        repository.save(payment);
        if ("PENDING".equals(payment.getStatus())) {
            startDelay(); // Simulate payment processing
            payment.setStatus("SUCCESS");
        }
        return payment;
    }

    public Optional<Payment> getPayment(Long id) {
        return repository.findById(id);
    }

    public List<Payment> getAllPayments() {
        return repository.findAll();
    }

    public void deletePayment(Long id) {
        repository.deleteById(id);
    }

    private void startDelay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
