package com.xuecheng.content;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.mapper.TeachplanMediaMapper;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.model.po.TeachplanMedia;
import com.xuecheng.content.service.TeachplanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeachplanServiceTests {
    @Autowired
    TeachplanMapper teachplanMapper;
    @Autowired
    TeachplanMediaMapper teachplanMediaMapper;
    int id = 264;
    @Test
    public void delete(){
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
//                new RuntimeException()
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
