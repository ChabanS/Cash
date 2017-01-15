package dao;

import java.util.List;
import model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Sergey
 */
public class AccountDaoImpl implements IAccountDao{

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = sessionFactory.openSession();
    
    @Override
    public List<Account> getAllAccount() {
        List<Account> list = session.createQuery("SELECT a FROM Account a").list();
        return list;
    }
}
