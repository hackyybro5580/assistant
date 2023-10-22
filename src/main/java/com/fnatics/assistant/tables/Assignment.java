package com.fnatics.assistant.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@Table(name = "assignment")
@NoArgsConstructor
@AllArgsConstructor
public class Assignment {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "student_id")
    private int student_id;

    @Column(name = "sub_code")
    private int sub_code;

    @Column(name = "name")
    private String name;

    @Column(name = "marks")
    private int marks;

    @Column(name = "start_date")
    private Date start_date;

    @Column(name = "end_date")
    private Date end_date;

}
