package dev.devloup.shared.infrastructure;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import dev.devloup.core.Logger;

public class FileLogger implements Logger {
    private final Path logFile;

    public FileLogger(Path logFile) {
        this.logFile = logFile;
    }

    @Override
    public void log(String message) {
        try (FileWriter fileWriter = new FileWriter(logFile.toFile(), true)) {
            fileWriter.append(message);
            fileWriter.append(System.lineSeparator());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
