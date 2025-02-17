package com.alexa.store.dto;

import com.alexa.store.entity.Order;
import com.alexa.store.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PaymentStatusDTO {

    private int orderId;

    private String transactionId;

    private PaymentMethod paymentMethod;

}
