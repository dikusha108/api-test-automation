package b2b_user;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import model.b2b_user.B2bUserElement;
import model.errors.BaseResponseError;
import model.errors.Error;
import model.errors.ValidationError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.ParseException;
import java.util.HashMap;
import java.util.stream.Stream;

import static data.Errors.*;
import static data.TestParams.*;
import static data.TestParams.getNonexistentIdBitrix;
import static steps.B2bUserSteps.getB2bUserElement;
import static steps.BaseSteps.checkBody;

@Epic("b2b_users")
@Feature("GET /element")
public class GetB2bUserElementTest {
    private static Stream<Arguments> provideParamsForTestsWithExistingUser() {
        return Stream.of(
                Arguments.of(
                        "with valid id_bitrix",
                        "id_bitrix",
                        getIdBitrix()
                ),
                Arguments.of(
                        "with valid rs_id",
                        "rs_id",
                        getRsId()
                ),
                Arguments.of(
                        "with valid id",
                        "id",
                        getId()
                ),
                Arguments.of(
                        "with valid user_robotlogin",
                        "user_robotlogin",
                        getUserRobotLogin()
                )
        );
    }

    @ParameterizedTest(name = "{0}")
    @DisplayName("Get B2b user element")
    @MethodSource("provideParamsForTestsWithExistingUser")
    public void getB2bUserElementWithValidId(String description, String field, Object id) throws ParseException {
        // Create queryParams
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("field_id", field);
        queryParams.put("id", id);

        // Get user information
        B2bUserElement actual = getB2bUserElement(
                "correct_token",
                queryParams,
                200,
                B2bUserElement.class
        );

        // Create expected result
        B2bUserElement expected = new B2bUserElement(
                getRsId().doubleValue(),
                getIdBitrix(),
                getUserRegDate(),
                getUserFullName().split(" ")[0],
                getUserFullName().split(" ")[1],
                getUserFullName().split(" ")[2],
                getUserPhone(),
                getUserEmail(),
                null,
                getCorrId(),
                getUsId(),
                getUserStatus(),
                null,
                null,
                getUserIsRobot() == 1,
                getUserIp(),
                getUserRobotLogin(),
                getUserRobotPassword(),
                null,
                "UNKNOWN",
                null,
                false,
                true,
                getCrmPhone(),
                getCrmEmail(),
                "decision_block",
                getLastB2bAuthDate(),
                getLastOapiAuthDate(),
                getLastSoapAuthDate()
        );

        // Check that actual result is equal to expected result
        checkBody(actual, expected, "id");
    }

    private static Stream<Arguments> provideParamsForTestsWithNonexistentUser() {
        return Stream.of(
                Arguments.of(
                        "with nonexistent id_bitrix",
                        "id_bitrix"
                ),
                Arguments.of(
                        "with nonexistent rs_id",
                        "rs_id"
                ),
                Arguments.of(
                            "with nonexistent id",
                        "id"
                ),
                Arguments.of(
                        "with nonexistent user_robotlogin",
                        "user_robotlogin"
                )
        );
    }

    @ParameterizedTest(name = "{0}")
    @DisplayName("Get B2b user element")
    @MethodSource("provideParamsForTestsWithNonexistentUser")
    public void getB2bUserElementWithNonexistentUser(String description, String field){
        // Create queryParams
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("field_id", field);
        queryParams.put("id", getNonexistentIdBitrix());

        // Get user information
        BaseResponseError actual = getB2bUserElement(
                "correct_token",
                queryParams,
                404,
                BaseResponseError.class
        );

        // Check that actual result is equal to expected result
        checkBody(actual, getNotFoundError());
    }

    private static Stream<Arguments> provideParamsForTestsWithInvalidParams() {
        return Stream.of(
                Arguments.of(
                        "with invalid id_bitrix",
                        "id_bitrix",
                        getIdBitrix() + "qwe",
                        "value_error"
                ),
                Arguments.of(
                        "with invalid rs_id",
                        "rs_id",
                        getRsId() + "qwe",
                        "value_error"
                ),
                Arguments.of(
                        "with invalid id",
                        "id",
                        getId() + "qwe",
                        "value_error"
                ),
                Arguments.of(
                        "with invalid user_robotlogin",
                        "idqwe",
                        getIdBitrix(),
                        "type_error.enum"
                )
        );
    }

    @ParameterizedTest(name = "{0}")
    @DisplayName("Get B2b user element")
    @MethodSource("provideParamsForTestsWithInvalidParams")
    public void getB2bUserElementWithInvalidParams(String description, String field, Object id, String type){
        // Create queryParams
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("field_id", field);
        queryParams.put("id", id);

        // Get user information
        ValidationError actual = getB2bUserElement(
                "correct_token",
                queryParams,
                422,
                ValidationError.class
        );

        // Check that actual result is equal to expected result
        checkBody(actual, getValidationError(type));
    }

    private static Stream<Arguments> provideParamsForTestsWithMissingParam() {
        return Stream.of(
                Arguments.of(
                        "without field_id",
                        "id",
                        getIdBitrix(),
                        "value_error.missing"
                ),
                Arguments.of(
                        "without id",
                        "field_id",
                        "id_bitrix",
                        "value_error.missing"
                )
        );
    }
    @ParameterizedTest(name = "{0}")
    @DisplayName("Get B2b user element")
    @MethodSource("provideParamsForTestsWithMissingParam")
    public void getB2bUserElementWithMissingParam(String description, String field_name, Object field_value, String type){
        // Create queryParams
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(field_name, field_value);

        // Get user information
        ValidationError actual = getB2bUserElement(
                "correct_token",
                queryParams,
                422,
                ValidationError.class
        );

        // Check that actual result is equal to expected result
        checkBody(actual, getValidationError(type));
    }

    @Test
    @DisplayName("Get B2b user element without params")
    public void getB2bUserElementWithoutParams(){
        // Create queryParams
        HashMap<String, Object> queryParams = new HashMap<>();

        // Get user information
        ValidationError actual = getB2bUserElement(
                "correct_token",
                queryParams,
                422,
                ValidationError.class
        );

        // Check that actual result is equal to expected result
        checkBody(actual, getValidationError("value_error.missing", "value_error.missing"));
    }

    @Test
    @DisplayName("Get B2b user element with incorrect token")
    public void getB2bUserElementWithIncorrectToken(){
        // Create queryParams
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("field_id", "id");
        queryParams.put("id", getId());

        // Get user information
        BaseResponseError actual = getB2bUserElement(
                "incorrect_token",
                queryParams,
                401,
                BaseResponseError.class
        );

        // Check that actual result is equal to expected result
        checkBody(actual, getIncorrectTokenError());
    }
}
