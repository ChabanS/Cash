/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.IUserDao;
import dao.UserDaoImpl;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.Hashing;
import util.Validator;

/**
 *
 * @author CodeFire
 */
@WebServlet("/login")
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Pipets GET");
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Hashing hashing = new Hashing();
        Validator validator = new Validator();
        IUserDao userDao = new UserDaoImpl();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passHex = null;
        System.out.println("Pipets");
        System.out.println(email);
        System.out.println(userDao.getEmail(email));
        if (validator.emailValidator(email) && validator.passValidator(password)) {
            try {
                passHex = hashing.Sha1(password);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(IndexServlet.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
            if (userDao.getEmail(email) != null) {
                System.out.println(userDao.getEmail(email));
                if (userDao.getEmail(email).equals(email) && userDao.getPass(email).equals(passHex)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", userDao.getUser(email));
                    session.setAttribute("idUser", userDao.getUserId(email));
                    System.out.println(userDao.getUserId(email));
                    session.setAttribute("auth", true);
                    response.sendRedirect("cash");
                    return;
                }
            }

        }
        request.setAttribute("error", "Incorrect login or password!");
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

}
