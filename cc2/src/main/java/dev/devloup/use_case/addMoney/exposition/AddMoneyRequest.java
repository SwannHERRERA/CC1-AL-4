package dev.devloup.use_case.addMoney.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public final class AddMoneyRequest {
  @Positive
  public int amount;
  @NotBlank
  public String userId;
}
