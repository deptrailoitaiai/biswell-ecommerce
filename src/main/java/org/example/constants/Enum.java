package org.example.constants;

public class Enum {
    public enum RoleEnum {
        ADMIN,
        CUSTOMER
    }

    public enum ActionEnum {
        CREATE,
        READ,
        UPDATE,
        DELETE
    }

    public enum ModuleEnum {
        AUTHOR,
        ALBUM,
        ODER,
        PRODUCT
    }

    public enum OrderStatusEnum {
        CREATED,
        PROCESSING,
        SHIPPED,
        CANCELLED
    }
}
