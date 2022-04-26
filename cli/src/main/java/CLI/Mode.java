package CLI;

public enum Mode {
    Interpretation("i"),
    Validation("v");

    private final String mode;

    Mode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
