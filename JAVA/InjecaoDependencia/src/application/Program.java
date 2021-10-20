package application;

import services.DeliveryService;
import services.PayService;
import services.TaxService;

public class Program {
    public static void main(String[] args) {
        TaxService taxService = new TaxService();
        DeliveryService deliveryService = new DeliveryService();
        PayService payService = new PayService(taxService, deliveryService);
        System.out.printf("Resultado = %.2f", payService.finalPrice(300.0,"SC"));
    }
}
