package com.xuecheng.content.api;

import com.xuecheng.base.exception.ValidationGroups;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.base.model.R;
import com.xuecheng.content.model.dto.AddCourseDto;
import com.xuecheng.content.model.dto.CourseBaseInfoDto;
import com.xuecheng.content.model.dto.EditCourseDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseBaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.M
 * @version 1.0
 * @description TODO
 * @date 2023/2/11 15:44
 */
@Api(value = "课程信息管理接口",tags = "课程信息管理接口")
@RestController
public class CourseBaseInfoController {

    @Autowired
    CourseBaseInfoService courseBaseInfoService;

    @ApiOperation("课程查询接口")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody(required=false) QueryCourseParamsDto queryCourseParamsDto) {

        PageResult<CourseBase> courseBasePageResult = courseBaseInfoService.queryCourseBaseList(pageParams, queryCourseParamsDto);

        return courseBasePageResult;

    }
    @ApiOperation("新增课程")
    @PostMapping("/course")
    public CourseBaseInfoDto createCourseBase(@RequestBody @Validated({ValidationGroups.Inster.class}) AddCourseDto addCourseDto){
        //获取到用户所属机构的id
        Long companyId = 1232141425L;
//        int i = 1/0;
        CourseBaseInfoDto courseBase = courseBaseInfoService.createCourseBase(companyId, addCourseDto);
        return courseBase;
    }

    /**
     * 根据课程ID查询课程详情
     * @param courseId
     * @return
     */
    @ApiOperation("查询课程")
    @GetMapping("/course/{courseId}")
    public CourseBaseInfoDto selectCourseBase(@PathVariable Long courseId){
        //获取到用户所属机构的id
        CourseBaseInfoDto courseBaseInfo = courseBaseInfoService.getCourseBaseInfo(courseId);
        return courseBaseInfo;
    }
    @ApiOperation("修改课程")
    @PutMapping("/course")
    public CourseBaseInfoDto updateCourseBase(@RequestBody @Validated EditCourseDto editCourseDto){
        //获取到用户所属机构的id
        CourseBaseInfoDto courseBaseInfoDto = courseBaseInfoService.updateCourseBase(editCourseDto);
        return courseBaseInfoDto;
    }
    @ApiOperation("删除课程")
    @DeleteMapping("/course/{id}")
    public R deleteCourseBase(@PathVariable Long id){
        //获取到用户所属机构的id
        courseBaseInfoService.deleteById(id);
        return R.success(null,"200");
    }
}
