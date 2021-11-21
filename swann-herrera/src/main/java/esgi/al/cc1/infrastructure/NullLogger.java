package esgi.al.cc1.infrastructure;

import esgi.al.cc1.domain.Logger;

public class NullLogger implements Logger {

  @Override
  public void log(String message) {
    // Do nothing
  }

}
