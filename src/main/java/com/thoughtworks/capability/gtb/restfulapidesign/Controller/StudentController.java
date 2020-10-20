package com.thoughtworks.capability.gtb.restfulapidesign.Controller;

import com.thoughtworks.capability.gtb.restfulapidesign.Repository.StudentRepository;
import com.thoughtworks.capability.gtb.restfulapidesign.Service.StudentService;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
        Student newStudent = studentService.createStudent(student);
        return ResponseEntity.created(null).body(newStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable int id){
        studentRepository.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudent(@RequestParam(value = "gender", required = false) String gender){
        List<Student> studentList = new ArrayList<>();
        if(gender == null){
            studentList = studentRepository.findAll();
        }else {
            studentList = studentRepository.getStudentByGender(gender);
        }
        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") int id){
        Student student = studentRepository.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity updateStudent(@RequestBody Student student){
        Student updatedStudent = studentRepository.updateStudent(student);
        return ResponseEntity.ok(student);
    }

}
