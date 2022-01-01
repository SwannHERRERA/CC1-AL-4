package dev.devloup.dummys;

import dev.devloup.core.Listener;
import dev.devloup.shared.domain.Transaction;

public class DummyTransactionListener implements Listener<Transaction> {

  @Override
  public void accept(Transaction event) {
    // Do nothing

  }

}
