package com.xuecheng.content;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.mapper.CourseTeacherMapper;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.mapper.TeachplanMediaMapper;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeachCourseMapperTests {
    @Autowired
    CourseTeacherMapper courseTeacherMapper;
    CourseTeacher courseTeacher = new CourseTeacher();

    @Test
    void InsertTeacher(){
        courseTeacher.setCourseId(1L);
        courseTeacher.setTeacherName("zhang老师");
        courseTeacher.setPosition("教师职位");
        courseTeacher.setIntroduction("教师简介");
        courseTeacherMapper.insert(courseTeacher);
        CourseTeacher courseTeacher1 = courseTeacherMapper.selectById(courseTeacher.getId());
        System.out.println(courseTeacher1);


    }

}
