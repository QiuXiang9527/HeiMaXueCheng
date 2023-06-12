package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.mapper.TeachplanMediaMapper;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import com.xuecheng.content.service.TeachplanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.rmi.CORBA.Util;
import java.util.List;

/**
 * @author Mr.M
 * @version 1.0
 * @description TODO
 * @date 2023/2/12 14:49
 */
/**
 * @description 课程计划service接口实现类
 * @author Mr.M
 * @date 2022/9/9 11:14
 * @version 1.0
 */
@Service
public class TeachplanServiceImpl implements TeachplanService {
    @Autowired
    TeachplanMediaMapper teachplanMediaMapper;
    @Autowired
    TeachplanMapper teachplanMapper;
    @Override
    public List<TeachplanDto> findTeachplanTree(long courseId) {
        return teachplanMapper.selectTreeNodes(courseId);
    }

    @Override
    public void saveTeachplan(SaveTeachplanDto teachplanDto) {
        //判断是新增还是修改
        if (teachplanDto.getId()!=null){
            //修改
            Teachplan teachplan = new Teachplan();
            BeanUtils.copyProperties(teachplanDto,teachplan);
            teachplanMapper.updateById(teachplan);
        }else {
//            select  count(*) from teachplan where course_id = '22' and parentid='237'
            LambdaQueryWrapper<Teachplan> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Teachplan::getCourseId,teachplanDto.getCourseId()).eq(Teachplan::getParentid,teachplanDto.getParentid());
            Integer integer = teachplanMapper.selectCount(lambdaQueryWrapper);
            //新增
            Teachplan teachplan = new Teachplan();
            BeanUtils.copyProperties(teachplanDto,teachplan);
            teachplan.setOrderby(integer+1);
            teachplanMapper.insert(teachplan);
        }
    }
    @Transactional
    @Override
    public void deleteById(Long id) {
        LambdaQueryWrapper<Teachplan> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Teachplan::getId,id);
        Teachplan teachplan = teachplanMapper.selectOne(lambdaQueryWrapper);
        if (teachplan.getParentid()==0){
            //父节点
            if (!(teachplanMapper.selectSonCount((long) id)>0)){
                System.out.println("可以删除");
                LambdaQueryWrapper<Teachplan> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
                lambdaQueryWrapper1.eq(Teachplan::getId,id);
                teachplanMapper.delete(lambdaQueryWrapper1);

            }else {
                //报错信息
                XueChengPlusException.cast("课程计划信息还有子级信息，无法操作");
            }

        }else {
            //子节点
            LambdaQueryWrapper<TeachplanMedia> teachplanMediaLambdaQueryWrapper = new LambdaQueryWrapper<>();
            teachplanMediaLambdaQueryWrapper.eq(TeachplanMedia::getTeachplanId, id);
            teachplanMediaMapper.delete(teachplanMediaLambdaQueryWrapper);
            LambdaQueryWrapper<Teachplan> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
            lambdaQueryWrapper1.eq(Teachplan::getId,id);
            teachplanMapper.delete(lambdaQueryWrapper1);
        }
    }
}
