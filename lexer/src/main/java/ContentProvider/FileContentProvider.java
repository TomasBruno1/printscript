package ContentProvider;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class FileContentProvider implements ContentProvider {
    private final File content;

    public FileContentProvider(File content) {
        this.content = content;
    }

    @SneakyThrows
    @Override
    public String getContent() {
        return Files.readString(content.toPath(), StandardCharsets.US_ASCII);
    }
}
