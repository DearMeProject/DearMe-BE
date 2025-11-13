package com.dearme.backend.dearmebe.global.exception;

public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;
    private final String errorMsg;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.errorMsg = errorCode.getMessage();
    }

    public CustomException(ErrorCode errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return errorMsg;
    }
}
