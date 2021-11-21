package esgi.al.cc1.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

import esgi.al.cc1.Config;

public class LoggerTest {
  @Test
  void logger_test() {
    Path filePath = Path.of(Config.getLogFolder() + "test.log");
    Logger logger = LoggerFactory.createFileLogger(filePath.toString());
    String dummyContent = "test";

    logger.log(dummyContent);
    try {
      List<String> lines = Files.readAllLines(filePath, Charset.defaultCharset());
      assertEquals(1, lines.size());
      assertEquals(dummyContent, lines.get(0));
    } catch (Exception e) {
      fail(e);
    } finally {
      filePath.toFile().delete();
    }
  }
}
