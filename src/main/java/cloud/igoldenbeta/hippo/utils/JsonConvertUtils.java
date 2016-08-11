package cloud.igoldenbeta.hippo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConvertUtils {
    private static ObjectMapper mapper = new ObjectMapper();
	/**
    /**
     * 把对象转成json字符串
     */
    public static String toJson(Object obj) {
        if (obj == null){
            return null;
        }
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JsonConvert Error");
        }
    }
}
