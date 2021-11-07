package esgi.al.cc1.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class AccountTest {
  private final EventBus<PaymentEvent> bus = new EventBus<>();

  @Test
  void test_creation() {
    var money = Money.of(200);
    var account = Account.of(money, bus);
    account.getId();
    assertEquals(money, account.getBalance());
  }

  @Test
  void test_creation_with_custom_id() {
    var uuid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
    var money = Money.of(10);
    var account = Account.withUUID(uuid, money, bus);
    assertEquals(account.getId(), uuid);
    assertEquals(money, account.getBalance());
  }

  @Test
  void test_creation_with_balance_at_0() {
    var account = Account.of(Money.ZERO, bus);
    assertEquals(Money.ZERO, account.getBalance());
  }

  @Test
  void test_creation_with_negative_balance() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> Money.of(-50));
    assertTrue(exception.getMessage().contains("balance must be positive"));
  }
}
