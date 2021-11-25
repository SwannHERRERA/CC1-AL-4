package esgi.al.cc2.infrastructure;

import esgi.al.cc2.domain.Logger;

public final class NullLogger implements Logger {

  @Override
  public void log(String message) {
    // Do nothing
  }

}
