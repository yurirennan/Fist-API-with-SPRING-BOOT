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

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addStudents(Student student){
        Optional<Student> findStudent = studentRepository.findStudentByEmail(student.getEmail());

        if(findStudent.isPresent()){
            throw new IllegalStateException("O email já está cadastrado");
        }

        studentRepository.save(student);
    }

    public void deleteStudents(Long studentId){
        boolean existStudent =  studentRepository.existsById(studentId);
        if(!existStudent){
            throw new IllegalStateException("Estudante com o id " + studentId + "Não existe!");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email){
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
                "Estudante com o id " + studentId + "não existe!"
        ));

        if(name != null && name.length() >= 3 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if(email != null && email.length() > 3 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

            if(studentOptional.isPresent()){
                throw new IllegalStateException("Email já cadastrado");
            }
            student.setEmail(email);
        }
    }
}
