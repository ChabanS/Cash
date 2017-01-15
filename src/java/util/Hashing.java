/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import java.security.*;
/**
 *
 * @author Sergey
 */
public class Hashing {
    
    public String Sha1(String pass) throws NoSuchAlgorithmException{
        byte [] passByte = pass.getBytes();
        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
        messageDigest.update(passByte);
        byte [] digest = messageDigest.digest(passByte);
        StringBuffer hexPass = new StringBuffer();
        for (int i = 0; i < digest.length; i++) {
            hexPass.append(Integer.toString((digest[i]&0xff)+0x100,16).substring(1));
        }
        return hexPass.toString();
    }
}
