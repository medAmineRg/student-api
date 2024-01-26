package com.medev.studentapi.service;

import com.medev.studentapi.entity.Student;
import com.medev.studentapi.exception.NotFoundException;

import java.util.List;

public interface StudentService {

    public List<Student> getStudent();

    public Student saveStudent(Student student);

    public Student updateStudent(Long id, Student student) throws NotFoundException;

    public void deleteStudent(Long id) throws NotFoundException;
}
