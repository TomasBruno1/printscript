package ContentProvider;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StringContentProvider implements ContentProvider {

    private String string;

    @Override
    public String getContent() {
        return string;
    }
}
