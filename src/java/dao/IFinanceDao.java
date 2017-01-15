/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import model.Fin;

/**
 *
 * @author Sergey
 */
public interface IFinanceDao {
    
    List<Fin> getAll(int idUser);
    void addFin(Fin fin);
    String sumAll(int idUser);
    List<Fin> getSort(int idAccount, Date date1, Date date2, int idUser);
    String sumSort(int idAccount, Date date1, Date date2, int idUser);
    void delRow(int id);
    List<Fin> allRecordsByIdAccount(int idAccount, int idUser);
    String sumByIdAccount(int idAccount, int idUser);
}
