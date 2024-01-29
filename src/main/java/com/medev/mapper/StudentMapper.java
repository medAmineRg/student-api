package com.medev.mapper;

import com.medev.dto.StudentDto;
import com.medev.entity.Student;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<StudentDto> studentListEntityToDto(List<Student> students);

    StudentDto studentEntityToDto(Student student);

    Student studentDtoToEntity(StudentDto studentDto);

    default Page<StudentDto> studentEntityToDtoPage(Page<Student> studentPage) {
        return studentPage.map(this::studentEntityToDto);
    }
}
