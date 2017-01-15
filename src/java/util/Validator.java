/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Sergey
 */
public class Validator {

    public boolean emailValidator(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        } else {
            Pattern pattern;
            Matcher matcher;
            pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            matcher = pattern.matcher(email);
            return matcher.matches();
        }
    }

    public boolean loginValidator(String login) {
        if (login == null || login.trim().isEmpty()) {
            return false;
        } else {
            Pattern pattern;
            Matcher matcher;
            pattern = Pattern.compile("^[a-zA-Z0-9._-]{3,45}$");
            matcher = pattern.matcher(login);
            return matcher.matches();
        }
    }
    public boolean passValidator(String pass) {
        if (pass == null || pass.trim().isEmpty()) {
            return false;
        } else {
            Pattern pattern;
            Matcher matcher;
            pattern = Pattern.compile("^[a-zA-Z0-9._-]{1,45}$");
            matcher = pattern.matcher(pass);
            return matcher.matches();
        }
    }
}
