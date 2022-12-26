package com.example.scheduler.Other;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

public class StartEndWeek {

    public static ArrayList<String> StartENdWeek(){
        LocalDate today = LocalDate.now();
        Month month = LocalDate.now().getMonth();
        String monday = today.with(previousOrSame(MONDAY)).toString();
        String sunday = today.with(nextOrSame(SUNDAY)).toString();
        monday = monday.substring(monday.lastIndexOf('-') + 1);
        sunday = sunday.substring(sunday.lastIndexOf('-') + 1);
        return new ArrayList<String>(Arrays.asList(monday, sunday));

    }
}
