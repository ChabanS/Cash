package dao;

import java.util.List;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Sergey
 */
public class UserDaoImpl implements IUserDao {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    @Override
    public List<User> getAll() {

        Session session = sessionFactory.openSession();
        List<User> userList = session.createQuery("User.findAll").list();
        return userList;
    }

    @Override
    public void addUser(User user) {
       Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public String getUser(String email) {
        Session session = sessionFactory.openSession();
        List<User> userList = session.createQuery("SELECT u.username FROM User u WHERE u.email ='"+email+"'").list();
        String userDB = null;
        for (Object userIter : userList) {
            userDB = userIter.toString();
        }
        return userDB;
    }

    @Override
    public String getPass(String email) {
        Session session = sessionFactory.openSession();
        List<User> passList = session.createQuery("SELECT u.userpassword FROM User u WHERE u.email ='"+email+"'").list();
        String passDB = null;
        for (Object passIter : passList) {
            passDB = passIter.toString();
        }
        return passDB;
    }

    @Override
    public String getEmail(String email) {
        Session session = sessionFactory.openSession();
        List<User> emailList = session.createQuery("SELECT u.email FROM User u WHERE u.email ='"+email+"'").list();
        String emailDB = null;
        for (Object emailIter : emailList) {
            emailDB = emailIter.toString();
        }
        return emailDB;
    }

    @Override
    public String getUserId(String email) {
        Session session = sessionFactory.openSession();
        List<User> emailList = session.createQuery("SELECT u.id FROM User u WHERE u.email ='"+email+"'").list();
        String idDB = null;
        for (Object emailIter : emailList) {
            idDB = emailIter.toString();
        }
        return idDB;
    }
}
