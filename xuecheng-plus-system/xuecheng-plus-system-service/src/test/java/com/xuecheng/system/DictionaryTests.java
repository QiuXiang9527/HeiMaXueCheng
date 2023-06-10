package com.xuecheng.system;

import com.alibaba.fastjson.JSONObject;
import com.xuecheng.system.model.dto.Dictionary201;
import com.xuecheng.system.model.po.Dictionary;
import com.xuecheng.system.service.DictionaryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mr.M
 * @version 1.0
 * @description TODO
 * @date 2023/2/12 9:24
 */
@SpringBootTest
public class DictionaryTests {

    @Autowired
    DictionaryService dictionaryService;

    @Test
    public void testCourseBaseMapper() {
    }

}
