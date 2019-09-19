package com.example.delete.controllers;

import com.example.delete.InvalidInputException;
import com.example.delete.NotFoundException;
import com.example.delete.models.Student;
import com.example.delete.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public Student register(@RequestBody Student student){
        if(student.getFirstName() == null ||
                student.getStudentNumber() == null)
            throw new InvalidInputException("Either student name or number is missing. Check your input.");

        Optional<Student> s = studentRepository.findByStudentNumber(student.getStudentNumber());
        if(!s.isPresent()){
            student.setScore(5);
            return studentRepository.save(student);
        }else
            return s.get();
    }

    @GetMapping
    public Student findByStudentNumber(@RequestParam String studentNumber){
        return studentRepository.findByStudentNumber(studentNumber)
                .orElseThrow(()-> new NotFoundException("Student not found"));
    }


}
