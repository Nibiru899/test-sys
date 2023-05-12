package sequrity;

import bdWorkers.BDWorker;
import data.users.*;

import org.jasypt.util.password.BasicPasswordEncryptor;

import java.util.List;
import java.util.stream.Collectors;


public class SecurityTool {


    private static String encode(String pass) {
        return new BasicPasswordEncryptor().encryptPassword(pass);
    }

    private static boolean checkPass(User user, String pass) {
        if (user == null){
            return false;
        }
        return new BasicPasswordEncryptor().checkPassword(pass, user.getPassword().getText());
    }



    public static void linkToSubject(Long subj,String login,boolean del) {
        BDWorker worker = new BDWorker();
        User user = worker.find(User.class,"login = '" + login +"'");
        if (!(user instanceof Teacher)){
            return;
        }
        Teacher teacher = (Teacher) user;
        List<Subject> l = teacher.getSubjects();
        if (del){
            l.stream().filter(e-> e.getId()!=subj).collect(Collectors.toList());
        } else {
            l.add(worker.find(Subject.class,subj));
        }
        teacher.setSubjects(l);
    }

    public static boolean linkToGroup(Long groupId,String login,boolean del) {
        BDWorker worker = new BDWorker();

        User user = worker.find(User.class,"login = '" + login +"'");
        if (user instanceof Student) {
            Student student = ((Student) user);
            if (del){
                student.setGroup(null);
            } else {
                student.setGroup(worker.find(Group.class,groupId));
            }
        }
        if (user instanceof Teacher) {
            Teacher teacher = (Teacher) user;
            List<Group> l = teacher.getGroups();
            if (del){
                l.stream().filter(e-> e.getId()!=groupId).collect(Collectors.toList());
            } else {
                l.add(worker.find(Group.class,groupId));
            }
            teacher.setGroups(l);
        }
        worker.updateOrAdd(user);
        return true;
    }
    public static boolean authorise (String login, String password) {
        BDWorker worker = new BDWorker();
        return checkPass(worker.find(User.class,"login = '" + login +"'"),password);
    }

    public static boolean delAcc (String login,String password){
        BDWorker worker = new BDWorker();
        User user = worker.find(User.class,"login = '" + login +"'");
        if (!checkPass(user,password)){
            return false;
        }
        worker.delete(user);
        return true;
    }
    public static Long register(String name,String surname,String fatherName,String login,String password,String level) {
        BDWorker worker = new BDWorker();
        if (worker.find(User.class,"login = '" + login + "'") != null){
            return Long.valueOf(-1);
        }
        User user = null;
        if (level.equals("TEACHER")){
            user = new Teacher();
        } else {
            if (level.equals("STUDENT")){
                user = new Student();
            } else {
                user = new User();
            }
        }

        user.setName(name);
        user.setSurName(surname);
        user.setFatherName(fatherName);
        user.setLogin(login);
        user.setPassword(new Password(encode(password)));
        return worker.updateOrAdd(user);
    }

    public static boolean changePass(String login,String old, String neww){
        BDWorker worker = new BDWorker();
        User user = worker.find(User.class,"login = '" + login +"'");
        if (!checkPass(user,old)){
            return false;
        }
        user.getPassword().setText(encode(neww));
        worker.updateOrAdd(user);
        return true;
    }

    public static boolean changePassNoOld(String login, String neww){
        BDWorker worker = new BDWorker();
        User user = worker.find(User.class,"login = '" + login + "'");
        if (user==null)
            return false;
        user.getPassword().setText(encode(neww));
        worker.updateOrAdd(user);
        return true;
    }
}
