package com.medev.service;

import com.medev.entity.Student;
import com.medev.exception.NotFoundException;
import com.medev.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudent() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public Student updateStudent(Long id, Student student) throws NotFoundException {

        Student foundStudent = studentRepository.findById(id).orElseThrow(()-> new NotFoundException("Student wasn't found!"));

        if(!Objects.equals(student.getFirstName(), foundStudent.getFirstName())) {
            foundStudent.setFirstName(student.getFirstName());
        }

        if(!Objects.equals(student.getLastName(), foundStudent.getLastName())) {
            foundStudent.setLastName(student.getLastName());
        }

        if(!Objects.equals(student.getPhone(), foundStudent.getPhone())) {
            foundStudent.setPhone(student.getPhone());
        }

        return studentRepository.save(foundStudent);
    }

    @Override
    public void deleteStudent(Long id) throws NotFoundException {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student wasn't found"));
        studentRepository.delete(student);
    }
}
