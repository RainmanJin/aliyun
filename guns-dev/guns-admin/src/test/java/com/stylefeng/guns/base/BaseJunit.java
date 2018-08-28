package com.stylefeng.guns.base;

import com.stylefeng.guns.GunsApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;


/**
 * 基础测试类
 *
 * @author stylefeng
 * @Date 2017/5/21 16:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GunsApplication.class)
@WebAppConfiguration
//@Transactional //打开的话测试之后数据可自动回滚
public class BaseJunit {

//    @Autowired
//    WebApplicationContext webApplicationContext;
//
//    protected MockMvc mockMvc;
//
//    @Before
//    public void setupMockMvc(){
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }

//    @Before
//    public void initDatabase(){
//    }
}
