package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.CourseTeacherMapper;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.mapper.TeachplanMediaMapper;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import com.xuecheng.content.service.CourseTeacherService;
import com.xuecheng.content.service.TeachplanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Mr.M
 * @version 1.0
 * @description TODO
 * @date 2023/2/12 14:49
 */

/**
 * @description课程教师service接口实现类
 * @author Mr.M
 * @date 2022/9/9 11:14
 * @version 1.0
 */
@Service
public class CourseTeacherServiceImpl implements CourseTeacherService {
   @Autowired
    CourseTeacherMapper courseTeacherMapper;

    @Override
    public List<CourseTeacher> getTeacher(Long courseId) {
        LambdaQueryWrapper<CourseTeacher> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CourseTeacher::getCourseId,courseId);
        List<CourseTeacher> courseTeachers = courseTeacherMapper.selectList(lambdaQueryWrapper);
        return courseTeachers;
    }

    @Override
    public CourseTeacher insertTeacher(CourseTeacher courseTeacher) {
        courseTeacherMapper.insert(courseTeacher);
        return courseTeacherMapper.selectById(courseTeacher.getId());
    }

    @Override
    public void delete(Long courseId, Long id) {
        LambdaQueryWrapper<CourseTeacher> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CourseTeacher::getId,id)
                .eq(CourseTeacher::getCourseId,courseId);
        courseTeacherMapper.delete(lambdaQueryWrapper);
    }

    @Override
    public CourseTeacher putTeacher(CourseTeacher courseTeacher) {
        CourseTeacher courseTeacher1 = courseTeacherMapper.selectById(courseTeacher);
        BeanUtils.copyProperties(courseTeacher,courseTeacher1);
        courseTeacherMapper.updateById(courseTeacher1);
        return courseTeacherMapper.selectById(courseTeacher);
    }
}
