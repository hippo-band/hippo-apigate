package com.github.hippo.framework;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebAPISecurityFilter implements Filter {
  private static final Logger LOGGER = LoggerFactory.getLogger(WebAPISecurityFilter.class);

  public FilterConfig config;


  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    config = filterConfig;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    corsResponse(httpResponse);

    if (httpRequest.getMethod().equalsIgnoreCase("options")) {
      httpResponse.setStatus(200);
      return;
    }

    chain.doFilter(httpRequest, response);
    return;
  }

  /**
   * Cross-origin resource sharing (CORS)
   *
   * @param response
   */
  private void corsResponse(HttpServletResponse response) {
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE, HEAD");
    response.setHeader("Access-Control-Max-Age", "3600");
    response.setHeader("Access-Control-Allow-Headers", WebAPISecurityProtocol.CUSTOM_HEADERS);
  }

  @Override
  public void destroy() {
    this.config = null;
  }

}
