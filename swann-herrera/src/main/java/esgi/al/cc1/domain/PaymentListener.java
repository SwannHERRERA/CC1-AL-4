package esgi.al.cc1.domain;

public class PaymentListener implements Listener<PaymentEvent> {

  @Override
  public void accept(PaymentEvent t) {
    // TODO Auto-generated method stub
    System.out.println("END User Creation successful with payment");
  }
}
