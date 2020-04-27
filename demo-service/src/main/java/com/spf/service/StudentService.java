package com.spf.service;


import com.spf.model.Student;

public interface StudentService {

    public int insertStudent(Student student);

    public Student selectStudent(Integer id);

    public Student updateStudent(Student student);
}
