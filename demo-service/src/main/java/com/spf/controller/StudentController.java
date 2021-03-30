package com.spf.controller;


import com.spf.model.Student;
import com.spf.service.StudentService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/student")
public class StudentController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static int MAX = 10;

    @Autowired
    private StudentService studentService;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedissonClient redissonClient;

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    @ResponseBody
    public Student addStudent(Student student) {
        studentService.insertStudent(student);
        return student;
    }

    @RequestMapping("/getStudent/{id}")
    @ResponseBody
    public Student selectStudent(@PathVariable("id") Integer id) {
        return studentService.selectStudent(id);
    }

    @RequestMapping(path = "/update")
    @ResponseBody
    public Student updateStudent(Student student) {
        studentService.updateStudent(student);
        return student;
    }


    @RequestMapping(path = "/test1")
    @ResponseBody
    public void test1() {
        redisTemplate.opsForValue().set("num",100);
    }

    @RequestMapping(path = "/test")
    @ResponseBody
    public String test() {

        String id = "prod_1011";
        RLock rLock = redissonClient.getLock(id);
        try {
            rLock.lock();

           int num = Integer.parseInt(redisTemplate.opsForValue().get("num").toString());

            if (num > 0) {
                num -= 1;
                redisTemplate.opsForValue().set("num",num);
                logger.info("---扣减成功，剩余库存：" + num);
            } else {
                logger.info("---商品已售罄");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            rLock.unlock();
        }
        return "ok";
    }

}
