package dev.devloup.exposition;

import dev.devloup.use_case.transaction.exposition.RecurentTransactionHandler;
import io.quarkus.scheduler.Scheduled;

public final class FinanceController {
  private final RecurentTransactionHandler recurentTransactionHandler;

  public FinanceController(RecurentTransactionHandler recurentTransactionHandler) {
    this.recurentTransactionHandler = recurentTransactionHandler;
  }

  @Scheduled(every = "P1D")
  public void checkForPayment() {
    recurentTransactionHandler.checkForPayment();
  }
}