package com.spf.controller;


import com.spf.model.Student;
import com.spf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(path = "/add",method = RequestMethod.GET)
    @ResponseBody
    public Student addStudent(Student student){
        studentService.insertStudent(student);
        return student;
    }

    @RequestMapping("/getStudent/{id}")
    @ResponseBody
    public Student selectStudent(@PathVariable("id") Integer id){
        return studentService.selectStudent(id);
    }

    @RequestMapping(path = "/update")
    @ResponseBody
    public Student updateStudent(Student student){
        studentService.updateStudent(student);
        return student;
    }

}
