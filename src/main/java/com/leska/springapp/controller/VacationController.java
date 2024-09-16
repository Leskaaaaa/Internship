package com.leska.springapp.controller;

import com.leska.springapp.services.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class VacationController {

    private final VacationService vacationService;

    @Autowired
    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping("/calculate")
    public String hello(@RequestParam("salary") double salary,
                        @RequestParam(required = false, value = "vacationDays") Integer vacationDays,
                        @RequestParam(required = false, value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                        @RequestParam(required = false, value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                        Model model) {

        if (startDate != null && endDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            String formattedStartDate = startDate.format(formatter);
            String formattedEndDate = endDate.format(formatter);
            model.addAttribute("payOfPeriodDays", vacationService.getVacationPayOfPeriodDay(salary, startDate, endDate));
            model.addAttribute("startDate", formattedStartDate);
            model.addAttribute("endDate", formattedEndDate);
        } else {
            model.addAttribute("paySalary", vacationService.getVacationSalaryCalculate(salary, vacationDays));
            model.addAttribute("vacationDays", vacationDays);
        }
        return "calculate";
    }
}
