package com.tsystems.javaschool.util;

import com.tsystems.javaschool.model.PrescriptionType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DateTimeUtil {
    public static final int DAYS_BEFORE_TOMORROW = 2;
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy");
    private static final String SPACE = " ";
    private static final String COLON = ":";
    public static final List<String> DAYS = Arrays.asList("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY");

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static List<LocalDateTime> timePatternToDates(String timePattern, int timePeriod, PrescriptionType type) {
        List<LocalDateTime> ldts = new ArrayList<>();

        if (type == PrescriptionType.TYPE_MEDICINE) {
            LocalDate currentDay = LocalDate.now().plusDays(1);
            String[] times = timePattern.split(SPACE);
            for (int i = 0; i < timePeriod; i++) {
                for (int j = 0; j < 3; j++) {
                    if (times[j].length() > 2) {
                        int hour = Integer.parseInt(times[j].substring(2).split(COLON)[0]);
                        int minute = Integer.parseInt(times[j].substring(2).split(COLON)[1]);
                        LocalDateTime ldt = currentDay.plusDays(i).atTime(hour, minute);
                        ldts.add(ldt);
                    }
                }
            }
        } else {
            LocalDate currentDay = LocalDate.now().plusDays(1);
            String[] days = timePattern.split(SPACE);
            int eventCount = 0;
            for (int i = 0; i < 7; i++) {
                if (days[i].length() > 2) {
                    eventCount += timePeriod;
                }
            }
            for (int i = eventCount; i > 0; ) {
                for (int j = 0; j < 7; j++) {
                    if (days[j].length() > 2) {
                        if (currentDay.getDayOfWeek().toString().equals(DAYS.get(j))) {
                            int hour = Integer.parseInt(days[j].substring(2).split(COLON)[0]);
                            int minute = Integer.parseInt(days[j].substring(2).split(COLON)[1]);
                            LocalDateTime ldt = currentDay.atTime(hour, minute);
                            ldts.add(ldt);
                            i--;
                            break;
                        }
                    }
                }
                currentDay = currentDay.plusDays(1);
            }
        }
        return ldts;
    }
}
