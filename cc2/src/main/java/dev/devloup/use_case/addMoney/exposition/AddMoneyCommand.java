package dev.devloup.use_case.addMoney.exposition;

import java.util.Objects;

import dev.devloup.shared.domain.Money;
import dev.devloup.shared.domain.UserId;

public final class AddMoneyCommand {
  public Money amount;
  public UserId userId;

  public AddMoneyCommand(Money amount, UserId userId) {
    this.amount = Objects.requireNonNull(amount);
    this.userId = Objects.requireNonNull(userId);
  }

}
