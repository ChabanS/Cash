/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Fin;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Sergey
 */
public class FinanceDaoImpl implements IFinanceDao {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<Fin> getAll(int idUser) {
        Session session = sessionFactory.openSession();
        List<Fin> list = session.createQuery("SELECT f FROM Fin f WHERE f.idUser = " + idUser).list();
        session.close();
        return list;
    }

    @Override
    public void addFin(Fin fin) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(fin);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public String sumAll(int idUser) {
        Session session = sessionFactory.openSession();

        List sumList = session.createQuery("SELECT SUM(f.finrez) as finrez FROM Fin f WHERE f.idUser = " + idUser).list();
        String sum = "";
        System.out.println(sumList);

        for (Object object : sumList) {
            if (object != null) {
                sum = object.toString();
            } else {
                sum = "0";
            }
        }

        session.close();
        return sum;
    }

    @Override
    public List<Fin> getSort(int idAccount, Date date1, Date date2, int idUser
    ) {
        Session session = sessionFactory.openSession();
        List<Fin> list = null;

        if (idAccount == 0) {
            list = session.createQuery("FROM Fin f where f.idUser = " + idUser + " and f.data BETWEEN '"
                    + new SimpleDateFormat("yyyy-MM-dd").format(date1) + "' AND '"
                    + new SimpleDateFormat("yyyy-MM-dd").format(date2) + "'").list();
        } else {
            list = session.createQuery("FROM Fin f where f.idUser = " + idUser + " and f.data BETWEEN '"
                    + new SimpleDateFormat("yyyy-MM-dd").format(date1) + "' AND '"
                    + new SimpleDateFormat("yyyy-MM-dd").format(date2) + "' AND f.account=" + idAccount).list();
        }

        return list;
    }

    @Override
    public String sumSort(int idAccount, Date date1, Date date2, int idUser
    ) {
        Session session = sessionFactory.openSession();
        List sumList;
        if (idAccount == 0) {
            sumList = session.createSQLQuery("SELECT SUM(f.finrez) as finrez FROM fin f where f.idUser = " + idUser + " and f.data BETWEEN '"
                    + new SimpleDateFormat("yyyy-MM-dd").format(date1) + "' AND '"
                    + new SimpleDateFormat("yyyy-MM-dd").format(date2) + "'").list();
        } else {
            sumList = session.createSQLQuery("SELECT SUM(f.finrez) as finrez FROM fin f where f.idUser = " + idUser + " and f.data BETWEEN '"
                    + new SimpleDateFormat("yyyy-MM-dd").format(date1) + "' AND '"
                    + new SimpleDateFormat("yyyy-MM-dd").format(date2) + "' AND f.account=" + idAccount).list();
        }
        String sum = "0";
        for (Object object : sumList) {
            if (object != null) {
                sum = object.toString();
            }
            session.close();
        }
        return sum;

    }

    @Override
    public void delRow(int id
    ) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        Query query = session.getNamedQuery("Fin.findById").setInteger("id", id);
        Fin fin = (Fin) query.uniqueResult();
        session.delete(fin);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Fin> allRecordsByIdAccount(int idAccount, int idUser
    ) {
        Session session = sessionFactory.openSession();
        List<Fin> list;
        if (idAccount == 0) {
            list = session.createQuery("SELECT f FROM Fin f WHERE f.idUser = " + idUser).list();
        } else {
            list = session.createQuery("SELECT f FROM Fin f "
                    + "where f.account=" + idAccount + " and f.idUser = " + idUser).list();
        }
        return list;
    }

    @Override
    public String sumByIdAccount(int idAccount, int idUser
    ) {
        Session session = sessionFactory.openSession();
        List sumList;
        String sum = null;
        if (idAccount != 0) {
            sumList = session.createSQLQuery("SELECT SUM(f.finrez) as finrez "
                    + "FROM fin f where f.account=" + idAccount + " and f.idUser = " + idUser).list();
            for (Object object : sumList) {
                if (object != null) {
                    sum = object.toString();
                }
            }
            session.close();
        } else {
            sum = sumAll(idUser);
        }
        return sum;
    }

}
