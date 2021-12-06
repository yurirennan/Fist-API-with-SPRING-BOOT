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

    public void deleteStudents(Long studentId){
        boolean existStudent =  studentRepository.existsById(studentId);
        if(!existStudent){
            throw new IllegalStateException("Estudante com o id " + studentId + "Não existe!");
        }
        studentRepository.deleteById(studentId);
    }
}
