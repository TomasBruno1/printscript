package CLI;

public enum Mode {
    Interpretation("interpretation"),
    Validation("validation");

    private final String mode;

    Mode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
