import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Jsoner {
    public static<T> T fromString(Class<T> cls, String json) {
        try {
            return new ObjectMapper().readValue(json,cls);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static String toJson(Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
