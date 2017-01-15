package dao;

import java.util.List;
import model.Operation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Sergey
 */
public class OperationDaoImpl implements IOperationDao {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    @Override
    public List<Operation> getAllOperatin(int idUser) {
        Session session = sessionFactory.openSession();
        List<Operation> list = session.createQuery("FROM Operation o WHERE o.idUser = " + idUser).list();
        return list;
    }

    @Override
    public int getBalans(int idoperation) {
        Session session = sessionFactory.openSession();
        List balansList = session.createQuery("SELECT o.balans FROM Operation o WHERE o.idoperation =" + idoperation).list();
        int balans = 0;
        for (Object object : balansList) {
            balans = (int) object;
        }
        return balans;
    }

    @Override
    public void addOperation(Operation operation) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(operation);
        session.getTransaction().commit();
        session.close();
    }
}
