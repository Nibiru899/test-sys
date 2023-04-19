import data.forming.Themes.Competity;
import data.forming.Plans.Plan;
import data.forming.Questions.Question;
import data.forming.Themes.Theme;
import data.users.Group;
import data.users.Subject;

import java.util.List;
import java.util.stream.Collectors;

public class FormerTool {


    private static Class getClass(String type){
        switch (type){
            case "Theme":{
                return Theme.class;
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
        Object object = Jsoner.fromString(cls,json);
        if (object!=null){
            new BDWorker().updateOrAdd(object);
        }
    }

    public static void delete(String type, Long id){
         new BDWorker().delete(getClass(type),"id = '" + id + "'");
    }

    public static List<String> getAll(String type,String filter){
         return (List<String>) new BDWorker().getObjects(getClass(type),filter).stream().map(e->Jsoner.toJson(e)).collect(Collectors.toList());
    }

    public static String get(String type,String filter) {
         return Jsoner.toJson(new BDWorker().find(getClass(type),filter));
    }



}