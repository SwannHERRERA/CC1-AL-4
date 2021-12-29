package dev.devloup.shared.domain;

import java.time.Period;
import java.time.ZonedDateTime;
import java.util.Objects;

public class UserSubscribtion {
  private UserStatus status;
  private ZonedDateTime lastBilling;
  private final Period billingFrequency;
  private final ZonedDateTime subscribtionDate;

  private UserSubscribtion(UserStatus status, ZonedDateTime lastBilling, Period billingFrequency,
      ZonedDateTime subscribtionDate) {
    this.status = Objects.requireNonNull(status);
    this.lastBilling = lastBilling;
    this.billingFrequency = Objects.requireNonNull(billingFrequency);
    this.subscribtionDate = Objects.requireNonNull(subscribtionDate);
  }

  public static UserSubscribtion newDefaultSubscribtion() {
    return new UserSubscribtion(UserStatus.CURRENTLY_AUDITED, null, Period.ofMonths(1), ZonedDateTime.now());
  }

  public void validate() {
    this.status = UserStatus.VERIFIED;
  }

  public void reject() {
    this.status = UserStatus.REJECTED;
  }

  public void renewSubscribtion() {
    this.lastBilling = ZonedDateTime.now();
  }

  public UserStatus getStatus() {
    return status;
  }

  public ZonedDateTime getLastBilling() {
    return lastBilling;
  }

  public Period getBillingFrequency() {
    return billingFrequency;
  }

  public ZonedDateTime getSubscribtionDate() {
    return subscribtionDate;
  }
}
