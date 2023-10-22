package com.fnatics.assistant.tables.service;

import com.fnatics.assistant.tables.Student;
import com.fnatics.assistant.tables.Subjects;
import com.fnatics.assistant.tables.repo.StudentRepo;
import com.fnatics.assistant.tables.repo.SubjectsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectsService {
    @Autowired
    private SubjectsRepo subjectsRepo;

    public List<Subjects> getAllSubjects(){
        return subjectsRepo.findAll();
    }
}

