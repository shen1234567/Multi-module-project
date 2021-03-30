package com.spf.service.impl;


import com.spf.mapper.StudentMapper;
import com.spf.model.Student;
import com.spf.service.StudentService;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("studentService")
public class StudentServiceImpl implements StudentService {


    @Autowired
    RedisTemplate redisTemplate;

    @Resource
    StudentMapper studentMapper;

    @Autowired
    RedissonClient redissonClient;

    @Override
    public int insertStudent(Student student) {
        return studentMapper.insertSelective(student);
    }

    @Override
//    @Cacheable(value = "student", key = "#id")
    public Student selectStudent(Integer id) {

        Student student = (Student) redisTemplate.opsForHash().get("student",id);
        if (student == null) {
            System.out.println("查询id为" + id + "的员工");
            student = studentMapper.selectByPrimaryKey(id);
//            redisTemplate.opsForList().rightPush(id, student);
//            redisTemplate.opsForValue().set("student_" + id,student);
            redisTemplate.opsForHash().put("student",id,student);
        }
        System.out.println(redisTemplate.opsForValue().setIfAbsent("student_" + id, "setifabsent"));
        System.out.println(redisTemplate.opsForValue().setIfPresent("student_" + id, "setifabsent"));
        return student;
    }

    @Override
//    @CachePut(value = "student", key = "#result.id")
    public Student updateStudent(Student student) {
        studentMapper.updateByPrimaryKey(student);
        redisTemplate.opsForValue().set("student_" + student.getId(),student);
        return student;
    }




  /*  public static void main(String[] args) {
        String Json = "{ \"v\":\"1.0\", \"ins\":\"M0000001\", \"id\":\"2014120318291234567890888888\", \"req\":{ \"cmd\":\"QryIndInfo\", \"bussCode\":\"J1_9800_FQ02\", \"usrNo\":\"6281234567111888\", \"merId\":\"001SPPT49000001\", \"transDtTm\":\"20190827115123\", \"reqData\":{ \"merIdName\":\"渠道侧或云闪付商户名\", \"transNo\":\"1234514233413\", \"instalCapital\":2000 }}}";
        byte[] bytes = Json.getBytes();

        byte[] md = new byte[8];
        byte[] a1 = new byte[8];
        int i;
        for ( i = 0; i <= bytes.length - 8; i += 8) {
            System.arraycopy(bytes, i, a1, 0, 8);
            //每8个字节异或
            md = HexUtil.oxr(md,a1);
        }
        if (i<bytes.length){
            byte[] a2 = new byte[8];
            System.arraycopy(bytes, i, a2, 0, bytes.length - i);
            md = HexUtil.oxr(md,a1);
        }

        System.out.println(HexUtil.bytes2Hex(md).toLowerCase());
    }*/


}
