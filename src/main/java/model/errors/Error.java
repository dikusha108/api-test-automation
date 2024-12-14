package model.errors;

import java.util.Objects;

public class Error {
    private String code;
    private String detail;

    public Error(String code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Error error = (Error) o;
        return Objects.equals(code, error.code) && Objects.equals(detail, error.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, detail);
    }
}
