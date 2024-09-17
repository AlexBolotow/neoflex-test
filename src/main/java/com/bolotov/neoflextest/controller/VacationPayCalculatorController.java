package com.bolotov.neoflextest.controller;

import com.bolotov.neoflextest.dto.VacationPayDTO;
import com.bolotov.neoflextest.service.VacationPayCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("vacation/pay-calculator")
public class VacationPayCalculatorController {


    private final VacationPayCalculatorService vacationPayCalculatorService;

    @GetMapping("/calculate-by-days")
    public ResponseEntity<VacationPayDTO> getVacationPayByDays(
            @RequestParam(name = "average_annual_pay") BigDecimal averageAnnualPay,
            @RequestParam(name = "vacation_days") Integer vacationDays) {

        BigDecimal pay = vacationPayCalculatorService.calculateVacationPayByDays(averageAnnualPay, vacationDays);
        return ResponseEntity.ok(VacationPayDTO.builder()
                .pay(pay)
                .build());
    }

    @GetMapping("/calculate-by-date")
    public ResponseEntity<VacationPayDTO> getVacationPayByDate(
            @RequestParam(name = "average_annual_pay") BigDecimal averageAnnualPay,
            @RequestParam(name = "start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(name = "end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {

        BigDecimal pay = vacationPayCalculatorService.calculateVacationPayByDate(averageAnnualPay, startDate, endDate);
        return ResponseEntity.ok(VacationPayDTO.builder()
                .pay(pay)
                .build());
    }


}
