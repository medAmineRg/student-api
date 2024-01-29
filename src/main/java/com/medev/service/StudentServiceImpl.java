package com.medev.service;

import com.medev.dto.StudentDto;
import com.medev.entity.Student;
import com.medev.exception.NotFoundException;
import com.medev.mapper.StudentMapper;
import com.medev.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);


    public StudentServiceImpl(StudentRepository studentDtoRepository) {
        this.studentRepository = studentDtoRepository;
    }
    
    @Override
    public Page<StudentDto> getStudent(int page, int size) {
        return studentMapper.studentEntityToDtoPage(studentRepository.findAll(PageRequest.of(page, size)));
    }

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
        return studentMapper.studentEntityToDto(studentRepository.save(studentMapper.studentDtoToEntity(studentDto)));
    }

    @Override
    @Transactional
    public StudentDto updateStudent(Long id, StudentDto studentDto) throws NotFoundException {

        Student foundStudent = studentRepository.findById(id).orElseThrow(()-> new NotFoundException("Student wasn't found!"));

        if(!Objects.equals(studentDto.getFirstName(), foundStudent.getFirstName())) {
            foundStudent.setFirstName(studentDto.getFirstName());
        }

        if(!Objects.equals(studentDto.getLastName(), foundStudent.getLastName())) {
            foundStudent.setLastName(studentDto.getLastName());
        }

        if(!Objects.equals(studentDto.getPhone(), foundStudent.getPhone())) {
            foundStudent.setPhone(studentDto.getPhone());
        }

        return studentMapper.studentEntityToDto(studentRepository.save(foundStudent));
    }

    @Override
    public void deleteStudent(Long id) throws NotFoundException {
        Student studentDto = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student wasn't found"));
        studentRepository.delete(studentDto);
    }
}
