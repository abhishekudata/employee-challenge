package com.example.rqchallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {

    public final static String NAME= "name";
    public final static String SALARY= "salary";
    public final static String AGE= "age";
    public final static String PROFILE_IMAGE= "profileImage";

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("employee_name")
    private String name;

    @JsonProperty("employee_salary")
    private Integer salary;

    @JsonProperty("employee_age")
    private Integer age;

    @JsonProperty("profile_image")
    private Integer profileImage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Integer profileImage) {
        this.profileImage = profileImage;
    }
}
