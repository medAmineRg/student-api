package com.medev.studentapi.service;

import com.medev.studentapi.entity.Student;
import com.medev.studentapi.exception.NotFoundException;

import java.util.List;

public interface StudentService {

    List<Student> getStudent();

    Student saveStudent(Student student);

    Student updateStudent(Long id, Student student) throws NotFoundException;

    void deleteStudent(Long id) throws NotFoundException;
}
