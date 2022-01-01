package dev.devloup.shared.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class TransactionTest {
  private final Account reciver = Account.of(Money.ZERO);
  private final Account sender = Account.of(Money.of(500));

  @Test
  void test_creation_of_transaction() {
    var transaction = Transaction.create(reciver, sender, Money.of(20));
    Assertions.assertThat(transaction.getReciver()).isEqualTo(reciver);
    Assertions.assertThat(transaction.getSender()).isEqualTo(sender);
    Assertions.assertThat(transaction.getStatus()).isEqualTo(TransactionStatus.IN_PROGRESS);
    Assertions.assertThat(transaction.getAmount()).isEqualTo(Money.of(20));
  }

  @Test
  void test_creation_of_successful_transaction() {
    var transaction = Transaction.success(reciver, sender, Money.of(20));
    Assertions.assertThat(transaction.getReciver()).isEqualTo(reciver);
    Assertions.assertThat(transaction.getSender()).isEqualTo(sender);
    Assertions.assertThat(transaction.getStatus()).isEqualTo(TransactionStatus.SUCCESSED);
    Assertions.assertThat(transaction.getAmount()).isEqualTo(Money.of(20));
  }

  @Test
  void test_creation_of_failed_transaction() {
    var transaction = Transaction.failed(reciver, sender, Money.of(20));
    Assertions.assertThat(transaction.getReciver()).isEqualTo(reciver);
    Assertions.assertThat(transaction.getSender()).isEqualTo(sender);
    Assertions.assertThat(transaction.getStatus()).isEqualTo(TransactionStatus.ERROR);
    Assertions.assertThat(transaction.getAmount()).isEqualTo(Money.of(20));
  }
}
