package dev.devloup;

import dev.devloup.domain.Listener;

public class DummyEventListener implements Listener<DummyEvent> {
  @Override
  public void accept(DummyEvent t) {
  }
}