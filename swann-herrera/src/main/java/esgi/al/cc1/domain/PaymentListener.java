package esgi.al.cc1.domain;

public class PaymentListener implements Listener<PaymentEvent> {
  private final Logger logger;

  public PaymentListener(Logger logger) {
    this.logger = logger;
  }

  @Override
  public void accept(PaymentEvent t) {
    logger.log(t.toString());
  }
}
