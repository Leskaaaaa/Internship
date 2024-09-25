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

    public String getVacationPayOfPeriodDay(Vacation vacation) {
        int workingDay = 0;

        for (LocalDate date = vacation.getVacationStartDate(); date.isBefore(vacation.getVacationEndDate()); date = date.plusDays(1)) {
            if (isWeekends(date) || isHoliday(date)) {
                continue;
            }
            workingDay++;
        }

        double salaryADay = (vacation.getSalary() * 12) / 365;
        return new DecimalFormat("#.##").format(salaryADay * workingDay);
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
