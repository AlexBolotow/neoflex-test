package com.bolotov.neoflextest.util;

import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;
import de.jollyday.ManagerParameters;

import java.time.LocalDate;

public class HolidayChecker {

    HolidayCalendar calendar;

    public HolidayChecker(HolidayCalendar calendar) {
        this.calendar = calendar;
    }

    public int getHolidaysBetweenDates(LocalDate startDate, LocalDate endDate) {
        HolidayManager holidayManager = HolidayManager.getInstance(ManagerParameters.create(HolidayCalendar.RUSSIA));

        return holidayManager.getHolidays(startDate, endDate).size();
    }
}
