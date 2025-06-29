package com.fyttaiscoding.paymentsms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fyttaiscoding.paymentsms.models.Payment;
import com.fyttaiscoding.paymentsms.services.PaymentService;

@SpringBootApplication
public class PaymentsmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentsmsApplication.class, args);
	}

	@Bean
    CommandLineRunner loadData(PaymentService paymentService) {
        return args -> {
            paymentService.createPayment(new Payment(null, "user1", 100.0, "credit_card", null));
            paymentService.createPayment(new Payment(null, "user2", 49.99, "paypal", null));
            paymentService.createPayment(new Payment(null, "user2", 15.5, "credit_card", null));
            paymentService.createPayment(new Payment(null, "user4", 99.0, "paypal", "FAILED"));
            paymentService.createPayment(new Payment(null, "user5", 25.5, "credit_card", "FAILED"));
        };
    }

}
