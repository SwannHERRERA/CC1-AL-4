package esgi.al.cc1.infrastructure;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import esgi.al.cc1.domain.Logger;

public class FileLogger implements Logger {
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
