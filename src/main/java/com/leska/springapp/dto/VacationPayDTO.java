package com.leska.springapp.dto;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class VacationPayDTO {
    private double vacationPay;

    public VacationPayDTO(String vacationPay) {
        this.vacationPay = parseDoubleWithComma(vacationPay);
    }

    public double getVacationPay() {
        return vacationPay;
    }

    public void setVacationPay(double vacationPay) {
        this.vacationPay = vacationPay;
    }

    private double parseDoubleWithComma(String value) {
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        Number number = null;

        try {
            number = format.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return number.doubleValue();
    }
}
