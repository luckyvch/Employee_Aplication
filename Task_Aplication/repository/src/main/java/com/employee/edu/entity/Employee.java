package com.employee.edu.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "empID")
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empID;

    @Column
    private String empName;

    @Column(columnDefinition = "boolean default true")
    private boolean empActive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dpID")
    private Department department;
}
