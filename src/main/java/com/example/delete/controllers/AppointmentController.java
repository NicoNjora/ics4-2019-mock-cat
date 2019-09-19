package com.example.delete.controllers;

import com.example.delete.InvalidInputException;
import com.example.delete.NotFoundException;
import com.example.delete.models.Appointment;
import com.example.delete.models.AppointmentDto;
import com.example.delete.models.Student;
import com.example.delete.models.Teacher;
import com.example.delete.repository.AppointmentRepository;
import com.example.delete.repository.StudentRepository;
import com.example.delete.repository.TeacherRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "appointments")
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public AppointmentController(AppointmentRepository appointmentRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.appointmentRepository = appointmentRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody AppointmentDto appointmentDto) {
        if (appointmentDto.getStudentId() == null ||
                appointmentDto.getTeacherId() == null) {
            throw new InvalidInputException("Teacher Id or Student Id not provided");
        }
        Student student = studentRepository.findById(appointmentDto.getStudentId())
                .orElseThrow(() -> new NotFoundException("Student not found"));

        Teacher teacher = teacherRepository.findById(appointmentDto.getTeacherId())
                .orElseThrow(() -> new NotFoundException("Lecturer not found"));

        Optional<Appointment> appointment = appointmentRepository.findByStudentIdAndTeacherId(
                appointmentDto.getStudentId(), appointmentDto.getTeacherId());
        if (appointment.isPresent()) {
            return appointment.get();
        } else {
            student.setScore(student.getScore() + 5);
            studentRepository.save(student);
            return appointmentRepository.save(new Appointment(student, teacher));
        }
    }

    @PatchMapping(value = "{id}")
    public Appointment confirmAppointment(@PathVariable Long id,
                                          @RequestParam Long studentId) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Appointment not found"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        appointment.setConfirmed(true);
        student.setScore(student.getScore() + 5);
        studentRepository.save(student);
        return appointmentRepository.save(appointment);
    }
}
