package com.example.delete;

import com.example.delete.models.Student;
import com.example.delete.models.Teacher;
import com.example.delete.repository.StudentRepository;
import com.example.delete.repository.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DummyData implements CommandLineRunner {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public DummyData(TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(teacherRepository.findAll().isEmpty()) {
            teacherRepository.save(new Teacher("Dr Khakata"));
            teacherRepository.save(new Teacher("Dr Henry"));
            teacherRepository.save(new Teacher("Dr Matiangi"));
        }
        if(studentRepository.findAll().isEmpty()) {
            studentRepository.save(new Student("1234", "Amukoye"));
        }
    }
}
