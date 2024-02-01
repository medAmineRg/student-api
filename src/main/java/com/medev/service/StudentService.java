package com.medev.service;

import com.medev.dto.StudentDto;
import com.medev.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;


public interface StudentService {

    Page<StudentDto> getStudent(int start, int size);
    StudentDto getStudentById(Long id) throws NotFoundException;
    Page<StudentDto> getFilteredStudent(String firstName, String lastName, String phone, int start, int size);

    StudentDto saveStudent(StudentDto student);

    StudentDto updateStudent(Long id, StudentDto studentDto) throws NotFoundException;

    void deleteStudent(Long id) throws NotFoundException;
}
