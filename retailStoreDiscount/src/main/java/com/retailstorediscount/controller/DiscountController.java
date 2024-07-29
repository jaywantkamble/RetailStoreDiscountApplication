package com.retailstorediscount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailstorediscount.model.Bill;
import com.retailstorediscount.service.DiscountService;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @PostMapping("/calculate")
    public ResponseEntity<Double> calculateNetPayableAmount(@RequestBody Bill bill) {
        double netPayableAmount = discountService.calculateNetPayableAmount(bill);
        return ResponseEntity.ok(netPayableAmount);
    }
}
