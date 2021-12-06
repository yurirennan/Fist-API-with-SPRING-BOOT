package com.example.Projeto.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student yuri = new Student("Yuri", "yuri@gmail.com", LocalDate.of(2005, Month.FEBRUARY, 18));

            Student rennan = new Student("Rennan", "rennan@gmail.com", LocalDate.of(2006, Month.FEBRUARY, 18));

            Student erlon = new Student("Erlon", "erlon@gmail.com", LocalDate.of(2006, Month.FEBRUARY, 18));

            studentRepository.saveAll(List.of(yuri,rennan, erlon));
        };
    }
}
