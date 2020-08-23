package br.com.codenation.paymentmethods;

public class CreditCardStrategy implements PriceStrategy {

    public final double CREDIT_CARD_DISCOUNT = 0.98;

    @Override
    public Double calculate(Double price) {
        return price * CREDIT_CARD_DISCOUNT;
    }
}