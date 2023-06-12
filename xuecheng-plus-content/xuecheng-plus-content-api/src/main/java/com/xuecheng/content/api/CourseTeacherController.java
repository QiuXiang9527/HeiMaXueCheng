package com.xuecheng.content.api;

import com.xuecheng.base.model.R;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import com.xuecheng.content.service.TeachplanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description 课程教师页面controller
 * @author Mr.M
 * @date 2022/9/6 11:29
 * @version 1.0
 */
@Api(value = "课程教师编辑接口",tags = "课程教师编辑接口")
@RestController
public class CourseTeacherController {
    @Autowired
    CourseTeacherService courseTeacherService;
    @ApiOperation("查询课程教师")
    @GetMapping("/courseTeacher/list/{courseId}")
    public List<CourseTeacher> getTeacher(@PathVariable Long courseId){
        return courseTeacherService.getTeacher(courseId);
    }
    @ApiOperation("新增课程教师")
    @PostMapping("/courseTeacher")
    public CourseTeacher insertTeacher(@RequestBody CourseTeacher courseTeacher){
        return courseTeacherService.insertTeacher(courseTeacher);
    }
    @ApiOperation("修改课程教师")
    @PutMapping("/courseTeacher")
    public CourseTeacher putTeacher(@RequestBody CourseTeacher courseTeacher){
        return courseTeacherService.putTeacher(courseTeacher);
    }
    @ApiOperation("删除课程教师")
    @DeleteMapping("/courseTeacher/course/{courseId}/{id}")
    public R<String> insertTeacher(@PathVariable Long courseId,@PathVariable Long id){
         courseTeacherService.delete(courseId,id);
         //todo 无法正确返回状态码
         return R.success(null,"200");
    }
}
