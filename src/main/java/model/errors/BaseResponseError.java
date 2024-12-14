package model.errors;

import java.lang.Error;

public class BaseResponseError {
    private java.lang.Error error;
    private String detail;

    public BaseResponseError(Error error) {
        this.error = error;
    }

    public BaseResponseError(String detail) {
        this.detail = detail;
    }
}
