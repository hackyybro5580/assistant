package com.fnatics.assistant.rest;

import com.fnatics.assistant.tables.Assignment;
import com.fnatics.assistant.tables.Takes;
import com.fnatics.assistant.tables.service.AssignmentService;
import com.fnatics.assistant.tables.service.TakesService;
import com.fnatics.assistant.tables.service.StudentService;
import com.fnatics.assistant.tables.service.SubjectsService;
import com.fnatics.assistant.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class ApiEndPoints {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectsService subjectsService;

    @Autowired
    private TakesService takesService;

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping("/getAllTakes")
    public List<Takes> getAllTakes(){
        return takesService.getAllTakes();
    }

    @GetMapping("/getMyTakes")
    public List<Takes> getMyTakes(@RequestParam String student_id){
        return takesService.getMyTakes(student_id, null);
    }

    @GetMapping("/getMyGrades")
    public List<Takes> getMyGrades(@RequestParam String student_id, @RequestParam String sub_code){
        return takesService.getMyTakes(student_id, sub_code);
    }


    //Below method will list all the assignments that is due in the mentioned week
    // week - 0 (Current Week)
    // week - 1 (Next Week) 2,3,...
    // week - (-1) Previous Week -2,-3,..
    @GetMapping("/getMyAssignmentDues")
    public List<Assignment> getMyAssignmentDues(@RequestParam String student_id, @RequestParam String sub_code, @RequestParam String week)
    {
        List<Date> dates = new Utils().getFirstAndLastDatesOfWeek(week);
        return assignmentService.getMyAssignmentDues(dates.get(0), dates.get(1));
    }

}
