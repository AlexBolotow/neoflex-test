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
        return averageAnnualPay
                .divide(BigDecimal.valueOf(AVERAGE_DAYS_MONTH), RoundingMode.FLOOR)
                .multiply(BigDecimal.valueOf(vacationDays));
    }

    @Override
    public BigDecimal calculateVacationPayByDate(BigDecimal averageAnnualPay,
                                                 LocalDate startDateVacation, LocalDate endDateVacation) {
        HolidayChecker holidayChecker = new HolidayChecker(HolidayCalendar.RUSSIA);
        long vacationDays = ChronoUnit.DAYS.between(startDateVacation, endDateVacation) + 1;
        long holidays = holidayChecker.getHolidaysBetweenDates(startDateVacation, endDateVacation);

        return averageAnnualPay
                .divide(BigDecimal.valueOf(AVERAGE_DAYS_MONTH), RoundingMode.FLOOR)
                .multiply(BigDecimal.valueOf(vacationDays - holidays));
    }
}
