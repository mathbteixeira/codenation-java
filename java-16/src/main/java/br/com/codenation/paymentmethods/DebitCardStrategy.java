package br.com.codenation.paymentmethods;

public class DebitCardStrategy implements PriceStrategy {

    public final double DEBIT_CARD_DISCOUNT = 0.95;

    @Override
    public Double calculate(Double price) {
        return price * DEBIT_CARD_DISCOUNT;
    }
}