package com.fnatics.assistant.tables.service;

import com.fnatics.assistant.tables.Student;
import com.fnatics.assistant.tables.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public List<Student> getAllStudents(){
        return studentRepo.findAll();
    }
}
