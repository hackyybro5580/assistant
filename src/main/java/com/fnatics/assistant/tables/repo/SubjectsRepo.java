package com.fnatics.assistant.tables.repo;

import com.fnatics.assistant.tables.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface SubjectsRepo extends JpaRepository<Subjects, Integer> {
}
