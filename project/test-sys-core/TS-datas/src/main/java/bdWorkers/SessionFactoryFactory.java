package bdWorkers;


import data.beans.Bean;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class SessionFactoryFactory {
    private static SessionFactory sessionFactory;

    private SessionFactoryFactory(){}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                Reflections reflections = new Reflections("data",new SubTypesScanner(false));
                reflections.getSubTypesOf(Bean.class).stream().forEach(cls ->{
                    configuration.addAnnotatedClass(cls);
                });


                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }

}
