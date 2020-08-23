package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {
    @Override
    public BigDecimal somar(Object object) {
        return this.realizarOperacao(object, Somar.class);
    }

    @Override
    public BigDecimal subtrair(Object object) {
        return this.realizarOperacao(object, Subtrair.class).abs();
    }

    @Override
    public BigDecimal totalizar(Object object) {
        return this.somar(object).subtract(this.subtrair(object));
    }

    private BigDecimal realizarOperacao(Object object, Class<? extends Annotation> annotation) {
        BigDecimal resultado = BigDecimal.ZERO;
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getType().equals(BigDecimal.class) && field.isAnnotationPresent(annotation)) {
                try {
                    if (annotation.equals(Somar.class)) {
                        resultado = resultado.add((BigDecimal) field.get(object));
                    }
                    else if (annotation.equals(Subtrair.class)) {
                        resultado = resultado.subtract((BigDecimal) field.get(object));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultado;
    }
}
