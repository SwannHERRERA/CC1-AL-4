package esgi.al.cc1.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import esgi.al.cc1.infrastructure.FileLogger;

public class LoggerFactory {
  private LoggerFactory() {
    throw new AssertionError();
  }

  public static Logger createFileLogger(String logFilePathValue) {
    try {
      Path logFile = createFileIfNotExist(logFilePathValue);
      return new FileLogger(logFile);
    } catch (IOException ioe) {
      throw new IllegalStateException(ioe);
    }
  }

  private static Path createFileIfNotExist(String logFilePathValue) throws IOException {
    Path path = Path.of(logFilePathValue);

    final Path parentDirectory = path.getParent();
    if (!Files.isDirectory(parentDirectory)) {
      Files.createDirectory(parentDirectory);
    }
    if (Files.notExists(path)) {
      Files.createFile(path);
    }
    return path;
  }
}
