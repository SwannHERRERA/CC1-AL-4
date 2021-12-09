package dev.devloup;

import dev.devloup.application.port.in.PaymentEvent;
import dev.devloup.domain.Listener;

public class DummyPaymentListener implements Listener<PaymentEvent> {
  @Override
  public void accept(PaymentEvent e) {
    // DO nothing
  }
}
