package com.thoughtworks.capability.gtb.restfulapidesign.Repository;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.Student;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class StudentRepository {
    private final String[] STUDENTNAME = new String[]{"成吉思汗", "鲁班七号", "太乙真人", "钟无艳",
            "花木兰", "雅典娜", "芈月", "白起", "刘禅", "庄周", "马超", "刘备",
            "哪吒", "大乔", "蔡文姬"};
    private List<Student> studentList = new ArrayList<>();

    public StudentRepository(){
        for(int i = 0; i < STUDENTNAME.length; i++) {
            int length = studentList.size();
            String gender;
            if(i%2 == 0){
                gender = "female";
            }else {
                gender = "male";
            }
            Student newStudent = new Student(length+1, STUDENTNAME[i], gender,"");
            studentList.add(newStudent);
        }
    }

    public List<Student> findAll(){
        return studentList;
    }

    public void addStudent(Student student){
        studentList.add(student);
    }

    public int getStudentLength(){
        return studentList.size();
    }

    public void deleteStudent(int id){
        studentList.remove(id-1);
    }

    public List<Student> getStudentByGender(String gender){
        List<Student> result = new ArrayList<>();
        for(Student stu: studentList){
            if(stu.getGender().equals(gender))
                result.add(stu);
        }
        return result;
    }

    public Student getStudentById(int id){
        return studentList.get(id-1);
    }

    public Student updateStudent(Student student){
        int id = student.getId();
        Student destStudent = getStudentById(id);
        if(student.getName()!=null){
            destStudent.setName(student.getName());
        }
        if(student.getGender()!=null){
            destStudent.setGender(student.getGender());
        }
        if(student.getNote()!=null){
            destStudent.setNote(student.getNote());
        }
        studentList.remove(id);
        studentList.add(id, destStudent);
        return destStudent;
    }
}
