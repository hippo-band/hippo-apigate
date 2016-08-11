package cloud.igoldenbeta;

import cloud.igoldenbeta.hippo.mapper.ServiceRouteMapper;
import cloud.igoldenbeta.hippo.model.ServiceRoute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
