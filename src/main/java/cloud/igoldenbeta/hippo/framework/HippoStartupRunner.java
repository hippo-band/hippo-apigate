package cloud.igoldenbeta.hippo.framework;

import cloud.igoldenbeta.hippo.bean.ServiceRouteBean;
import cloud.igoldenbeta.hippo.cache.RouteCache;
import cloud.igoldenbeta.hippo.service.ServiceRouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/** spring boot startup to load cache
 * Created by hanruofei on 16/8/11.
 */
@Component
public class HippoStartupRunner implements CommandLineRunner{
    private static final Logger LOGGER = LoggerFactory.getLogger(HippoStartupRunner.class);

    @Autowired
    private ServiceRouteService serviceRouteService;

    @Override
    public void run(String... strings) throws Exception {
        LOGGER.info("----- start to load service route cache -----");
        List<ServiceRouteBean> serviceRouteBeen = serviceRouteService.showAll();
        LOGGER.info("serviceRouteBeen:{}",serviceRouteBeen);
        RouteCache.INSTANCE.refreshCache(serviceRouteBeen);
        LOGGER.info("----- load service route cache success -----");
    }
}
