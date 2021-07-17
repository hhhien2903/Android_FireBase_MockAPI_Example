package com.example.a8_18050261_hoanghuuhien;

import java.io.Serializable;

public class Student implements Serializable {
    private String id;
    private String fullName;
    private String className;
    private String status;
    private String workingName;

    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWorkingName() {
        return workingName;
    }

    public void setWorkingName(String workingName) {
        this.workingName = workingName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", className='" + className + '\'' +
                ", status='" + status + '\'' +
                ", workingName='" + workingName + '\'' +
                '}';
    }
}
