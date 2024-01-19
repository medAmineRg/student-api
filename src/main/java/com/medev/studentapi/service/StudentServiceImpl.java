package com.medev.studentapi.service;

import com.medev.studentapi.entity.Student;
import com.medev.studentapi.repository.StudentRepository;
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
    public Student updateStudent(Long id, Student student) {

        Student foundStudent = studentRepository.findById(id).get();

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
    public Student deleteStudent(Long id) {
        Student deletedStudent = studentRepository.findById(id).get();
        studentRepository.deleteById(id);
        return deletedStudent;
    }
}
