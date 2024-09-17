package com.bolotov.neoflextest.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VacationPayCalculatorServiceImplTest {

    @Autowired
    private VacationPayCalculatorService vacationPayCalculatorService;

    @Test
    void calculateVacationPayByDays() {
        //ТЖ 95536
        BigDecimal salary = BigDecimal.valueOf(100_000);
        BigDecimal expected = BigDecimal.valueOf(95_536);

        BigDecimal actual = vacationPayCalculatorService
                .calculateVacationPayByDays(salary, 28);

        assertEquals(expected, actual);
    }

    @Test
    void calculateVacationPayByDate_October() {
        //ТЖ 95536
        BigDecimal salary = BigDecimal.valueOf(100_000);
        BigDecimal expected = BigDecimal.valueOf(95_536);
        LocalDate startDate = LocalDate.of(2024, 10, 1);
        LocalDate endDate = LocalDate.of(2024, 10, 28);

        BigDecimal actual = vacationPayCalculatorService
                .calculateVacationPayByDate(salary, startDate, endDate);

        assertEquals(expected, actual);
    }

    @Test
    void calculateVacationPayByDate_DecemberJanuary() {
        //ТЖ 68_240
        BigDecimal salary = BigDecimal.valueOf(100_000);
        BigDecimal expected = BigDecimal.valueOf(68_240);
        LocalDate startDate = LocalDate.of(2024, 12, 20);
        LocalDate endDate = LocalDate.of(2025, 1, 16);

        BigDecimal actual = vacationPayCalculatorService
                .calculateVacationPayByDate(salary, startDate, endDate);

        assertEquals(expected, actual);
    }

    @Test
    void calculateVacationPayByDate_May() {
        //ТЖ 37_532
        BigDecimal salary = BigDecimal.valueOf(100_000);
        BigDecimal expected = BigDecimal.valueOf(37_532);
        LocalDate startDate = LocalDate.of(2025, 5, 3);
        LocalDate endDate = LocalDate.of(2025, 5, 14);

        BigDecimal actual = vacationPayCalculatorService
                .calculateVacationPayByDate(salary, startDate, endDate);

        assertEquals(expected, actual);
    }


}