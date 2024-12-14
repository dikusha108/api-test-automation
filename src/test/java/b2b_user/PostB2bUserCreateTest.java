package b2b_user;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import model.b2b_user.B2bUserElement;
import model.b2b_user.B2bUserElementAction;
import model.b2b_user.B2bUserElementGetter;
import model.errors.BaseResponseError;
import model.errors.Error;
import model.errors.ValidationError;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.LocalDateTime;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.stream.Stream;

import static data.Errors.*;
import static data.RequestBody.getB2bUserCreateRequestBody;
import static data.TestParams.getIdBitrix;
import static data.TestParams.getUserRobotLogin;
import static steps.B2bUserSteps.getB2bUserElement;
import static steps.B2bUserSteps.getMaxUid;
import static steps.B2bUserSteps.postB2bUserCreate;
import static steps.BaseSteps.checkBody;

@Epic("b2b_users")
@Feature("POST /create")
@Execution(ExecutionMode.SAME_THREAD)
public class PostB2bUserCreateTest {
    private static Stream<Arguments> provideParamsForTestsWithValidParams() {
        return Stream.of(
                Arguments.of(
                        "registration_scenario = INTERNAL",
                        (B2bUserElementAction) (element) -> element.setRegistrationScenario("INTERNAL")
                ),
                Arguments.of(
                        "registration_scenario = ADDITIONAL",
                        (B2bUserElementAction) (element) -> element.setRegistrationScenario("ADDITIONAL")
                ),
                Arguments.of(
                        "holding_admin_process_status = NO_ADMINS",
                        (B2bUserElementAction) (element) -> element.setHoldingAdminProcessStatus("NO_ADMINS")
                ),
                Arguments.of(
                        "holding_admin_process_status = WAITING_ADMINS",
                        (B2bUserElementAction) (element) -> element.setHoldingAdminProcessStatus("WAITING_ADMINS")
                ),
                Arguments.of(
                        "holding_admin_process_status = APPROVED",
                        (B2bUserElementAction) (element) -> element.setHoldingAdminProcessStatus("APPROVED")
                ),
                Arguments.of(
                        "holding_admin_process_status = BLOCKED",
                        (B2bUserElementAction) (element) -> element.setHoldingAdminProcessStatus("BLOCKED")
                ),
                Arguments.of(
                        "holding_admin_process_status = ERROR",
                        (B2bUserElementAction) (element) -> element.setHoldingAdminProcessStatus("ERROR")
                ),
                Arguments.of(
                        "last_b2b_auth_date",
                        (B2bUserElementAction) (element) -> element.setLastB2bAuthDate(LocalDateTime.now().toString() + "Z")
                ),
                Arguments.of(
                        "last_oapi_auth_date",
                        (B2bUserElementAction) (element) -> element.setLastOapiAuthDate(LocalDateTime.now().toString() + "Z")
                ),
                Arguments.of(
                        "last_soap_auth_date",
                        (B2bUserElementAction) (element) -> element.setLastSoapAuthDate(LocalDateTime.now().toString() + "Z")
                )
        );
    }
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideParamsForTestsWithValidParams")
    @DisplayName("Create new user with valid params")
    public void postB2bUserCreateWithValidParamsParametrized(String description, B2bUserElementAction action){
        B2bUserElement body = getB2bUserCreateRequestBody();
        body.setIdBitrix(getMaxUid().toString());
        action.performAction(body);

        // Create new user
        B2bUserElement actualCreate = postB2bUserCreate(
                "correct_token",
                body,
                200,
                B2bUserElement.class
        );

        // Check that actual result is equal to expected result
        checkBody(actualCreate, body, "user_reg_date");

        // Create queryParams
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("field_id", "id_bitrix");
        queryParams.put("id", Integer.parseInt(actualCreate.getIdBitrix()));

        // Get user information
        B2bUserElement actual = getB2bUserElement(
                "correct_token",
                queryParams,
                200,
                B2bUserElement.class
        );

        // Check that actual result is equal to expected result
        checkBody(actual, body, "user_reg_date");
    }

    @Test
    @DisplayName("Create new user with valid params")
    public void postB2bUserCreateWithValidParams(){
        B2bUserElement body = getB2bUserCreateRequestBody();
        body.setIdBitrix(getMaxUid().toString());

        // Create new user
        B2bUserElement actualCreate = postB2bUserCreate(
                "correct_token",
                body,
                200,
                B2bUserElement.class
        );

        // Check that actual result is equal to expected result
        checkBody(actualCreate, body, "user_reg_date");

        // Create queryParams
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("field_id", "id_bitrix");
        queryParams.put("id", Integer.parseInt(actualCreate.getIdBitrix()));

        // Get user information
        B2bUserElement actual = getB2bUserElement(
                "correct_token",
                queryParams,
                200,
                B2bUserElement.class
        );

        // Check that actual result is equal to expected result
        checkBody(actual, body, "user_reg_date");
    }

    private static Stream<Arguments> provideParamsForTestsWithInvalidParams() {
        return Stream.of(
                Arguments.of(
                        "with invalid id_bitrix",
                        (B2bUserElementAction) (element) -> element.setIdBitrix("qwe"),
                        "type_error.integer"
                ),
                Arguments.of(
                        "with invalid user_lastname",
                        (B2bUserElementAction) (element) -> element.setUserLastname(RandomStringUtils.random(256, true, true)),
                        "value_error.any_str.max_length"
                ),
                Arguments.of(
                        "with invalid user_name",
                        (B2bUserElementAction) (element) -> element.setUserName(RandomStringUtils.random(256, true, true)),
                        "value_error.any_str.max_length"
                ),
                Arguments.of(
                        "with invalid user_surname",
                        (B2bUserElementAction) (element) -> element.setUserSurname(RandomStringUtils.random(256, true, true)),
                        "value_error.any_str.max_length"
                ),
                Arguments.of(
                        "with invalid user_phone",
                        (B2bUserElementAction) (element) -> element.setUserPhone(RandomStringUtils.random(256, true, true)),
                        "value_error.any_str.max_length"
                ),
                Arguments.of(
                        "with invalid user_email",
                        (B2bUserElementAction) (element) -> element.setUserEmail(RandomStringUtils.random(65, true, true)),
                        "value_error.any_str.max_length"
                ),
                Arguments.of(
                        "with invalid comment",
                        (B2bUserElementAction) (element) -> element.setComment(RandomStringUtils.random(256, true, true)),
                        "value_error.any_str.max_length"
                ),
                Arguments.of(
                        "with invalid corr_id",
                        (B2bUserElementAction) (element) -> element.setCorrId(RandomStringUtils.random(5, true, true)),
                        "type_error.integer"
                ),
                Arguments.of(
                        "with invalid user_status",
                        (B2bUserElementAction) (element) -> element.setUserStatus(RandomStringUtils.random(5, true, true)),
                        "type_error.enum"
                ),
                Arguments.of(
                        "with invalid user_org_name",
                        (B2bUserElementAction) (element) -> element.setUserOrgName(RandomStringUtils.random(256, true, true)),
                        "value_error.any_str.max_length"
                ),
                Arguments.of(
                        "with invalid user_lockreason",
                        (B2bUserElementAction) (element) -> element.setUserLockreason(RandomStringUtils.random(255, true, true)),
                        "value_error.any_str.max_length"
                ),
                Arguments.of(
                        "with invalid user_isrobot",
                        (B2bUserElementAction) (element) -> element.setUserIsrobot(RandomStringUtils.random(255, true, true)),
                        "type_error.bool"
                ),
                Arguments.of(
                        "with invalid user_ip",
                        (B2bUserElementAction) (element) -> element.setUserIp(RandomStringUtils.random(255, true, true)),
                        "value_error.any_str.max_length"
                ),
                Arguments.of(
                        "with invalid user_robotlogin",
                        (B2bUserElementAction) (element) -> element.setUserRobotlogin(RandomStringUtils.random(255, true, true)),
                        "value_error.any_str.max_length"
                ),
                Arguments.of(
                        "with invalid user_robotpassword",
                        (B2bUserElementAction) (element) -> element.setUserRobotpassword(RandomStringUtils.random(41, true, true)),
                        "value_error.any_str.max_length"
                ),
                Arguments.of(
                        "with invalid note",
                        (B2bUserElementAction) (element) -> element.setNote(RandomStringUtils.random(255, true, true)),
                        "value_error.any_str.max_length"
                ),
                Arguments.of(
                        "id_bitrix = null",
                        (B2bUserElementAction) (element) -> element.setIdBitrix(null),
                        "value_error.missing"
                ),
                Arguments.of(
                        "user_lastname = null",
                        (B2bUserElementAction) (element) -> element.setUserLastname(null),
                        "value_error.missing"
                ),
                Arguments.of(
                        "user_name = null",
                        (B2bUserElementAction) (element) -> element.setUserName(null),
                        "value_error.missing"
                ),
                Arguments.of(
                        "user_surname = null",
                        (B2bUserElementAction) (element) -> element.setUserSurname(null),
                        "value_error.missing"
                ),
                Arguments.of(
                        "user_email = null",
                        (B2bUserElementAction) (element) -> element.setUserEmail(null),
                        "value_error.missing"
                ),
                Arguments.of(
                        "user_email = null",
                        (B2bUserElementAction) (element) -> element.setUserIsrobot(null),
                        "value_error.missing"
                ),
                Arguments.of(
                        "with invalid registration_scenario",
                        (B2bUserElementAction) (element) -> element.setRegistrationScenario("qwe"),
                        "type_error.enum"
                ),
                Arguments.of(
                        "with invalid holding_admin_process_status",
                        (B2bUserElementAction) (element) -> element.setHoldingAdminProcessStatus("qwe"),
                        "type_error.enum"
                ),
                Arguments.of(
                        "with invalid user_extra_phone",
                        (B2bUserElementAction) (element) -> element.setUserExtraPhone("qwe"),
                        "value_error.str.regex"
                ),
                Arguments.of(
                        "with invalid user_extra_phone",
                        (B2bUserElementAction) (element) -> element.setUserExtraPhone(RandomStringUtils.random(255, false, true)),
                        "value_error.str.regex"
                ),
                Arguments.of(
                        "with invalid is_phone_verified",
                        (B2bUserElementAction) (element) -> element.setIsPhoneVerified("qwe"),
                        "type_error.bool"
                ),
                Arguments.of(
                        "with invalid is_email_verified",
                        (B2bUserElementAction) (element) -> element.setIsEmailVerified("qwe"),
                        "type_error.bool"
                ),
                Arguments.of(
                        "with invalid crm_email",
                        (B2bUserElementAction) (element) -> element.setCrmEmail(RandomStringUtils.random(255, true, true)),
                        "value_error.any_str.max_length"
                ),
                Arguments.of(
                        "with invalid last_b2b_auth_date",
                        (B2bUserElementAction) (element) -> element.setLastB2bAuthDate(RandomStringUtils.random(50, true, true)),
                        "value_error.datetime"
                ),
                Arguments.of(
                        "with invalid last_oapi_auth_date",
                        (B2bUserElementAction) (element) -> element.setLastOapiAuthDate(RandomStringUtils.random(50, true, true)),
                        "value_error.datetime"
                ),
                Arguments.of(
                        "with invalid last_soap_auth_date",
                        (B2bUserElementAction) (element) -> element.setLastSoapAuthDate(RandomStringUtils.random(50, true, true)),
                        "value_error.datetime"
                )
        );
    }

    @ParameterizedTest(name = "{0}")
    @DisplayName("Create new user")
    @MethodSource("provideParamsForTestsWithInvalidParams")
    public void postB2bUserCreateWithInvalidParams(String description, B2bUserElementAction action, String type){
        // Create request body
        B2bUserElement body = getB2bUserCreateRequestBody();
        body.setIdBitrix(getMaxUid().toString());
        action.performAction(body);

        // Get user information
        ValidationError actual = postB2bUserCreate(
                "correct_token",
                body,
                422,
                ValidationError.class
        );

        // Check that actual result is equal to expected result
        checkBody(actual, getValidationError(type));
    }

    private static Stream<Arguments> provideParamsForTestsWithMissingParams() {
        return Stream.of(
                Arguments.of(
                        "user_phone = null",
                        (B2bUserElementAction) (element) -> element.setUserPhone(null)
                ),
                Arguments.of(
                        "comment = null",
                        (B2bUserElementAction) (element) -> element.setComment(null)
                ),
                Arguments.of(
                        "corr_id = null",
                        (B2bUserElementAction) (element) -> element.setCorrId(null)
                ),/*
                Arguments.of(
                        "rs_manager_id = null",
                        (B2bUserElementAction) (element) -> element.setRsManagerId(null)
                ),*/
                Arguments.of(
                        "user_status = null",
                        (B2bUserElementAction) (element) -> element.setUserStatus(null)
                ),
                Arguments.of(
                        "user_org_name = null",
                        (B2bUserElementAction) (element) -> element.setUserOrgName(null)
                ),
                Arguments.of(
                        "user_lockreason = null",
                        (B2bUserElementAction) (element) -> element.setUserLockreason(null)
                ),
                Arguments.of(
                        "user_ip = null",
                        (B2bUserElementAction) (element) -> element.setUserIp(null)
                ),
                Arguments.of(
                        "user_robotlogin = null",
                        (B2bUserElementAction) (element) -> element.setUserRobotlogin(null)
                ),
                Arguments.of(
                        "user_robotpassword = null",
                        (B2bUserElementAction) (element) -> element.setUserRobotpassword(null)
                ),
                Arguments.of(
                        "note = null",
                        (B2bUserElementAction) (element) -> element.setNote(null)
                ),
                Arguments.of(
                        "registration_scenario = null",
                        (B2bUserElementAction) (element) -> element.setRegistrationScenario(null)
                ),
                Arguments.of(
                        "holding_admin_process_status = null",
                        (B2bUserElementAction) (element) -> element.setHoldingAdminProcessStatus(null)
                ),
                Arguments.of(
                        "user_extra_phone = null",
                        (B2bUserElementAction) (element) -> element.setUserExtraPhone(null)
                ),
                Arguments.of(
                        "is_phone_verified = null",
                        (B2bUserElementAction) (element) -> element.setIsPhoneVerified(null)
                ),
                Arguments.of(
                        "is_email_verified = null",
                        (B2bUserElementAction) (element) -> element.setIsEmailVerified(null)
                ),
                Arguments.of(
                        "crm_phone = null",
                        (B2bUserElementAction) (element) -> element.setCrmPhone(null),
                        (B2bUserElementGetter) B2bUserElement::getCrmPhone,
                        "crm_phone"
                )/*,
                Arguments.of(
                        "сrm_email = null",
                        (B2bUserElementAction) (element) -> element.setCrmEmail(null),
                        (B2bUserElementGetter) B2bUserElement::getCrmEmail,
                        "сrm_email"
                )*/,
                Arguments.of(
                        "last_b2b_auth_date = null",
                        (B2bUserElementAction) (element) -> element.setLastB2bAuthDate(null),
                        (B2bUserElementGetter) B2bUserElement::getLastB2bAuthDate,
                        "last_b2b_auth_date"
                ),
                Arguments.of(
                        "last_oapi_auth_date = null",
                        (B2bUserElementAction) (element) -> element.setLastB2bAuthDate(null),
                        (B2bUserElementGetter) B2bUserElement::getLastOapiAuthDate,
                        "last_oapi_auth_date"
                ),
                Arguments.of(
                        "last_soap_auth_date = null",
                        (B2bUserElementAction) (element) -> element.setLastSoapAuthDate(null),
                        (B2bUserElementGetter) B2bUserElement::getLastSoapAuthDate,
                        "last_soap_auth_date"
                )
        );
    }

    @ParameterizedTest(name = "{0}")
    @DisplayName("Create new user")
    @MethodSource("provideParamsForTestsWithMissingParams")
    public void postB2bUserCreateWithMissingParams(String description, B2bUserElementAction action){
        // Create request body
        B2bUserElement body = getB2bUserCreateRequestBody();
        body.setIdBitrix(getMaxUid().toString());
        action.performAction(body);

        // Create new user
        B2bUserElement actualCreate = postB2bUserCreate(
                "correct_token",
                body,
                200,
                B2bUserElement.class
        );

        // Check that actual result is equal to expected result
        if (body.getUserStatus() == null) body.setUserStatus(0.0);
        if (body.getRegistrationScenario() == null) body.setRegistrationScenario("OTHER");
        if (body.getIsEmailVerified() == null) body.setIsEmailVerified(true);
        if (body.getIsPhoneVerified() == null) body.setIsPhoneVerified(false);
        checkBody(actualCreate, body, "user_reg_date");

        // Create queryParams
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", actualCreate.getIdBitrix());
        queryParams.put("field_id", "id_bitrix");

        // Get user information
        B2bUserElement actual = getB2bUserElement(
                "correct_token",
                queryParams,
                200,
                B2bUserElement.class
        );

        // Check that actual result is equal to expected result
        if (body.getUserStatus() == null) body.setUserStatus(0.0);
        checkBody(actual, body, "user_reg_date");
    }

    @Test
    @DisplayName("Create new user with existing id")
    public void postB2bUserCreateWithExistingIdBitrix(){
        // Create request body
        B2bUserElement body = getB2bUserCreateRequestBody();
        body.setIdBitrix(getIdBitrix().toString());

        // Get user information
        BaseResponseError actual = postB2bUserCreate(
                "correct_token",
                body,
                400,
                BaseResponseError.class
        );

        // Check that actual result is equal to expected result
        checkBody(actual, getAlreadyExistError());
    }

    @Test
    @DisplayName("Create new user with existing user_robotlogin")
    public void postB2bUserCreateWithExistingUserRobotLogin(){
        // Create request body
        B2bUserElement body = getB2bUserCreateRequestBody();
        body.setIdBitrix(getMaxUid().toString());
        body.setUserRobotlogin(getUserRobotLogin());

        // Get user information
        BaseResponseError actual = postB2bUserCreate(
                "correct_token",
                body,
                400,
                BaseResponseError.class
        );

        // Check that actual result is equal to expected result
        checkBody(actual, getLoginAlreadyExistError());
    }

    @Test
    @DisplayName("Create new user with incorrect token")
    public void postB2bUserCreateWithIncorrectToken(){
        // Get user information
        BaseResponseError actual = postB2bUserCreate(
                "incorrect_token",
                getB2bUserCreateRequestBody(),
                401,
                BaseResponseError.class
        );

        // Check that actual result is equal to expected result
        checkBody(actual, getIncorrectTokenError());
    }
}
