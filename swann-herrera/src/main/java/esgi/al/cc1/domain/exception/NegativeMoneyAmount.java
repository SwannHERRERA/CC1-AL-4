package esgi.al.cc1.domain.exception;

public class NegativeMoneyAmount extends IllegalArgumentException {
  public NegativeMoneyAmount(long value) {
    super("Invalid creation of money with negative value : " + value);
  }
}
