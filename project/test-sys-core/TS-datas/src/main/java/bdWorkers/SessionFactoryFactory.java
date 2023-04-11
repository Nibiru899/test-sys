package bdWorkers;


import data.forming.*;
import data.service.Picture;
import data.testing.Result;
import data.testing.UserAnswer;
import data.testing.UserConnectingAnswer;
import data.testing.UserSimplyAnswer;
import data.users.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFactoryFactory {
    private static SessionFactory sessionFactory;

    private SessionFactoryFactory(){}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Answer.class);
                configuration.addAnnotatedClass(Competity.class);
                configuration.addAnnotatedClass(ConnectedAnswer.class);
                configuration.addAnnotatedClass(Plan.class);
                configuration.addAnnotatedClass(Question.class);
                configuration.addAnnotatedClass(Theme.class);
                configuration.addAnnotatedClass(Picture.class);

                configuration.addAnnotatedClass(Result.class);
                configuration.addAnnotatedClass(UserAnswer.class);
                configuration.addAnnotatedClass(UserConnectingAnswer.class);
                configuration.addAnnotatedClass(UserSimplyAnswer.class);

                configuration.addAnnotatedClass(Group.class);
                configuration.addAnnotatedClass(Student.class);
                configuration.addAnnotatedClass(Subject.class);
                configuration.addAnnotatedClass(Teacher.class);
                configuration.addAnnotatedClass(User.class);


                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }

}
