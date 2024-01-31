package com.medev.service;

import com.medev.dto.StudentDto;
import com.medev.exception.NotFoundException;
import org.springframework.data.domain.Page;


public interface StudentService {

    Page<StudentDto> getStudent(int start, int size);
    Page<StudentDto> getFilteredStudent(String firstName, String lastName, String phone, int start, int size);

    StudentDto saveStudent(StudentDto student);

    StudentDto updateStudent(Long id, StudentDto studentDto) throws NotFoundException;

    void deleteStudent(Long id) throws NotFoundException;
}
