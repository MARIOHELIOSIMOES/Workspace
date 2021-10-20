package services;

public class DeliveryService {
    public DeliveryService(){

    }
    public double fee(String state){
        if(state.equalsIgnoreCase("SP")){
            return 10.0;
        }
        return 20.0;
    }
}
