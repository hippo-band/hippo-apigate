package com.github.hippo.utils;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.github.hippo.cache.RouteCache;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by hanruofei on 16/8/11.
 */
public class RouteRulesUtils {
  
  public static Set<String>SERVICENAMES=new HashSet<>();

  /**
   * get a host by service name & method name
   * 
   * @param serviceName
   * @param methodName
   * @return
   */
  public static String getHost(String serviceName, String methodName) {
    String host = _getHost(serviceName, methodName);
    if(StringUtils.isNotBlank(host)) {
      SERVICENAMES.add(host);
    }
    return host;
  }

  private static String _getHost(String serviceName, String methodName) {
    Map<String, String> methodHostMap = RouteCache.INSTANCE.getCache(serviceName);
    if (methodHostMap == null || MapUtils.isEmpty(methodHostMap))
      throw new RuntimeException("cache not exist");

    Set<String> methodSet = methodHostMap.keySet();
    if (methodSet.contains(methodName)) return methodHostMap.get(methodName);// Match exactly

    String[] methodNameSplit = methodName.split("/");
    for (String oneMethod : methodSet) {
      String[] oneMethodSplit = oneMethod.split("/");
      if (oneMethodSplit[0].equals(methodNameSplit[0])) return methodHostMap.get(oneMethod);// Match
                                                                                            // first
                                                                                            // part
      if (oneMethodSplit[1].equals(methodNameSplit[1])) return methodHostMap.get(oneMethod);// Match
                                                                                            // second
                                                                                            // part
    }

    if (methodSet.contains("*/*")) return methodHostMap.get("*/*");// Match default
    throw new RuntimeException("route rules not exist");

  }
}
