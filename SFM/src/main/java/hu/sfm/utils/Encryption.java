

package hu.sfm.utils;

import java.util.Random;

public class Encryption {
    static Random r = new Random();

    public Encryption() {
    }

    public static char hashedChar(int realCharValue) {
        if (realCharValue - 66 < 33) {
            return (char)realCharValue;
        } else {
            char ch;
            do {
                do {
                    ch = (char)(r.nextInt(realCharValue - 66) + 33);
                } while(ch == '$');
            } while(realCharValue - ch == 36 || ch == ';' || realCharValue - ch == 59);

            return ch;
        }
    }

    public static String titkosit(String password) {
        StringBuilder hashedPass = new StringBuilder();
        char[] var3 = password.toCharArray();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            char karakter = var3[var5];
            karakter = (char)(karakter + 150);

            while(karakter != 0) {
                char ch = hashedChar(karakter);
                karakter -= ch;
                hashedPass.append(ch);
            }

            hashedPass.append("$");
        }

        return hashedPass.toString();
    }

    public static String visszafejt(String hashedPass) {
        StringBuilder visszafejtettPass = new StringBuilder();
        int osszeg = 0;
        char[] var4 = hashedPass.toCharArray();
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            char karakter = var4[var6];
            if (karakter == '$') {
                visszafejtettPass.append((char)(osszeg - 150));
                osszeg = 0;
            } else {
                osszeg += karakter;
            }
        }

        return visszafejtettPass.toString();
    }
}
