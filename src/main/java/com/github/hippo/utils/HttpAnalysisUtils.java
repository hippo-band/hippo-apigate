package com.github.hippo.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

/**
 * Created by hanruofei on 16/8/11.
 */
public class HttpAnalysisUtils {

    /**
     * analysis request json from url
     * @param request
     * @return
     */
    public static String resolveRequestToUrl(HttpServletRequest request){
        Map requestMap = request.getParameterMap();// resqustMap type is <Object,String[]>
        /*把单个参数解析为字符 而不是数组*/
        Set<Map.Entry<String, Object>> requestEntries = requestMap.entrySet();
        requestEntries.parallelStream().forEach(n->{
            Object[] values = (Object[]) n.getValue();
            if (values.length == 1) n.setValue(values[0]);
        });
        return JsonConvertUtils.toJson(requestMap);
    }

    /**
     * analysis request json from body
     * @param request
     * @return
     */
    public static String resolveRequestToBody(HttpServletRequest request){
        try {
            int length = request.getContentLength();
            String requestStr = "";
            StringBuilder sb = new StringBuilder();
            if (length != 0) {
                InputStream inputStream = request.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                while ((requestStr = br.readLine()) != null) {
                    sb.append(requestStr);
                }
                br.close();
            }
            return sb.toString();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
