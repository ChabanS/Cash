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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import util.Hashing;
import util.Validator;

/**
 *
 * @author Sergey
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IUserDao userDao = new UserDaoImpl();
        Validator validator = new Validator();
        Hashing hashing = new Hashing();

        String username = request.getParameter("username");
        String pass = request.getParameter("password");
        String email = request.getParameter("email");
        if (validator.loginValidator(username) && validator.emailValidator(email) && validator.passValidator(pass)) {

            
            if (userDao.getEmail(email) != null) {
                request.setAttribute("regisrtationEmail", "Email already exists, try again!");
            } else {
                try {
                    userDao.addUser(new User(username, hashing.Sha1(pass), email));
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                }
                request.setAttribute("regisrtation", "Congratulation, registration complit!");
                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            }
        }
        request.setAttribute("regisrtation", "Registration fail, try again!");
        request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
    }
}
