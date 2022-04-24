package Commons;

import lombok.Getter;

public interface Type {
    enum V1_0 implements Type {
        STRING("string"),
        NUMBER("number");

        @Getter
        final String type;

        V1_0(String type) {
            this.type = type;
        }
    }

    enum V1_1 implements Type {
        BOOLEAN("boolean");

        @Getter
        final String type;

        V1_1(String type) {
            this.type = type;
        }
    }

    String getType();
}
