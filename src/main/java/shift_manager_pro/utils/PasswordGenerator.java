package shift_manager_pro.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordGenerator {

    public static void main(String[] args) {
        System.out.println("Encrypted Password : "+ BCrypt.hashpw(args[0], BCrypt.gensalt()));
    }
}
