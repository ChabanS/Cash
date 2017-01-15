/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.User;

/**
 *
 * @author Sergey
 */
public interface IUserDao {
    
    List<User> getAll();
    void addUser(User user);
    String getUser(String email);
    String getPass(String email);
    String getEmail(String email);
    String getUserId(String email);
    
}
