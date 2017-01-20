package com.github;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.hippo.mapper.ServiceRouteMapper;
import com.github.hippo.model.ServiceRoute;

import java.util.List;

/**
 * Created by hanruofei on 16/8/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MybatisTest {

    @Autowired
    private ServiceRouteMapper serviceRouteMapper;

    @Test
    public void selectAll(){

        List<ServiceRoute> serviceRoutes = serviceRouteMapper.selectAll();

        System.out.println(serviceRoutes);
    }

}
