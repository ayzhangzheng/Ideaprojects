package utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password="123";
        String pwd = encodePassword(password);//$2a$10$D1Nxfg8nkNbDoL6Qw5udWOm47z5Yb/0YM2VIz0ZFPAYj/tjYRe/pO
        System.out.println(pwd);
        System.out.print(pwd.length());
    }
}
