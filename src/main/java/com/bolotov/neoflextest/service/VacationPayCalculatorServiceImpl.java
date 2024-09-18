package com.bolotov.neoflextest.service;

import com.bolotov.neoflextest.util.HolidayChecker;
import de.jollyday.HolidayCalendar;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class VacationPayCalculatorServiceImpl implements VacationPayCalculatorService {

    @Override
    public BigDecimal calculateVacationPayByDays(BigDecimal averageAnnualPay, int vacationDays) {
        if (averageAnnualPay == null || averageAnnualPay.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Average annual pay must be greater than zero");
        }

        if (vacationDays <= 0) {
            throw new IllegalArgumentException("Vacation days must be greater than zero");
        }

        return averageAnnualPay
                .divide(BigDecimal.valueOf(AVERAGE_DAYS_MONTH), RoundingMode.FLOOR)
                .multiply(BigDecimal.valueOf(vacationDays));
    }

    @Override
    public BigDecimal calculateVacationPayByDate(BigDecimal averageAnnualPay,
                                                 LocalDate startDate, LocalDate endDate) {
        if (averageAnnualPay == null || averageAnnualPay.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Average annual pay must be greater than zero");
        }

        if (startDate == null || endDate == null || endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        HolidayChecker holidayChecker = new HolidayChecker(HolidayCalendar.RUSSIA);
        long vacationDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        long holidays = holidayChecker.getHolidaysBetweenDates(startDate, endDate);

        return averageAnnualPay
                .divide(BigDecimal.valueOf(AVERAGE_DAYS_MONTH), RoundingMode.FLOOR)
                .multiply(BigDecimal.valueOf(vacationDays - holidays));
    }
}
