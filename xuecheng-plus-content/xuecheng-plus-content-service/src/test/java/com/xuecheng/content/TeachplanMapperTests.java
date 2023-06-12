package com.xuecheng.content;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.base.exception.XueChengPlusException;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.model.po.Teachplan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TeachplanMapperTests {
    @Autowired
    TeachplanMapper teachplanMapper;
    Long id = 280L;
    @Test
    public void moveup(){
        List<Integer> list = teachplanMapper.selectOrderByField(id);
        //获取当前小节的TeachPlan对象
        LambdaQueryWrapper<Teachplan> nowlambdaQueryWrapper = new LambdaQueryWrapper<>();
        nowlambdaQueryWrapper.eq(Teachplan::getId,id);
        Teachplan nowteachplan = teachplanMapper.selectOne(nowlambdaQueryWrapper);
        Integer temp = 0;
        temp = nowteachplan.getOrderby();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)==nowteachplan.getOrderby()){
                System.out.println(list.get(i));

                LambdaQueryWrapper<Teachplan> furlambdaQueryWrapper = new LambdaQueryWrapper<>();
                int order = i-1;
                if (order<0){
                    XueChengPlusException.cast("已经到顶");
                }
                furlambdaQueryWrapper.eq(Teachplan::getOrderby,list.get(order))
                        .eq(Teachplan::getCourseId,nowteachplan.getCourseId())
                        .eq(Teachplan::getParentid,nowteachplan.getParentid());
                Teachplan furteachplan = teachplanMapper.selectOne(furlambdaQueryWrapper);
                nowteachplan.setOrderby(furteachplan.getOrderby());
                furteachplan.setOrderby(temp);
                return;
            }
        }
    }
    @Test
    public void movedown(){
        List<Integer> list = teachplanMapper.selectOrderByField(id);
        //获取当前小节的TeachPlan对象
        LambdaQueryWrapper<Teachplan> nowlambdaQueryWrapper = new LambdaQueryWrapper<>();
        nowlambdaQueryWrapper.eq(Teachplan::getId,id);
        Teachplan nowteachplan = teachplanMapper.selectOne(nowlambdaQueryWrapper);
        Integer temp = 0;
        temp = nowteachplan.getOrderby();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)==nowteachplan.getOrderby()){
                System.out.println(list.get(i));

                LambdaQueryWrapper<Teachplan> furlambdaQueryWrapper = new LambdaQueryWrapper<>();
                int order = i+1;
                if (order==list.size()){
                    XueChengPlusException.cast("已经到底达底部，在怎么做也是向上");
                }
                furlambdaQueryWrapper.eq(Teachplan::getOrderby,list.get(order))
                        .eq(Teachplan::getCourseId,nowteachplan.getCourseId())
                        .eq(Teachplan::getParentid,nowteachplan.getParentid());
                Teachplan furteachplan = teachplanMapper.selectOne(furlambdaQueryWrapper);
                nowteachplan.setOrderby(furteachplan.getOrderby());
                furteachplan.setOrderby(temp);
                return;
            }
        }
    }
}
