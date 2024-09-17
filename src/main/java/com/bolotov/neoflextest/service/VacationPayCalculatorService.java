package com.bolotov.neoflextest.service;


import java.math.BigDecimal;
import java.time.LocalDate;

public interface VacationPayCalculatorService {

    double AVERAGE_DAYS_MONTH = 29.3;

    BigDecimal calculateVacationPayByDays(BigDecimal averageAnnualPay, int vacationDays);

    BigDecimal calculateVacationPayByDate(BigDecimal averageAnnualPay, LocalDate startDateVacation, LocalDate endDateVacation);
}
