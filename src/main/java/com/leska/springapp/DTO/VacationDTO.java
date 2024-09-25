package com.leska.springapp.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;

public class VacationDTO {

    @Min(value = 0, message = "Salary should be greater than 0")
    private double salary;

    @Min(value = 0, message = "Day of vacation should be greater than 0")
    private int dayOfVacation;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate vacationStartDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
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
