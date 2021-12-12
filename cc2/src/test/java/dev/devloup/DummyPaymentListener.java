package dev.devloup;

import dev.devloup.core.ApplicationEventListener;

public class DummyPaymentListener implements ApplicationEventListener<DummyEvent> {
  @Override
  public void listenTo(DummyEvent event) {
    // Do nothing
  }
}
