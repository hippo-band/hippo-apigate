package com.github.hippo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.hippo.bean.ServiceRouteBean;
import com.github.hippo.cache.RouteCache;
import com.github.hippo.client.HippoProxy;
import com.github.hippo.framework.ResponseEntity;
import com.github.hippo.service.ServiceRouteService;
import com.github.hippo.util.FastJsonConvertUtils;
import com.github.hippo.utils.HttpAnalysisUtils;
import com.github.hippo.utils.RouteRulesUtils;
import com.google.gson.Gson;

/**
 * Created by hanruofei on 16/8/4.
 */
@EnableAutoConfiguration
@Controller
@RequestMapping(value = "/service")
public class ServiceControllor {
  private static final Logger LOGGER = LoggerFactory.getLogger(ServiceControllor.class);

  @Autowired
  private HippoProxy hippoProxy;
  
  @Autowired
  private ServiceRouteService serviceRouteService;
  
  @ResponseBody
  @RequestMapping(value = "/ping")
  ResponseEntity<?> ping() {
    return ResponseEntity.success("Hello World!");
  }
  

  @ResponseBody
  @RequestMapping(value = "/common/refresh")
  ResponseEntity<?> refresh() {
      try {
          LOGGER.info("----- start to load service route cache -----");
          List<ServiceRouteBean> serviceRouteBeen = serviceRouteService.showAll();
          LOGGER.info("serviceRouteBeen:{}", serviceRouteBeen);
          RouteCache.INSTANCE.refreshCache(serviceRouteBeen);
          LOGGER.info("----- load service route cache success -----");

          return ResponseEntity.success(serviceRouteBeen);
      } catch (Exception e) {
          return ResponseEntity.error("refresh error", e);
      }
  }

  @RequestMapping(value = "/{serviceName}/**/", method = {RequestMethod.GET,
      RequestMethod.DELETE}, produces = {"application/json;charset=UTF-8"})
  public @ResponseBody ResponseEntity<?> http1(
      @PathVariable(value = "serviceName") String serviceName, HttpServletRequest request) {
    try {
      String requestURI = request.getRequestURI();
      String methodName = StringUtils.substringAfter(requestURI, "service/" + serviceName + "/");
      String host = RouteRulesUtils.getHost(serviceName, methodName);

      try {
        String o = (String) hippoProxy.apiRequest(host, methodName,
            HttpAnalysisUtils.resolveRequestToUrl(request));
        return ResponseEntity.success(FastJsonConvertUtils.jsonToJavaObject(o, Object.class));
      } catch (Throwable e) {
        LOGGER.error("rpc_exception", e);
        return ResponseEntity.error(e.getMessage(), null);
      }
    } catch (Exception e) {
      LOGGER.error("getHttp_exception", e);
      return ResponseEntity.error(e.getMessage(), null);
    }
  }

  @RequestMapping(value = "/{serviceName}/**/", method = {RequestMethod.POST, RequestMethod.PUT,
      RequestMethod.PATCH}, produces = "application/json;charset=UTF-8")
  public @ResponseBody ResponseEntity<?> http2(
      @PathVariable(value = "serviceName") String serviceName, HttpServletRequest request) {
    try {
      String requestURI = request.getRequestURI();
      String methodName = StringUtils.substringAfter(requestURI, serviceName + "/");
      String host = RouteRulesUtils.getHost(serviceName, methodName);
      try {
        String o = (String) hippoProxy.apiRequest(host, methodName,
            HttpAnalysisUtils.resolveRequestToBody(request));
        return ResponseEntity.success(FastJsonConvertUtils.jsonToJavaObject(o, Object.class));
      } catch (Throwable e) {
        LOGGER.error("rpc_exception", e);
        return ResponseEntity.error(e.getMessage(), null);
      }
    } catch (Exception e) {
      LOGGER.error("getHttp_exception", e);
      return ResponseEntity.error(e.getMessage(), null);
    }
  }



  @RequestMapping(value = "/{serviceName}/**/", method = {RequestMethod.POST, RequestMethod.PUT,
      RequestMethod.PATCH}, headers = {"Content-Type=application/xml", "Content-Type=text/xml"})
  public @ResponseBody ResponseEntity<?> http4(
      @PathVariable(value = "serviceName") String serviceName, HttpServletRequest request) {
    try {
      String requestURI = request.getRequestURI();
      String methodName = StringUtils.substringAfter(requestURI, serviceName + "/");
      String host = RouteRulesUtils.getHost(serviceName, methodName);

      try {
        String xml = HttpAnalysisUtils.resolveRequestToBody(request);
        Gson gson = new Gson();
        String jsonPack = gson.toJson(xml);
        String o = (String) hippoProxy.apiRequest(host, methodName, jsonPack);
        return ResponseEntity.success(FastJsonConvertUtils.jsonToJavaObject(o, Object.class));
      } catch (Throwable e) {
        return ResponseEntity.error(e.getMessage(), null);
      }
    } catch (Exception e) {
      LOGGER.error("getHttp_exception", e);
      return ResponseEntity.error(e.getMessage(), null);
    }
  }


  @RequestMapping(value = "/{serviceName}/**/", method = {RequestMethod.OPTIONS}, produces = {
      "application/json;charset=UTF-8"})
  public @ResponseBody ResponseEntity<?> http3(
      @PathVariable(value = "serviceName") String serviceName, HttpServletRequest request) {
    return ResponseEntity.success(null);
  }

}
