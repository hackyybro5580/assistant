package com.fnatics.assistant.tables;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "firstname")
    private String firstName;
}
