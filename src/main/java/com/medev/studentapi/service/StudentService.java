package com.medev.studentapi.service;

import com.medev.studentapi.entity.Student;

import java.util.List;

public interface StudentService {

    public List<Student> getStudent();

    public Student saveStudent(Student student);

    public Student updateStudent(Long id, Student student);

    public Student deleteStudent(Long id);
}
