package com.example.crud;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Create
    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDTO, student);
        Student savedStudent = studentRepository.save(student);
        StudentDTO savedDTO = new StudentDTO();
        BeanUtils.copyProperties(savedStudent, savedDTO);
        return ResponseEntity.ok(savedDTO);
    }

    // Read all
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> studentDTOs = studentRepository.findAll().stream()
            .map(student -> {
                StudentDTO dto = new StudentDTO();
                BeanUtils.copyProperties(student, dto);
                return dto;
            })
            .collect(Collectors.toList());
        return ResponseEntity.ok(studentDTOs);
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id)
            .map(student -> {
                StudentDTO dto = new StudentDTO();
                BeanUtils.copyProperties(student, dto);
                return ResponseEntity.ok(dto);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        return studentRepository.findById(id)
            .map(student -> {
                BeanUtils.copyProperties(studentDTO, student, "id");
                Student updatedStudent = studentRepository.save(student);
                StudentDTO updatedDTO = new StudentDTO();
                BeanUtils.copyProperties(updatedStudent, updatedDTO);
                return ResponseEntity.ok(updatedDTO);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}