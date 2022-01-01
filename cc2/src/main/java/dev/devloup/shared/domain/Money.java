package dev.devloup.shared.domain;

import java.math.BigInteger;
import java.util.Objects;

import dev.devloup.core.Entity;
import dev.devloup.shared.domain.exception.NegativeMoneyAmount;

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

  public static Money of(long value) throws NegativeMoneyAmount {
    if (value < 0) {
      throw new NegativeMoneyAmount(value);
    }
    return new Money(BigInteger.valueOf(value));
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

  public long toPrimitive() {
    return amount.longValue();
  }

  public String toString() {
    return "Amount : " + amount;
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Money other = (Money) obj;
    if (!other.getAmount().equals(amount)) {
      return false;
    }
    return true;
  }

}