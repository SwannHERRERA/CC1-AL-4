package esgi.al.cc1;

import java.util.UUID;

import esgi.al.cc1.domain.User;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        User user = User.of(UUID.randomUUID(), "firstName", "lastName", "email", 10);
        System.out.println(user);
        System.out.println("Hello World!");
    }
}
