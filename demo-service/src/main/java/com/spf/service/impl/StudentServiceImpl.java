package com.spf.service.impl;


import com.spf.mapper.StudentMapper;
import com.spf.model.Student;
import com.spf.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentServiceImpl implements StudentService {


//    @Autowired
//    RedisTemplate redisTemplate;

    @Autowired(required = false)
    StudentMapper studentMapper;

    @Override
    public int insertStudent(Student student) {
        return studentMapper.insertSelective(student);
    }

    @Override
    @Cacheable(value = "student", key = "#id")
    public Student selectStudent(Integer id) {
//        Student student = (Student) redisTemplate.opsForList().leftPop(id);
//        if (student == null){
            System.out.println("查询id为"+ id +"的员工" );
            Student student = studentMapper.selectByPrimaryKey(id);
//          redisTemplate.opsForList().rightPush(id,student);
//        }
        return student;
    }

    @Override
    @CachePut(value = "student", key = "#result.id")
    public Student updateStudent(Student student) {
        studentMapper.updateByPrimaryKey(student);
        return student;
    }
}
