package dev.devloup.shared.domain;

import java.time.Period;
import java.time.ZonedDateTime;
import java.util.Objects;

import dev.devloup.core.Config;

public final class UserSubscribtion {
  private final UserStatus status;
  private final ZonedDateTime lastBilling;
  private final Period billingFrequency;
  private final ZonedDateTime subscribtionDate;
  private final UserId userId;

  private UserSubscribtion(UserStatus status, ZonedDateTime lastBilling, Period billingFrequency,
      ZonedDateTime subscribtionDate, UserId userId) {
    this.status = Objects.requireNonNull(status);
    this.lastBilling = lastBilling;
    this.billingFrequency = Objects.requireNonNull(billingFrequency);
    this.subscribtionDate = Objects.requireNonNull(subscribtionDate);
    this.userId = Objects.requireNonNull(userId);
  }

  public static UserSubscribtion of(UserStatus status, ZonedDateTime lastBilling, Period billingFrequency,
      ZonedDateTime subscribtionDate, UserId userId) {
    return new UserSubscribtion(status, lastBilling, billingFrequency, subscribtionDate, userId);
  }

  public static UserSubscribtion newDefaultSubscribtion(UserId id) {
    return new UserSubscribtion(UserStatus.CURRENTLY_AUDITED, null, Config.DEFAULT_SUBSCRIBTION_PERIOD,
        ZonedDateTime.now(), id);
  }

  public UserSubscribtion validate(ZonedDateTime billingDate) {
    return new UserSubscribtion(UserStatus.VERIFIED, billingDate, Config.DEFAULT_SUBSCRIBTION_PERIOD,
        this.subscribtionDate, this.userId);
  }

  public UserSubscribtion reject(ZonedDateTime billingDate) {
    return new UserSubscribtion(UserStatus.REJECTED, billingDate, Config.DEFAULT_SUBSCRIBTION_PERIOD,
        this.subscribtionDate, this.userId);
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
