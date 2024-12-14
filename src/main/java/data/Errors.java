package data;

import model.errors.BaseResponseError;
import model.errors.Detail;
import model.errors.Error;
import model.errors.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class Errors {
    public static BaseResponseError getIncorrectTokenError() {
        return new BaseResponseError (new Error("auth_failed", "Unauthorized"));
    }

    public static ValidationError getValidationError(String ...types) {
        List<Detail> details = new ArrayList<>();
        for (String type : types) {
            details.add(new Detail(type));
        }
        return new ValidationError(details);
    }

    public static BaseResponseError getNotFoundError() {
        return new BaseResponseError(new Error(
                "user_not_found",
                "User not found"
        ));
    }

    public static BaseResponseError getAlreadyExistError() {
        return new BaseResponseError(new Error(
                "already_exist",
                "User with this id_bitrix already exists"
        ));
    }

    public static BaseResponseError getLoginAlreadyExistError() {
        return new BaseResponseError(new Error(
                "login_already_in_use",
                "User with this login already exists"
        ));
    }
}