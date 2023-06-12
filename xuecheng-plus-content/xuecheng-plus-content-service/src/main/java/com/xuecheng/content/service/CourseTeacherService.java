package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.CourseTeacher;

import java.util.List;

public interface CourseTeacherService {


    List<CourseTeacher> getTeacher(Long courseId);

    CourseTeacher insertTeacher(CourseTeacher courseTeacher);

    void delete(Long courseId, Long id);

    CourseTeacher putTeacher(CourseTeacher courseTeacher);
}
