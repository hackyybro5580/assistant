package com.fnatics.assistant.tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name = "subjects")
@NoArgsConstructor
@AllArgsConstructor
public class Subjects {
    @Id
    @Column(name = "SUB_CODE")
    private int id;

    @Column(name = "NAME")
    private String name;
}
