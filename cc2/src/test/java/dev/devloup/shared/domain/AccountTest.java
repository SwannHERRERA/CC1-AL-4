package dev.devloup.shared.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.ZonedDateTime;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import dev.devloup.shared.domain.exception.NegativeMoneyAmount;

final class AccountTest {
  @Test
  void test_creation() {
    var money = Money.of(200);
    var account = Account.of(money);
    account.getId();
    assertEquals(money, account.getBalance());
  }

  @Test
  void test_creation_with_custom_id() {
    var uuid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
    var money = Money.of(10);
    var account = Account.withUUID(uuid, money);
    assertEquals(account.getId(), uuid);
    assertEquals(money, account.getBalance());
  }

  @Test
  void test_creation_with_balance_at_0() {
    var account = Account.of(Money.ZERO);
    assertEquals(Money.ZERO, account.getBalance());
  }

  @Test
  void test_creation_with_negative_balance() {
    assertThrows(NegativeMoneyAmount.class, () -> Money.of(-50));
  }

  @Test
  void test_send_money() {
    var money = Money.of(200);
    var sender = Account.of(money);
    var reciver = Account.of(Money.ZERO);
    var moneySend = Money.of(20);
    var transaction = sender.sendMoney(moneySend, reciver);

    Assertions.assertThat(transaction.getAmount()).isEqualTo(moneySend);
    Assertions.assertThat(Money.subtract(money, moneySend)).isEqualTo(sender.getBalance());
    Assertions.assertThat(Money.subtract(money, moneySend).getAmount()).isEqualTo(sender.getBalance().getAmount());
    Assertions.assertThat(Money.of(180)).hasToString(sender.getBalance().toString());
    Assertions.assertThat(transaction.getStatus()).isEqualTo(TransactionStatus.SUCCESSED);
    Assertions.assertThat(transaction.getSender()).isEqualTo(sender);
    Assertions.assertThat(transaction.getReciver()).isEqualTo(reciver);
    Assertions.assertThat(transaction.getOccurenceDate()).isBetween(ZonedDateTime.now().minusHours(1),
        ZonedDateTime.now());
  }

  @Test
  void test_send_more_money_money_than_account_have() {
    var money = Money.of(20);
    var sender = Account.of(money);
    var reciver = Account.of(Money.ZERO);
    var moneySend = Money.of(200);
    var transaction = sender.sendMoney(moneySend, reciver);
    Assertions.assertThat(transaction.getStatus()).isEqualTo(TransactionStatus.ERROR);
    Assertions.assertThat(money).isEqualTo(sender.getBalance());
    Assertions.assertThat(reciver.getBalance()).isEqualTo(Money.ZERO);
  }
}
