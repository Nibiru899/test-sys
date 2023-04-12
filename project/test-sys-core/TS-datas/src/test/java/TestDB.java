import bdWorkers.SessionFactoryFactory;
import data.users.Group;
import org.hibernate.criterion.Restrictions;
import org.junit.jupiter.api.Assertions;
import org.hibernate.*;
import org.junit.jupiter.api.Test;

public class TestDB {
    @Test
    public void test1(){
        Session session = SessionFactoryFactory.getSessionFactory().openSession();
        Group group = new Group();
        group.setCode("ИПБ-19");
        group.setName("Иженерия ВАЩЕ");
        session.persist(group);
        Criteria criteria = session.createCriteria(Group.class);
        criteria.add(Restrictions.eq("code","ИПБ-19"));
        Group group1 = (Group) criteria.uniqueResult();

        Assertions.assertEquals(group,group1);
    }
}
