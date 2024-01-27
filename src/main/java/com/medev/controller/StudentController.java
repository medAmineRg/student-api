package com.medev.controller;

import com.medev.entity.Student;
import com.medev.exception.NotFoundException;
import com.medev.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private static final Logger LOG = LoggerFactory.getLogger(StudentController.class);

    StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping()
    public List<Student> getStudent() {
        LOG.info("Start method get Student");
        List<Student> students = studentService.getStudent();
        LOG.info("End method get Student");
        return students;
    }

    @PostMapping()
    public Student saveStudent(@RequestBody Student student) {
        LOG.info("Start method saveStudent");
        Student savedStudent = studentService.saveStudent(student);
        LOG.info("End method saveStudent. Saved student: {}", savedStudent);
        return savedStudent;
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) throws NotFoundException {
        LOG.info("Start method updateStudent for student with ID {}", id);
        Student updatedStudent = studentService.updateStudent(id, student);
        LOG.info("End method updateStudent. Updated student: {}", updatedStudent);
        return updatedStudent;
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) throws NotFoundException {
        LOG.info("Start method deleteStudent for student with ID {}", id);
        studentService.deleteStudent(id);
        LOG.info("End method deleteStudent. Student with ID {} was deleted", id);
        return "Student was deleted";
    }
}
