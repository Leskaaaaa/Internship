package com.leska.springapp.model;

import jakarta.validation.constraints.Min;

import java.time.LocalDate;

public class Vacation {

    @Min(value = 0, message = "Salary should be greater than 0")
    private double salary;

    @Min(value = 0, message = "Day of vacation should be greater than 0")
    private int dayOfVacation;

    private LocalDate vacationStartDate;

    private LocalDate vacationEndDate;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDayOfVacation() {
        return dayOfVacation;
    }

    public void setDayOfVacation(int dayOfVacation) {
        this.dayOfVacation = dayOfVacation;
    }

    public LocalDate getVacationStartDate() {
        return vacationStartDate;
    }

    public void setVacationStartDate(LocalDate vacationStartDate) {
        this.vacationStartDate = vacationStartDate;
    }

    public LocalDate getVacationEndDate() {
        return vacationEndDate;
    }

    public void setVacationEndDate(LocalDate vacationEndDate) {
        this.vacationEndDate = vacationEndDate;
    }
}
