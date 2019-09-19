package com.example.delete.repository;

import com.example.delete.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findByStudentIdAndTeacherId(Long studentId, Long teacherId);
}
