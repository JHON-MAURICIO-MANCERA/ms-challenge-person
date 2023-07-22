package co.com.sofka.model.enums;

import java.util.Arrays;

public enum PersonStatusEnums {
    ACTIVE("d91851c1-9ce9-4e78-8d48-cd55278a12dd"),
    DISABLED("6e84467e-2790-11ee-be56-0242ac120002");

    private final String id;

    PersonStatusEnums(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public static String nameFromId(String id) {
        return Arrays.stream(values())
                .filter(dse -> dse.getId().contentEquals(id))
                .map(Enum::name).findFirst()
                .orElseThrow(() -> new RuntimeException("OBJECT_STATUS_NOT_VALID"));
    }

    public static String idFromName(String name) {
        return Arrays.stream(values())
                .filter(dse -> dse.name().contentEquals(name))
                .map(PersonStatusEnums::getId).findFirst()
                .orElseThrow(() -> new RuntimeException("OBJECT_NAME_NOT_VALID"));
    }

    public static String getStatusId(String valueStatus) {
        return Arrays.stream(values()).filter(dse -> dse.name().contentEquals(valueStatus))
                .map(PersonStatusEnums::getId).findFirst().orElseGet(() -> idFromId(valueStatus));
    }

    private static String idFromId(String id) {
        return Arrays.stream(values())
                .filter(dse -> dse.getId().contentEquals(id))
                .map(idValue -> idValue.id).findFirst()
                .orElseThrow(() -> new RuntimeException("OBJECT_STATUS_NOT_VALID"));
    }
}
