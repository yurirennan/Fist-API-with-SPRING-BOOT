package com.example.Projeto.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudents(Student student){
        Optional<Student> findStudent = studentRepository.findStudentByEmail(student.getEmail());

        if(findStudent.isPresent()){
            throw new IllegalStateException("O email já está cadastrado");
        }

        studentRepository.save(student);
    }

}
