package esgi.al.cc1;

import esgi.al.cc1.domain.UserService;
import esgi.al.cc1.domain.commands.create_user.CreateUserCommand;
import esgi.al.cc1.infrastructure.EnrollmentListener;
import esgi.al.cc1.infrastructure.MemoryUserRepository;
import esgi.al.cc1.infrastructure.PaymentListener;
import esgi.al.cc1.domain.EventBus;
import esgi.al.cc1.domain.LoggerFactory;
import esgi.al.cc1.domain.Money;
import esgi.al.cc1.domain.PaymentEvent;
import esgi.al.cc1.domain.Account;
import esgi.al.cc1.domain.CreateUserEvent;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        var logger = LoggerFactory.createFileLogger(Config.getLogFolder() + "file.log");

        var createUserBus = new EventBus<CreateUserEvent>();
        var paymentBus = new EventBus<PaymentEvent>();
        paymentBus.registerListener(new PaymentListener(logger));
        var email = "swann@devloup.dev";
        var userRepo = new MemoryUserRepository();
        var appAccount = Account.of(Money.ZERO, paymentBus);
        var listener = new EnrollmentListener(appAccount, logger);
        var baseMoney = Money.of(Config.BASE_MONEY);
        var userService = new UserService(userRepo, createUserBus, paymentBus);
        createUserBus.registerListener(listener);
        userService.createUser(new CreateUserCommand("Swann", "HERRERA", email, 20, baseMoney));

        var user = userRepo.findByEmail(email);
        if (user.isPresent()) {
            logger.log(user.get().getAccount().getBalance().toString());
        } else {
            logger.log("user not found");
        }
    }
}
