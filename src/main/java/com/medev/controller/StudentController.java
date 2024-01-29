package com.medev.controller;

import com.medev.dto.StudentDto;
import com.medev.entity.Student;
import com.medev.exception.NotFoundException;
import com.medev.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/student")
public class StudentController {

    private final StudentService studentService;
    private static final Logger LOG = LoggerFactory.getLogger(StudentController.class);

    StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping()
    public Page<StudentDto> getStudent(@RequestParam(name ="start", required = false) Integer start, @RequestParam(name="size", required = false) Integer size) {
        LOG.info("Start method get Student");
        int st = (start != null) ? start : 0;
        int sz = (size != null) ? size : 10;
        Page<StudentDto> students = studentService.getStudent(st, sz);
        LOG.info("End method get Student");
        return students;
    }

    @PostMapping()
    public StudentDto saveStudent(@RequestBody StudentDto studentDto) {
        LOG.info("Start method saveStudent");
        StudentDto savedStudent = studentService.saveStudent(studentDto);
        LOG.info("End method saveStudent. Saved student: {}", savedStudent);
        return savedStudent;
    }

    @PutMapping("/{id}")
    public StudentDto updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) throws NotFoundException {
        LOG.info("Start method updateStudent for student with ID {}", id);
        StudentDto updatedStudent = studentService.updateStudent(id, studentDto);
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
