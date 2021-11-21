package esgi.al.cc1.infrastructure;

import esgi.al.cc1.domain.Listener;
import esgi.al.cc1.domain.Logger;
import esgi.al.cc1.domain.PaymentEvent;

public class PaymentListener implements Listener<PaymentEvent> {
  private final Logger logger;

  public PaymentListener(Logger logger) {
    this.logger = logger;
  }

  @Override
  public void accept(PaymentEvent event) {
    logger.log(event.toString());
  }
}
