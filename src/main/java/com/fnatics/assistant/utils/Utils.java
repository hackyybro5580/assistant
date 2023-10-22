package com.fnatics.assistant.utils;

import com.fnatics.assistant.tables.Assignment;
import com.fnatics.assistant.tables.Takes;
import com.fnatics.assistant.tables.service.AssignmentService;
import com.fnatics.assistant.tables.service.TakesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.*;

public class Utils {
    @Autowired
    private TakesService takesService;

    @Autowired
    private AssignmentService assignmentService;

    private final static ZoneId TZ = ZoneId.of("America/Toronto");

    public int getMyGrades(String studentId, String sub_code, String week){

        List<Takes> takes = takesService.getMyTakes(studentId, sub_code);

        return 0;
    }

    public List<Assignment> getMyAssignmentDues(String studentId, String sub_code, String week){

        List<Date> dates = getFirstAndLastDatesOfWeek(week);
        return  assignmentService.getMyAssignmentDues(dates.get(0), dates.get(1));
    }

    public static List<Date> getFirstAndLastDatesOfWeek(String week){
        DayOfWeek firstDayOfWeek = DayOfWeek.SUNDAY;
        DayOfWeek lastDayOfWeek = DayOfWeek.SATURDAY;

        Calendar cal = Calendar.getInstance();

        int weekInt = Integer.parseInt(week);

        cal.add(Calendar.DATE, weekInt == 0 ? 0 : weekInt < 0 ? -(Math.abs(weekInt) * 7) : weekInt * 7);
        /*if(weekInt < 0){
            cal.add(Calendar.DATE, weekInt == 0 ? 0 : weekInt < 0 ? -(Math.abs(weekInt) * 7) : weekInt * 7);
        }else{
            cal.add(Calendar.DATE, 0);
        }*/

        Date firstDateOfPreviousWeek = cal.getTime();
        LocalDate fdw = firstDateOfPreviousWeek.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().with(TemporalAdjusters.previousOrSame(firstDayOfWeek));
        firstDateOfPreviousWeek = Date.from(fdw.atStartOfDay(ZoneId.systemDefault()).toInstant());

        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, weekInt == 0 ? 0 : weekInt < 0 ? -(Math.abs(weekInt) * 7) : weekInt * 7);
        /*if(weekInt > 0){
            cal.add(Calendar.DATE, weekInt == 0 ? 0 : weekInt < 0 ? -(Math.abs(weekInt) * 7) : weekInt * 7);
        }else{
            cal.add(Calendar.DATE, 0);
        }*/
        Date lastDateOfPreviousWeek = cal.getTime();
        LocalDate ldw = lastDateOfPreviousWeek.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().with(TemporalAdjusters.nextOrSame(lastDayOfWeek));
        lastDateOfPreviousWeek = Date.from(ldw.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Date> dates = new ArrayList<>();
        try {
            dates.add(java.sql.Date.valueOf(fdw));
            dates.add(java.sql.Date.valueOf(ldw));
        }catch(Exception e){

        }
        return dates;
    }
}
