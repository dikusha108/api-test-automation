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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import static data.Errors.*;
import static data.RequestBody.getB2bUserCreateRequestBody;
import static data.RequestBody.getB2bUserUpdateRequestBody;
import static data.TestParams.*;
import static steps.B2bUserSteps.*;
import static steps.BaseSteps.checkBody;

@Epic("b2b_users")
@Feature("PATCH /update")
@Execution(ExecutionMode.SAME_THREAD)
public class PatchB2bUserUpdateTest {
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
                        "decision_type_status = MANAGERB24",
                        (B2bUserElementAction) (element) -> element.setDecisionTypeStatus("MANAGERB24")
                )
        );
    }
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideParamsForTestsWithValidParams")
    @DisplayName("Edit user with valid params")
    public void patchB2bUserUpdateWithValidParamsParametrized(String description, B2bUserElementAction action){
        // Get request bodies
        B2bUserElement bodyCreate = getB2bUserCreateRequestBody();
        bodyCreate.setIdBitrix(getMaxUid().toString());
        B2bUserElement bodyUpdate = getB2bUserUpdateRequestBody();

        // Set value in the request body
        action.performAction(bodyUpdate);

        // Create new user
        B2bUserElement newUser = postB2bUserCreate(
                "correct_token",
                bodyCreate,
                200,
                B2bUserElement.class
        );

        // Create queryParams
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("field_id", "id_bitrix");
        queryParams.put("id", Integer.parseInt(newUser.getIdBitrix()));

        // Edit user
        B2bUserElement actualUpdate = patchB2bUserUpdate(
                "correct_token",
                bodyUpdate,
                queryParams,
                200,
                B2bUserElement.class
        );

        // Check that actual result is equal to expected result
        bodyUpdate.setRegistrationScenario(bodyCreate.getRegistrationScenario());
        checkBody(actualUpdate, bodyUpdate, "user_reg_date", "id_bitrix");

        // Get user information
        B2bUserElement actual = getB2bUserElement(
                "correct_token",
                queryParams,
                200,
                B2bUserElement.class
        );

        // Check that actual result is equal to expected result
        checkBody(actual, bodyUpdate, "user_reg_date", "id_bitrix");
    }

    @Test
    @DisplayName("Edit user with valid params")
    public void patchB2bUserUpdateWithValidParams(){
        // Get request bodies
        B2bUserElement createBody = getB2bUserCreateRequestBody();
        createBody.setIdBitrix(getMaxUid().toString());
        B2bUserElement updateBody = getB2bUserUpdateRequestBody();

        // Create new user
        B2bUserElement newUser = postB2bUserCreate(
                "correct_token",
                createBody,
                200,
                B2bUserElement.class
        );

        // Create queryParams
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("field_id", "id_bitrix");
        queryParams.put("id", Integer.parseInt(newUser.getIdBitrix()));

        // Edit user
        B2bUserElement actualUpdate = patchB2bUserUpdate(
                "correct_token",
                updateBody,
                queryParams,
                200,
                B2bUserElement.class
        );

        // Check that actual result is equal to expected result
        updateBody.setRegistrationScenario(createBody.getRegistrationScenario());
        checkBody(actualUpdate, updateBody, "user_reg_date", "id_bitrix");

        // Get user information
        B2bUserElement actual = getB2bUserElement(
                "correct_token",
                queryParams,
                200,
                B2bUserElement.class
        );

        // Check that actual result is equal to expected result
        checkBody(actual, updateBody, "user_reg_date", "id_bitrix");
    }

    private static Stream<Arguments> provideParamsForTestsWithInvalidParams() {
        return Stream.of(
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
                        "with invalid holding_admin_process_status",
                        (B2bUserElementAction) (element) -> element.setHoldingAdminProcessStatus("qwe"),
                        "type_error.enum"
                ),
                Arguments.of(
                        "with invalid decision_type_status",
                        (B2bUserElementAction) (element) -> element.setDecisionTypeStatus("qwe"),
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
                        (B2bUserElementAction) (element) -> element.setCrmEmail(RandomStringUtils.random(250, true, true)),
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
    @DisplayName("Edit user with invalid параметром")
    @MethodSource("provideParamsForTestsWithInvalidParams")
    public void patchB2bUserUpdateWithInvalidParams(String description, B2bUserElementAction action, String type){
        // Get request bodies
        B2bUserElement updateBody = getB2bUserUpdateRequestBody();
        action.performAction(updateBody);

        // Create queryParams
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("field_id", "id_bitrix");
        queryParams.put("id", getIdBitrix());

        // Edit user
        ValidationError actual = patchB2bUserUpdate(
                "correct_token",
                updateBody,
                queryParams,
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
                        (B2bUserElementAction) (element) -> element.setUserPhone(null),
                        (B2bUserElementGetter) B2bUserElement::getUserPhone,
                        "user_phone"
                ),
                Arguments.of(
                        "comment = null",
                        (B2bUserElementAction) (element) -> element.setComment(null),
                        (B2bUserElementGetter) B2bUserElement::getComment,
                        "comment"
                ),
                Arguments.of(
                        "corr_id = null",
                        (B2bUserElementAction) (element) -> element.setCorrId(null),
                        (B2bUserElementGetter) B2bUserElement::getCorrId,
                        "corr_id"
                ),
                Arguments.of(
                        "user_status = null",
                        (B2bUserElementAction) (element) -> element.setUserStatus(null),
                        (B2bUserElementGetter) B2bUserElement::getUserStatus,
                        "user_status"
                ),
                Arguments.of(
                        "user_org_name = null",
                        (B2bUserElementAction) (element) -> element.setUserOrgName(null),
                        (B2bUserElementGetter) B2bUserElement::getUserOrgName,
                        "user_org_name"
                ),
                Arguments.of(
                        "user_lockreason = null",
                        (B2bUserElementAction) (element) -> element.setUserLockreason(null),
                        (B2bUserElementGetter) B2bUserElement::getUserLockreason,
                        "user_lockreason"
                ),
                Arguments.of(
                        "user_ip = null",
                        (B2bUserElementAction) (element) -> element.setUserIp(null),
                        (B2bUserElementGetter) B2bUserElement::getUserIp,
                        "user_ip"
                ),
                Arguments.of(
                        "user_robotlogin = null",
                        (B2bUserElementAction) (element) -> element.setUserRobotlogin(null),
                        (B2bUserElementGetter) B2bUserElement::getUserRobotlogin,
                        "user_robotlogin"
                ),
                Arguments.of(
                        "user_robotpassword = null",
                        (B2bUserElementAction) (element) -> element.setUserRobotpassword(null),
                        (B2bUserElementGetter) B2bUserElement::getUserRobotpassword,
                        "user_robotpassword"
                ),
                Arguments.of(
                        "note = null",
                        (B2bUserElementAction) (element) -> element.setNote(null),
                        (B2bUserElementGetter) B2bUserElement::getNote,
                        "note"
                ),
                Arguments.of(
                        "registration_scenario = null",
                        (B2bUserElementAction) (element) -> element.setRegistrationScenario(null),
                        (B2bUserElementGetter) B2bUserElement::getRegistrationScenario,
                        "registration_scenario"
                ),
                Arguments.of(
                        "holding_admin_process_status = null",
                        (B2bUserElementAction) (element) -> element.setHoldingAdminProcessStatus(null),
                        (B2bUserElementGetter) B2bUserElement::getHoldingAdminProcessStatus,
                        "holding_admin_process_status"
                ),
                Arguments.of(
                        "decision_type_status = null",
                        (B2bUserElementAction) (element) -> element.setDecisionTypeStatus(null),
                        (B2bUserElementGetter) B2bUserElement::getDecisionTypeStatus,
                        "decision_type_status"
                ),
                Arguments.of(
                        "user_extra_phone = null",
                        (B2bUserElementAction) (element) -> element.setUserExtraPhone(null),
                        (B2bUserElementGetter) B2bUserElement::getUserExtraPhone,
                        "user_extra_phone"
                ),
                Arguments.of(
                        "is_phone_verified = null",
                        (B2bUserElementAction) (element) -> element.setIsPhoneVerified(null),
                        (B2bUserElementGetter) B2bUserElement::getIsPhoneVerified,
                        "is_phone_verified"
                ),
                Arguments.of(
                        "is_email_verified = null",
                        (B2bUserElementAction) (element) -> element.setIsEmailVerified(null),
                        (B2bUserElementGetter) B2bUserElement::getIsEmailVerified,
                        "is_email_verified"
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
                        (B2bUserElementAction) (element) -> element.setLastOapiAuthDate(null),
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
    @DisplayName("Edit user with missing params")
    @MethodSource("provideParamsForTestsWithMissingParams")
    public void patchB2bUserUpdateWithMissingParams(
            String description,
            B2bUserElementAction actionSet,
            B2bUserElementGetter getterAction,
            String ignore_param
    ){
        // Create request bodies
        B2bUserElement bodyCreate = getB2bUserCreateRequestBody();
        bodyCreate.setIdBitrix(getMaxUid().toString());
        B2bUserElement bodyUpdate = getB2bUserUpdateRequestBody();

        // Set value in the request body
        actionSet.performAction(bodyUpdate);

        // Create new user
        B2bUserElement actualCreate = postB2bUserCreate(
                "correct_token",
                bodyCreate,
                200,
                B2bUserElement.class
        );

        // Create queryParams
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", actualCreate.getIdBitrix());
        queryParams.put("field_id", "id_bitrix");

        // Edit user
        B2bUserElement actualUpdate = patchB2bUserUpdate(
                "correct_token",
                bodyUpdate,
                queryParams,
                200,
                B2bUserElement.class
        );
        bodyUpdate.setRegistrationScenario(bodyCreate.getRegistrationScenario());

        // Check that actual result is equal to expected result
        checkBody(actualUpdate, bodyUpdate, "user_reg_date", "id_bitrix", ignore_param);
        checkBody(getterAction.getValue(actualUpdate), getterAction.getValue(bodyCreate));

        // Get user information
        B2bUserElement actual = getB2bUserElement(
                "correct_token",
                queryParams,
                200,
                B2bUserElement.class
        );

        // Check that actual result is equal to expected result
        checkBody(actual, bodyUpdate, "user_reg_date", "id_bitrix", ignore_param);
        checkBody(getterAction.getValue(actual), getterAction.getValue(bodyCreate));
    }

    private static Stream<Arguments> provideParamsForTestsWithNonexistentParams() {
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
    @DisplayName("Edit user with nonexistent params")
    @MethodSource("provideParamsForTestsWithNonexistentParams")
    public void patchB2bUserUpdateWithNonexistentParam(String description, String field_id){
        // Create queryParams
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", getNonexistentIdBitrix());
        queryParams.put("field_id", field_id);

        // Edit user
        BaseResponseError actual = patchB2bUserUpdate(
                "correct_token",
                getB2bUserUpdateRequestBody(),
                queryParams,
                404,
                BaseResponseError.class
        );

        // Check that actual result is equal to expected result
        checkBody(actual, getNotFoundError());
    }

    private static Stream<Arguments> provideParamsForTestsWithInvalidQueryParams() {
        return Stream.of(
                Arguments.of(
                        "with invalid id_bitrix",
                        "id_bitrix",
                        "qwe",
                        "value_error"
                ),
                Arguments.of(
                        "with invalid rs_id",
                        "rs_id",
                        "qwe",
                        "value_error"
                ),
                Arguments.of(
                        "with invalid id",
                        "id",
                        "qwe",
                        "value_error"
                ),
                Arguments.of(
                        "with invalid field_id",
                        "qwe",
                        getIdBitrix().toString(),
                        "type_error.enum"
                )
        );
    }
    @ParameterizedTest(name = "{0}")
    @DisplayName("Edit user with invalid queryParams")
    @MethodSource("provideParamsForTestsWithInvalidQueryParams")
    public void patchB2bUserUpdateWithInvalidQueryParam(String description, String field_id, String value, String type){
        // Create queryParams
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", value);
        queryParams.put("field_id", field_id);

        // Edit user
        ValidationError actual = patchB2bUserUpdate(
                "correct_token",
                getB2bUserUpdateRequestBody(),
                queryParams,
                422,
                ValidationError.class
        );

        // Check that actual result is equal to expected result
        checkBody(actual, getValidationError(type));
    }

    @Test
    @DisplayName("Edit user without queryParams")
    public void patchB2bUserUpdateWithoutQueryParams(){
        // Create queryParams
        HashMap<String, Object> queryParams = new HashMap<>();

        // Edit user
        ValidationError actual = patchB2bUserUpdate(
                "correct_token",
                getB2bUserUpdateRequestBody(),
                queryParams,
                422,
                ValidationError.class
        );

        // Check that actual result is equal to expected result
        checkBody(actual, getValidationError("value_error.missing", "value_error.missing"));
    }

    @Test
    @DisplayName("Edit user with empty body")
    public void patchB2bUserUpdateWithEmptyBody(){
        // Create queryParams
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("field_id", "id_bitrix");
        queryParams.put("id", getIdBitrix());

        // Get user information
        B2bUserElement userElement = getB2bUserElement(
                "correct_token",
                queryParams,
                200,
                B2bUserElement.class
        );

        // Edit user
        B2bUserElement actualUpdate = patchB2bUserUpdate(
                "correct_token",
                new B2bUserElement(),
                queryParams,
                200,
                B2bUserElement.class
        );

        // Check that actual result is equal to expected result
        checkBody(actualUpdate, userElement);

        // Get user information после изменения
        B2bUserElement actual = getB2bUserElement(
                "correct_token",
                queryParams,
                200,
                B2bUserElement.class
        );

        // Check that actual result is equal to expected result
        checkBody(actual, userElement);
    }

    @Test
    @DisplayName("Edit user with incorrect token")
    public void patchB2bUserUpdateWithIncorrectToken(){
        // Create queryParams
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("id", getIdBitrix());
        queryParams.put("field_id", "id_bitrix");

        // Edit user
        BaseResponseError actual = patchB2bUserUpdate(
                "incorrect_token",
                getB2bUserUpdateRequestBody(),
                queryParams,
                401,
                BaseResponseError.class
        );

        // Check that actual result is equal to expected result
        checkBody(actual, getIncorrectTokenError());
    }
}
