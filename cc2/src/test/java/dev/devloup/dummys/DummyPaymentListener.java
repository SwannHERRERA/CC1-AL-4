package dev.devloup.dummys;

import dev.devloup.core.Listener;
import dev.devloup.use_case.register.exposition.PaymentEvent;

public class DummyPaymentListener implements Listener<PaymentEvent> {

  @Override
  public void accept(PaymentEvent t) {
    // DO Nothing
  }

}
