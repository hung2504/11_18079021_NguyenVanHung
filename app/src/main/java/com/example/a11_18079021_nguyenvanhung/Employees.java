package com.example.a11_18079021_nguyenvanhung;

import java.io.Serializable;

public class Employees implements Serializable {
    private int id;
    private String employeeName;
    private int age;
    private String department;

    public Employees() {
    }

    public Employees(int id, String employeeName, int age, String department) {
        this.id = id;
        this.employeeName = employeeName;
        this.age = age;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
