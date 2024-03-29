package com.medev.controller;

import com.medev.dto.StudentDto;
import com.medev.exception.NotFoundException;
import com.medev.service.StudentService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/student")
@Tag(name = "student", description = "This endpoint will help interact with the student controller")
public class StudentController {

    private final StudentService studentService;
    // private final StudentSearchDao studentSearchDao;
    private static final Logger LOG = LoggerFactory.getLogger(StudentController.class);

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Operation(
            summary = "Fetch all students",
            description = "fetches all student entities and their data from DB")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
    })
    @GetMapping()
    public Page<StudentDto> getStudent(@RequestParam(name ="start", required = false) Integer start, @RequestParam(name="size", required = false) Integer size) {
        LOG.info("Start method get Student");
        int st = (start != null) ? start : 0;
        int sz = (size != null) ? size : 10;
        Page<StudentDto> students = studentService.getStudent(st, sz);
        LOG.info("End method get Student");
        return students;
    }

    @Operation(
            summary = "Fetch student by id",
            description = "fetches a specific student by id")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "User not found"),
    })
    @GetMapping("/{id}")
    public StudentDto getStudentById(@Parameter(required = true, name = "id", description = "Student ID") @PathVariable Long id) throws NotFoundException {
        LOG.info("Start method get Student by id");
        StudentDto foundStudent = studentService.getStudentById(id);
        LOG.info("End method get Student by id");
        return foundStudent;
    }

    @Hidden
    @GetMapping("/filter")
    public Page<StudentDto> getFilteredStudent(@RequestParam(name ="firstName", required = false) String firstName, @RequestParam(name="lastName", required = false) String lastName, @RequestParam(name="phone", required = false) String phone, @RequestParam(name ="start", required = false) Integer start, @RequestParam(name="size", required = false) Integer size) {
        LOG.info("Start method get Filtred Student");
        Page<StudentDto> students = studentService.getFilteredStudent(firstName, lastName, phone, start, size);
        LOG.info("End method get Filtred Student");
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
