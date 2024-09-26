package com.leska.springapp.services;

import com.leska.springapp.model.Vacation;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class VacationService {

    public String calculateVacationPay(Vacation vacation) {
        String vacationPay;

        if (vacation.getVacationStartDate() == null || vacation.getVacationEndDate() == null) {
            vacationPay = getVacationPay(vacation);
        } else {
            vacationPay = getVacationPayOfPeriodDay(vacation);
        }
        return vacationPay;
    }

    private String getVacationPay(Vacation vacation) {
        int vacationDays = vacation.getDayOfVacation();

        double salaryPerDay = (vacation.getSalary() * 12) / 365;
        return new DecimalFormat("#.##").format(salaryPerDay * vacationDays);
    }

    private String getVacationPayOfPeriodDay(Vacation vacation) {
        int vacationDays = getWorkingDays(vacation.getVacationStartDate(), vacation.getVacationEndDate());

        double salaryPerDay = (vacation.getSalary() * 12) / 365;
        return new DecimalFormat("#.##").format(salaryPerDay * vacationDays);
    }

    private int getWorkingDays(LocalDate startDate, LocalDate endDate) {
        int workingDay = 0;

        while (startDate.isBefore(endDate)) {
            if (isWeekends(startDate) || isHoliday(startDate)) {
                startDate = startDate.plusDays(1);
                continue;
            }
            workingDay++;
            startDate = startDate.plusDays(1);
        }

        return workingDay;
    }

    private boolean isWeekends(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private boolean isHoliday(LocalDate date) {
        List<LocalDate> holidays = List.of(
                LocalDate.of(date.getYear(), Month.JANUARY, 1)
                // Add next Holidays
        );
        return holidays.contains(date);
    }
}
