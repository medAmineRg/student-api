package com.medev.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medev.dto.StudentDto;
import com.medev.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentService studentService;


    @Test
    public void testGetAllStudent() throws Exception {

        // Performing the GET request and validating the response
        mockMvc.perform(get("/v1/student"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveStudent() throws Exception {

        StudentDto studentDto = new StudentDto(1L, "John", "Doe", "0612345678");

        // Mock the service to return the same student
        when(studentService.saveStudent(any(StudentDto.class))).thenReturn(studentDto);

        // Perform the HTTP request and verify the response
        mockMvc.perform(post("/v1/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(studentDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.phone").value("0612345678"));
    }

    @Test
    public void testUpdateStudent() throws Exception{

        // Given
        Long studentId = 1L;
        StudentDto updatedStudent = new StudentDto(1L, "Amine", "Rguig", "0632996002");

        // Mocking the service method
        when(studentService.updateStudent(eq(studentId), ArgumentMatchers.any(StudentDto.class)))
                .thenReturn(updatedStudent);

        // Creating a mock MVC instance
        mockMvc.perform(put("/v1/student/{id}", studentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedStudent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(studentId))
                .andExpect(jsonPath("$.firstName").value("Amine"))
                .andExpect(jsonPath("$.lastName").value("Rguig"))
                .andExpect(jsonPath("$.phone").value("0632996002"))
                .andDo(print());

        verify(studentService, times(1)).updateStudent(1L, updatedStudent);
    }

    @Test
    public void testDeleteStudent() throws Exception {
        // Mock the service method to do nothing when deleteStudent is called
        doNothing().when(studentService).deleteStudent(1L);

        // Perform the DELETE request and verify the response
        mockMvc.perform(delete("/v1/student/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(studentService, times(1)).deleteStudent(1L);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}