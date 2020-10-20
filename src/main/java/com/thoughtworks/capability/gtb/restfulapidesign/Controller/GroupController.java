package com.thoughtworks.capability.gtb.restfulapidesign.Controller;

import com.thoughtworks.capability.gtb.restfulapidesign.Repository.GroupRepository;
import com.thoughtworks.capability.gtb.restfulapidesign.Repository.StudentRepository;
import com.thoughtworks.capability.gtb.restfulapidesign.Service.StudentService;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    StudentRepository studentRepository;

    @PutMapping
    public ResponseEntity updateGroupName(@RequestParam("id") int id, @RequestParam("name") String name){
        Group group = groupRepository.updateGroupName(id, name);
        return ResponseEntity.ok(group);
    }

    @GetMapping
    public ResponseEntity<List<Group>> groupStudent(){
        List<Group> groupList = groupRepository.studentGroup(studentRepository.findAll());
        return ResponseEntity.ok(groupList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Student>> getGroupStudentList(@PathVariable int id){
        List<Student> studentList = groupRepository.findById(id).getStudentList();
        return ResponseEntity.ok(studentList);
    }

}
