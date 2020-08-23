package br.com.codenation.paymentmethods;

public class CashStrategy implements PriceStrategy {

    public final double CASH_DISCOUNT = 0.9;

    @Override
    public Double calculate(Double price) {
        return price * CASH_DISCOUNT;
    }
}