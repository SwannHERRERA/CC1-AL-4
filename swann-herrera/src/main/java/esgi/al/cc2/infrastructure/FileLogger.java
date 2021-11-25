package esgi.al.cc2.infrastructure;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import esgi.al.cc2.domain.Logger;

public final class FileLogger implements Logger {
  private final Path logFile;

  public FileLogger(Path logFile) {
    this.logFile = logFile;
  }

  @Override
  public void log(String message) {
    try (FileWriter fileWriter = new FileWriter(logFile.toFile(), true)) {
      fileWriter.append(message);
      fileWriter.append("\n");
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

}
