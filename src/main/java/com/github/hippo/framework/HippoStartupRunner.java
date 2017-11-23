package com.github.hippo.framework;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.github.hippo.bean.ServiceRouteBean;
import com.github.hippo.cache.RouteCache;
import com.github.hippo.govern.ServiceGovern;
import com.github.hippo.netty.HippoClientBootstrap;
import com.github.hippo.netty.HippoClientBootstrapMap;
import com.github.hippo.service.ServiceRouteService;
import com.github.hippo.utils.RouteRulesUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * spring boot startup to load cache Created by hanruofei on 16/8/11.
 */
@Component
public class HippoStartupRunner implements CommandLineRunner {
  private static final Logger LOGGER = LoggerFactory.getLogger(HippoStartupRunner.class);

  @Autowired
  private ServiceRouteService serviceRouteService;

  @Autowired
  private ServiceGovern serviceGovern;

  @Override
  public void run(String... strings) throws Exception {
    LOGGER.info("----- start to load service route cache -----");
    List<ServiceRouteBean> serviceRouteBeen = serviceRouteService.showAll();
    LOGGER.info("serviceRouteBeen:{}", serviceRouteBeen);
    RouteCache.INSTANCE.refreshCache(serviceRouteBeen);
    LOGGER.info("----- load service route cache success -----");

    new Thread(() -> {
      ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(1);
      newScheduledThreadPool.scheduleAtFixedRate(() -> {
        if (CollectionUtils.isEmpty(RouteRulesUtils.SERVICENAMES)) {
          return;
        }
        RouteRulesUtils.SERVICENAMES.forEach(this::conntectionProcess);
      }, 120, 15, TimeUnit.SECONDS);

    }).start();
  }

  private void conntectionProcess(String serviceName) {
    List<String> serviceAddresses = null;
    try {
      serviceAddresses = serviceGovern.getServiceAddresses(serviceName);
    } catch (Exception e) {
      LOGGER.error("getServiceAddresses error:[" + serviceName + "],每10秒会重试", e);
      return;
    }
    if (CollectionUtils.isEmpty(serviceAddresses)) {
      return;
    }
    for (String serviceAddress : serviceAddresses) {
      String[] split = serviceAddress.split(":");
      String host = split[0];
      int port = Integer.parseInt(split[1]);
      if (StringUtils.isBlank(host) || port <= 0 || port > 65532) {
        LOGGER.warn("[%s]服务参数异常.host=%s,port=%s", serviceName, host, port);
        continue;
      }
      createHippoHandler(serviceName, host, port);

    }
  }

  static void createHippoHandler(String serviceName, String host, int port) {
    synchronized (serviceName) {
      if (checkServiceExist(serviceName, host, port)) {
        return;
      }
      try {
        HippoClientBootstrap bootstrap = new HippoClientBootstrap(serviceName, host, port);
        HippoClientBootstrapMap.put(serviceName, host, port, bootstrap);
      } catch (Exception e) {
        LOGGER.error(e.getMessage(), e);
      }
    }

  }

  private static boolean checkServiceExist(String serviceName, String host, int port) {
    if (!HippoClientBootstrapMap.containsKey(serviceName)) {
      return false;
    }
    Map<String, HippoClientBootstrap> map = HippoClientBootstrapMap.get(serviceName);
    if (map == null || CollectionUtils.isEmpty(map.values())) {
      return false;
    }
    return !HippoClientBootstrapMap.containsSubKey(serviceName, host + ":" + port);
  }

}
