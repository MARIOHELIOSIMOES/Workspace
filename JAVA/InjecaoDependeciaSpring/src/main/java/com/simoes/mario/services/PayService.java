package com.simoes.mario.services;


import org.springframework.stereotype.Service;

@Service
public class PayService {
    private TaxService taxService;
    private  DeliveryService deliveryService;

    public PayService(TaxService taxService, DeliveryService deliveryService){
        this.taxService = taxService;
        this.deliveryService = deliveryService;
    }


    public double finalPrice(double cost, String state){
        return cost + taxService.tax(cost)+deliveryService.fee(state);
    }
}
