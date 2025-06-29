package com.fyttaiscoding.paymentsms.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private Long id;
    private String userId;
    private Double amount;
    private String method; // "CREDIT_CARD", "PAYPAL"
    private String status; // "PENDING", "SUCCESS", "FAILED"
}
