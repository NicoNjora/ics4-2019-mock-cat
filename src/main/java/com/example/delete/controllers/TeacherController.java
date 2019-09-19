package com.example.delete.controllers;

import com.example.delete.models.Teacher;
import com.example.delete.repository.TeacherRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "lecturers")
public class TeacherController {

    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping
    public List<Teacher>getAllTeachers(){
        return teacherRepository.findAll();
    }
}
