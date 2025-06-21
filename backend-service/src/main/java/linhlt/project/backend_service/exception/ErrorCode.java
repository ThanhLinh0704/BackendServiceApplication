package linhlt.project.backend_service.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(999, "uncategorized exception"),
    USER_EXISTED(1001, "User already existed"),
    PASSWORD_INVALID(1003, "password must at least 5 characters"),

    UNAUTHENTICATION(1004, "unauthentication"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
