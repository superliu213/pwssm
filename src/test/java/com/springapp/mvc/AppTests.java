package com.springapp.mvc;

import com.springapp.common.busilog.dao.SysLogDao;
import com.springapp.common.entity.SysLog;
import com.springapp.mvc.dao.*;
import com.springapp.mvc.entity.Function;
import com.springapp.mvc.entity.Group;
import com.springapp.mvc.entity.Role;
import com.springapp.mvc.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/spring-config.xml"
        ,"classpath:spring/springmvc-servlet.xml"})
public class AppTests {
    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Autowired
    private UserDao userDao;

    @Autowired
    private FunctionDao functionDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private SysLogDao sysLogDao;

    @Autowired
    private RoleDao roleDao;

    @Test
    public void testUser() throws Exception {
        List<User> tempList = userDao.listBy(null);

        System.out.println("size:"+tempList.size());
        for(User temp : tempList){
            System.out.println(temp.toString());;
        }

        System.out.println("end");
    }

    @Test
    public void testFunction() throws Exception {
        List<Function> tempList = functionDao.listBy(null);

        System.out.println("size:"+tempList.size());
        for(Function temp : tempList){
            System.out.println(temp.toString());;
        }

        System.out.println("end");
    }

    @Test
    public void testGroup() throws Exception {
        List<Group> tempList = groupDao.listBy(null);

        System.out.println("size:"+tempList.size());
        for(Group temp : tempList){
            System.out.println(temp.toString());;
        }

        System.out.println("end");
    }

    @Test
    public void testLog() throws Exception {
        List<SysLog> tempList = sysLogDao.listBy(null);

        System.out.println("size:"+tempList.size());
        for(SysLog temp : tempList){
            System.out.println(temp.toString());;
        }

        System.out.println("end");
    }

    @Test
    public void testRole() throws Exception {
        List<Role> tempList = roleDao.listBy(null);

        System.out.println("size:"+tempList.size());
        for(Role temp : tempList){
            System.out.println(temp.toString());;
        }

        System.out.println("end");
    }
}
