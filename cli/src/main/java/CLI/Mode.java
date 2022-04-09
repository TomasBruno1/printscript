package CLI;

public enum Mode {
    Interpretation("I"),
    Validation("V");

    private final String mode;

    Mode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
