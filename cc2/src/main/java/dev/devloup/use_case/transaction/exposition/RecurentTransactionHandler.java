package dev.devloup.use_case.transaction.exposition;

import java.util.List;

import dev.devloup.shared.domain.User;
import dev.devloup.use_case.register.domain.UserRepository;

public final class RecurentTransactionHandler {
  private final UserRepository userRepository;

  public RecurentTransactionHandler(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void batch() {
    List<User> activeUsers = userRepository.listAllActive();
    // TODO get que ce qui ont leur date de payement passé d'un mois
    // Pour tout les utilisateurs regarder si leur date de payement a passer
    // aujourd'hui, les faire renew leur contrat
  }

  public void startSubscribtion() {

  }
}
