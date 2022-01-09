package dev.devloup.dummys;

import dev.devloup.core.Listener;
import dev.devloup.shared.domain.Transaction;

public class DummyPaymentListener implements Listener<Transaction> {

  @Override
  public void accept(Transaction t) {
    // DO Nothing
  }

}
