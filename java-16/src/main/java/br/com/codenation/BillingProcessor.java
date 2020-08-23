package br.com.codenation;

public class BillingProcessor {

    public Double calculate(Order order) {
        try {
            return order.getPaymentMethod().getPaymentStrategy().calculate(order.getPrice());
        } catch (Exception e){
            throw new RuntimeException("Payment method not implemented");
        }
    }
}