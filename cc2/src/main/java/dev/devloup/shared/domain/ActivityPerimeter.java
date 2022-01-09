package dev.devloup.shared.domain;

import java.util.Objects;

public final class ActivityPerimeter {
  private final double longitude;
  private final double latitude;
  private final double activityRadius;

  private ActivityPerimeter(double longitude, double latitude, double activityRadius) {
    this.longitude = Objects.requireNonNull(longitude);
    this.latitude = Objects.requireNonNull(latitude);
    this.activityRadius = Objects.requireNonNull(activityRadius);
  }

  public static ActivityPerimeter of(double longitude, double latitude, double activityRadius) {
    return new ActivityPerimeter(longitude, latitude, activityRadius);
  }

  public double getLongitude() {
    return longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getActivityRadius() {
    return activityRadius;
  }

  @Override
  public int hashCode() {
    return Objects.hash(longitude, latitude, activityRadius);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ActivityPerimeter other = (ActivityPerimeter) obj;
    if (Double.doubleToLongBits(activityRadius) != Double.doubleToLongBits(other.activityRadius))
      return false;
    if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
      return false;
    if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
      return false;
    return true;
  }

}
