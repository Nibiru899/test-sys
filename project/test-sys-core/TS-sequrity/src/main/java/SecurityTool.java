import data.users.Password;
import data.users.User;

import org.jasypt.util.password.BasicPasswordEncryptor;



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
    public static Long register(String name,String surname,String fatherName,String login,String password) {
        BDWorker worker = new BDWorker();
        if (worker.find(User.class,"login = '" + login + "'") != null){
            return Long.valueOf(-1);
        }
        User user = new User();
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
