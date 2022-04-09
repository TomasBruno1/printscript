package Interpreter;

public class Writer {

    private final StringBuffer buffer = new StringBuffer();

    public void write(String result) {
        buffer.append(result).append("\n");
    }

    public String read() {
        return buffer.toString();
    }
}
