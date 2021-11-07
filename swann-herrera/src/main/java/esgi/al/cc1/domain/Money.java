package esgi.al.cc1.domain;

import java.math.BigInteger;

import esgi.al.cc1.domain.exception.NegativeMoneyAmount;
import esgi.al.cc1.kernel.Entity;

@Entity
public class Money {
  public static final Money ZERO = Money.of(0);
  private final BigInteger amount;

  private Money(BigInteger amount) {
    this.amount = amount;
  }

  public boolean isGreaterThanOrEqualTo(Money money) {
    return this.amount.compareTo(money.amount) >= 0;
  }

  public boolean isGreaterThan(Money money) {
    return this.amount.compareTo(money.amount) >= 1;
  }

  public static Money of(long value) throws NegativeMoneyAmount {
    if (value < 0) {
      throw new NegativeMoneyAmount(value);
    }
    return new Money(BigInteger.valueOf(value));
  }

  public static Money add(Money a, Money b) {
    return new Money(a.amount.add(b.amount));
  }

  public static Money subtract(Money a, Money b) {
    return new Money(a.amount.subtract(b.amount));
  }

  public Money minus(Money money) {
    return new Money(this.amount.subtract(money.amount));
  }

  public Money plus(Money money) {
    return new Money(this.amount.add(money.amount));
  }

  public BigInteger getAmount() {
    return amount;
  }

  public String toString() {
    return "Amount : " + amount;
  }
}
