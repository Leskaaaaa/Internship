package com.leska.springapp.services;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class VacationService {

    public String getVacationSalaryCalculate(double salary, int vacationDays) {
        double salaryADay = (salary * 12) / 365;
        return new DecimalFormat("#.##").format(salaryADay * vacationDays);
    }

    public String getVacationPayOfPeriodDay(double salary, LocalDate startDate, LocalDate endDate) {
        int workingDay = 0;
        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            if (isWeekends(date) || isHoliday(date)) {
                continue;
            }
            workingDay++;
        }
        double salaryADay = (salary * 12) / 365;
        return new DecimalFormat("#.##").format(salaryADay * workingDay);
    }

    private boolean isWeekends(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.WEDNESDAY;
    }

    private boolean isHoliday(LocalDate date) {
        List<LocalDate> holidays = List.of(
                LocalDate.of(date.getYear(), Month.JANUARY, 1)
        );
        return holidays.contains(date);
    }
}
