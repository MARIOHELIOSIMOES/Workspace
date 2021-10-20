package services;

public class PayService {
    public PayService(TaxService taxService, DeliveryService deliveryService){
        this.taxService = taxService;
        this.deliveryService = deliveryService;
    }
    private TaxService taxService;
    private  DeliveryService deliveryService;

    public double finalPrice(double cost, String state){
        return cost + taxService.tax(cost)+deliveryService.fee(state);
    }
}
