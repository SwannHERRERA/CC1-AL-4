package esgi.al.cc1;

import esgi.al.cc1.commands.create_user.CreateUserCommand;
import esgi.al.cc1.domain.UserService;
import esgi.al.cc1.infrastructure.MemoryUserRepository;
import esgi.al.cc1.domain.EventBus;
import esgi.al.cc1.domain.Money;
import esgi.al.cc1.domain.PaymentEvent;
import esgi.al.cc1.domain.CreateUserEvent;
import esgi.al.cc1.domain.EnrollmentListener;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        var email = "swann@devloup.dev";
        var userRepo = new MemoryUserRepository();
        var listener = new EnrollmentListener();
        var createUserBus = new EventBus<CreateUserEvent>();
        var paymentBus = new EventBus<PaymentEvent>();
        var baseMoney = Money.of(Config.BASE_MONEY);
        var userService = new UserService(userRepo, createUserBus, paymentBus);
        createUserBus.registerListener(listener);
        userService.createUser(new CreateUserCommand("Swann", "HERRERA", email, 20, baseMoney));

    }
}
