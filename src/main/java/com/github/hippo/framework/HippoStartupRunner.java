package com.github.hippo.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.hippo.bean.ServiceRouteBean;
import com.github.hippo.cache.RouteCache;
import com.github.hippo.service.ServiceRouteService;

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
   //     LOGGER.info("----- start to load service route cache -----");
    //    List<ServiceRouteBean> serviceRouteBeen = serviceRouteService.showAll();
     //   LOGGER.info("serviceRouteBeen:{}",serviceRouteBeen);
      //  RouteCache.INSTANCE.refreshCache(serviceRouteBeen);
       // LOGGER.info("----- load service route cache success -----");
    }
}
