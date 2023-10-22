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
@Table(name = "takes")
@NoArgsConstructor
@AllArgsConstructor
public class Takes {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "student_id")
    private int student_id;

    @Column(name = "sub_code")
    private int sub_code;
}
