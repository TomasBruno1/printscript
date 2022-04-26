package CLI;

public enum Version {
    V1_0("1.0"),
    V1_1("1.1");

    private final String version;

    Version(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
}
