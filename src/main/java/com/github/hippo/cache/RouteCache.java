package com.github.hippo.cache;

import org.apache.commons.collections.MapUtils;

import com.github.hippo.bean.ServiceRouteBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  service route cache
 *
 * Created by hanruofei on 16/8/11.
 */
public enum RouteCache {
    INSTANCE;

    private Map<String,Map<String,String>> routeMap = new HashMap<>();
    RouteCache() {
    }

    /**
     * get cache by service name
     * @param serviceName
     * @return
     */
    public Map<String,String> getCache(String serviceName){
        return routeMap.get(serviceName);
    }

    /**
     * refresh service route to cache
     * @param serviceRoute
     */
    public void refreshCache(List<ServiceRouteBean> serviceRoute){
        serviceRoute.stream().forEach(n->{
            Map<String, String> methodHostMap = routeMap.get(n.getServiceName());
            if (MapUtils.isEmpty(methodHostMap)) methodHostMap = new HashMap<>();
            methodHostMap.put(n.getServiceMethod(),n.getServiceHost());
            routeMap.put(n.getServiceName(),methodHostMap);
        });
    }
}
