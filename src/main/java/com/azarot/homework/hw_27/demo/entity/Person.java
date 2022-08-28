package com.azarot.homework.hw_27.demo.entity;

import com.azarot.homework.hw_27.annotation.Column;
import com.azarot.homework.hw_27.annotation.Id;
import com.azarot.homework.hw_27.annotation.Table;

import lombok.Data;

@Data
@Table(name = "persons")
public class Person {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
