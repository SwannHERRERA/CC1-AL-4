package esgi.al.cc2.infrastructure;

import esgi.al.cc2.domain.Listener;
import esgi.al.cc2.domain.Logger;
import esgi.al.cc2.domain.PaymentEvent;

public final class PaymentListener implements Listener<PaymentEvent> {
  private final Logger logger;

  public PaymentListener(Logger logger) {
    this.logger = logger;
  }

  @Override
  public void accept(PaymentEvent event) {
    logger.log(event.toString());
  }
}
