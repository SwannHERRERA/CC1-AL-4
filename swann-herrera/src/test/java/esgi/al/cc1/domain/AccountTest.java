package esgi.al.cc1.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import esgi.al.cc1.domain.exception.NegativeMoneyAmount;

public class AccountTest {
  private final EventBus<PaymentEvent> bus = new EventBus<>();

  private ArgumentCaptor<PaymentEvent> paymentCaptor = ArgumentCaptor.forClass(PaymentEvent.class);

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
    assertThrows(NegativeMoneyAmount.class, () -> Money.of(-50));
  }

  @Test
  void test_send_money() {
    var money = Money.of(200);
    var sender = Account.of(money, bus);
    var reciver = Account.of(Money.ZERO, bus);
    var moneySend = Money.of(20);
    assertTrue(sender.sendMoney(moneySend, reciver));
    assertEquals(moneySend.getAmount(), reciver.getBalance().getAmount());
  }

  @Test
  void test_send_more_money_money_than_account_have() {
    var money = Money.of(20);
    var sender = Account.of(money, bus);
    var reciver = Account.of(Money.ZERO, bus);
    var moneySend = Money.of(200);
    assertFalse(sender.sendMoney(moneySend, reciver));
    assertEquals(money.getAmount(), sender.getBalance().getAmount());
    assertEquals(Money.ZERO.getAmount(), reciver.getBalance().getAmount());
  }

  @Test
  void test_event_bus_is_imformed() {
    var money = Money.of(200);
    var moneySend = Money.of(20);
    var sender = Account.of(money, bus);
    var reciver = Account.of(Money.ZERO, bus);
    var listener = Mockito.spy(new PaymentListener());
    bus.registerListener(listener);
    assertTrue(sender.sendMoney(moneySend, reciver));
    verify(listener, times(1)).accept(paymentCaptor.capture());
  }
}
