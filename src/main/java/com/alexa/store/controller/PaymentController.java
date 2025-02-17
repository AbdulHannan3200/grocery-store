package com.alexa.store.controller;

import com.alexa.store.dto.PaymentDTO;
import com.alexa.store.dto.PaymentStatusDTO;
import com.alexa.store.exception.OrderNotFoundException;
import com.alexa.store.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PutMapping("/status") // More appropriate endpoint for updating status
    public ResponseEntity<PaymentDTO> updatePaymentStatus(@RequestBody PaymentStatusDTO paymentStatusDTO) {
        try {
            PaymentDTO updatedPayment = paymentService.updatePaymentStatus(paymentStatusDTO);
            return new ResponseEntity<>(updatedPayment, HttpStatus.OK);
        } catch (Exception e) { // Catch potential exceptions (e.g., OrderNotFoundException)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Or a more specific error response
        }
    }


    // Example of a GET endpoint to retrieve payment details by order ID (if needed)
//    @GetMapping("/order/{orderId}")
//    public ResponseEntity<PaymentDTO> getPaymentByOrderId(@PathVariable int orderId) {
//        try {
//            PaymentDTO payment = paymentService.getPaymentByOrderId(orderId);
//            if (payment != null) {
//                return new ResponseEntity<>(payment, HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 if no payment found
//            }
//        } catch (OrderNotFoundException ex) { // Catch the specific exception
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND, ex.getMessage()); // Return 404 with message
//        } catch (Exception e) { // Catch other exceptions
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 for other errors
//        }

}


