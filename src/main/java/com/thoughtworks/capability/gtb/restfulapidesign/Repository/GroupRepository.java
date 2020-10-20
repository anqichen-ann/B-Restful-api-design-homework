package com.thoughtworks.capability.gtb.restfulapidesign.Repository;

import com.thoughtworks.capability.gtb.restfulapidesign.Service.StudentService;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.Group;
import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class GroupRepository {
    private final int GROUPNUMBER = 6;

    @Autowired
    StudentService studentService;

    private  final StudentRepository studentRepository;

    public GroupRepository(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    private List<Group> groupList = new ArrayList<>();

    public List<Group> findAll() {
        return groupList;
    }

    public Group findById(int id){
        return groupList.get(id);
    }

    public Group updateGroupName(int id, String name){
        id = id - 1;
        Group desGroup = findById(id);
        desGroup.setName(name);
        groupList.remove(id);
        groupList.add(id, desGroup);
        return findById(id);
    }

    public List<Group> studentGroup(List<Student> studentList){
        Collections.shuffle(studentList);
        int length = studentList.size() / GROUPNUMBER;
        int left = studentList.size() % GROUPNUMBER;
        int index = 0;
        for(int i=0; i<GROUPNUMBER; i++){
            Group group = new Group();
            List list = new ArrayList();
            String groupName = (i+1) + "组";
            if(index<left){
                // GTB: 代码可读性低
                list.add(studentList.subList(i*(length+1),(i+1)*(length+1)));
                index++;
            }else {
                list.add(studentList.subList(i*length+left,(i+1)*length+left));
            }
            group.setId(i+1);
            group.setName(groupName);
            group.setStudentList(list);
            groupList.add(group);
        }
        return groupList;
    }
}
