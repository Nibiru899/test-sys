import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.forming.Competity;
import data.forming.Plan;
import data.forming.Question;
import data.forming.Theme;
import data.users.Group;
import data.users.Subject;

import java.util.List;
import java.util.stream.Collectors;

public class FormerTool {
     private static<T> T fromString(Class<T> cls,String json) {
        try {
            return new ObjectMapper().readValue(json,cls);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    private static String toJson(Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    private static Class getClass(String type){
        switch (type){
            case "Theme":{
                return Theme.class;
            }
            case "Question":{
                return Question.class;
            }
            case "Plan":{
                return Plan.class;
            }
            case "Competity":{
                return Competity.class;
            }
            case "Group" : {
                return Group.class;
            }
            case "Subject" : {
                return Subject.class;
            }
        }
        return null;
    }

    public static void update(String type,String json){
        Class cls = getClass(type);
        Object object = fromString(cls,json);
        if (object!=null){
            new BDWorker().updateOrAdd(object);
        }
    }

    public static void delete(String type, Long id){
         new BDWorker().delete(getClass(type),"id = '" + id + "'");
    }

    public static List<String> getAll(String type,String filter){
         return (List<String>) new BDWorker().getObjects(getClass(type),filter).stream().map(e->toJson(e)).collect(Collectors.toList());
    }



}