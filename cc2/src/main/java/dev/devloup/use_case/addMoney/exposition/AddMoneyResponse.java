package dev.devloup.use_case.addMoney.exposition;

import java.util.Objects;

import dev.devloup.shared.domain.Money;
import dev.devloup.shared.domain.Transaction;
import dev.devloup.shared.exposition.UserResponse;

public final class AddMoneyResponse {
  public Transaction transaction;
  public UserResponse userResponse;
  public Money userBalance;

  public AddMoneyResponse(Transaction transaction, UserResponse userResponse, Money userBalance) {
    this.transaction = Objects.requireNonNull(transaction);
    this.userResponse = Objects.requireNonNull(userResponse);
    this.userBalance = Objects.requireNonNull(userBalance);
  }
}
