import bdWorkers.SessionFactoryFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

public class BDWorker {
    Session session;
    BDWorker(){
        session = SessionFactoryFactory.getSessionFactory().openSession();
    }



    private Query createQuery(Class cls,String filter){

        String query = "FROM " + cls.getSimpleName() + " AS bean ";
        if (!filter.isEmpty()) {
            query += " WHERE " + filter;
        }
        return session.createQuery(query,cls);
    }

    public <T> void delete(Class<T> cls,String filter){
        session.getTransaction().begin();
        String query = "DELETE FROM " + cls.getSimpleName();
        if (!filter.isEmpty()) {
            query += " WHERE " + filter;
        }
        session.createQuery(query).executeUpdate();
        session.getTransaction().commit();
    }

    public <T> T find(Class<T> cls, String filter){
        try {
            return (T) createQuery(cls,filter).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    public <T> T find(Class<T> cls, Long id){
        try {
            return session.load(cls,id);
        } catch (NoResultException e) {
            return null;
        }
    }

    public <T> List<T> getObjects(Class<T> cls, String filter){
        try {
            return createQuery(cls,filter).list();
        } catch (NoResultException e) {
            return null;
        }
    }


    public <T> Long updateOrAdd(T object){
        session.getTransaction().begin();
        Long id = (Long) session.save(object);
        session.getTransaction().commit();
        return id;
    }

    public <T> void delete(T object) {
        session.getTransaction().begin();
        session.delete(object);
        session.getTransaction().commit();
    }



}
