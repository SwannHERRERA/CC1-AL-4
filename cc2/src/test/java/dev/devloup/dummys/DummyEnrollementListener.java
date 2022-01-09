package dev.devloup.dummys;

import dev.devloup.core.ApplicationEventListener;
import dev.devloup.use_case.register.exposition.CreateUserEvent;

public class DummyEnrollementListener implements ApplicationEventListener<CreateUserEvent> {

  @Override
  public void accept(CreateUserEvent event) {
    // DO Nothing
  }

}
