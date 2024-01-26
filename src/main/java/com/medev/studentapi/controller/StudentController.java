package com.medev.studentapi.controller;

import com.medev.studentapi.entity.Student;
import com.medev.studentapi.exception.NotFoundException;
import com.medev.studentapi.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping()
    public List<Student> getStudent() {
        return studentService.getStudent();
    }

    @PostMapping()
    public Student saveStudent(@RequestBody Student student) {

        return studentService.saveStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) throws NotFoundException {

        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) throws NotFoundException {
        studentService.deleteStudent(id);
        return "Student was deleted";
    }
}
