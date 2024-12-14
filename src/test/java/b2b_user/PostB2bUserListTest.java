package b2b_user;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import model.b2b_user.B2bUserElement;
import model.b2b_user.B2bUserElementAction;
import model.b2b_user.PostB2bUserListBody;
import model.b2b_user.PostB2bUserListResponse;
import model.errors.BaseResponseError;
import model.errors.Error;
import model.errors.ValidationError;
import org.joda.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static data.Errors.getIncorrectTokenError;
import static data.Errors.getValidationError;
import static data.TestParams.*;
import static steps.B2bUserSteps.*;
import static steps.BaseSteps.checkBody;
import static steps.BaseSteps.checkLists;

@Epic("b2b_users")
@Feature("POST /list")
public class PostB2bUserListTest {
    private static Stream<Arguments> provideParamsForTestsWithValidParams() {
        return Stream.of(
                Arguments.of(
                        " with filtration by corr_id",
                        new PostB2bUserListBody(List.of(getCorrId(), getForeignCorrId())),
                        getB2bUsersFromDb(List.of(getCorrId().toString(), getForeignCorrId().toString()), null, null, null, null)
                ),
                Arguments.of(
                        " with filtration by filter_by_user_status",
                        new PostB2bUserListBody(List.of(getCorrId().toString(), getForeignCorrId().toString()), List.of(1)),
                        getB2bUsersFromDb(List.of(getCorrId().toString(), getForeignCorrId().toString()), List.of("1"), null, null, null)
                ),
                Arguments.of(
                        " with filtration by filter_by_user_status",
                        new PostB2bUserListBody(List.of(getCorrId().toString(), getForeignCorrId().toString()), List.of(0, 3)),
                        getB2bUsersFromDb(List.of(getCorrId().toString(), getForeignCorrId().toString()), List.of("0","3"), null, null, null)
                ),
                Arguments.of(
                        " with filtration by filter_by_admin_status",
                        new PostB2bUserListBody(List.of(getCorrId().toString(), getForeignCorrId().toString()), null, List.of("NEW")),
                        getB2bUsersFromDb(List.of(getCorrId().toString(), getForeignCorrId().toString()), null, List.of("NEW"), null, null)
                ),
                Arguments.of(
                        " with filtration by filter_by_admin_status",
                        new PostB2bUserListBody(List.of(getCorrId().toString(), getForeignCorrId().toString()), null, List.of("NO_ADMINS")),
                        getB2bUsersFromDb(List.of(getCorrId().toString(), getForeignCorrId().toString()), null, List.of("NO_ADMINS"), null, null)
                ),
                Arguments.of(
                        " with filtration by filter_by_admin_status",
                        new PostB2bUserListBody(List.of(getCorrId().toString(), getForeignCorrId().toString()), null, List.of("WAITING_ADMINS")),
                        getB2bUsersFromDb(List.of(getCorrId().toString(), getForeignCorrId().toString()), null, List.of("WAITING_ADMINS"), null, null)
                ),
                Arguments.of(
                        " with filtration by filter_by_user_start_reg_date",
                        new PostB2bUserListBody(List.of(getCorrId().toString(), getForeignCorrId().toString()), null, null, "2024-01-07", null),
                        getB2bUsersFromDb(List.of(getCorrId().toString(), getForeignCorrId().toString()), null, null, "2024-01-07", null)
                ),
                Arguments.of(
                        " with filtration by all parameters",
                        new PostB2bUserListBody(List.of(getCorrId(), getForeignCorrId()), List.of(1, 3), List.of("NEW"), "2020-01-07", null),
                        getB2bUsersFromDb(List.of(getCorrId().toString(), getForeignCorrId().toString()), List.of("1", "3"), List.of("NEW"), "2020-01-07", null)
                )
        );
    }
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideParamsForTestsWithValidParams")
    @DisplayName("Get B2b user list with valid params")
    public void getB2bUserListWithValidCorrIdList(String description, PostB2bUserListBody body, List<B2bUserElement> expectedData){
        // Get user list
        PostB2bUserListResponse actual = getB2bUserList(
                "correct_token",
                body,
                200,
                PostB2bUserListResponse.class
        );

        // Check that actual result is equal to expected result
        checkLists(actual.getData(), expectedData);
    }

    @Test
    @DisplayName("Get B2b user list with valid params and filter by existent and non-existent corr_id")
    public void getB2bUserListWithExistentAndNonexistentCorrId(){
        // Get user list
        PostB2bUserListResponse actual = getB2bUserList(
                "correct_token",
                new PostB2bUserListBody(List.of(getCorrId(), getForeignCorrId() + "123")),
                200,
                PostB2bUserListResponse.class
        );

        // Create expected result
        List<B2bUserElement> data = getB2bUsersFromDb(List.of(getCorrId().toString()), null, null, null, null);
        PostB2bUserListResponse expected = new PostB2bUserListResponse(data);

        // Check that actual result is equal to expected result
        checkLists(actual.getData(), expected.getData());
    }

    @Test
    @DisplayName("Get B2b user list with valid params and filter by non-existent corr_id")
    public void getB2bUserListWithNonexistentCorrId(){
        // Get user list
        PostB2bUserListResponse actual = getB2bUserList(
                "correct_token",
                new PostB2bUserListBody(List.of(getForeignCorrId() + "123")),
                200,
                PostB2bUserListResponse.class
        );

        // Create expected result
        PostB2bUserListResponse expected = new PostB2bUserListResponse(List.of());

        // Check that actual result is equal to expected result
        checkBody(actual, expected);
    }

    @Test
    @DisplayName("Get B2b user list with empty corr_id list")
    public void getB2bUserListWithEmptyCorrIdList(){
        // Get user list
        PostB2bUserListResponse actual = getB2bUserList(
                "correct_token",
                new PostB2bUserListBody(List.of()),
                200,
                PostB2bUserListResponse.class
        );

        // Create expected result
        PostB2bUserListResponse expected = new PostB2bUserListResponse(List.of());

        // Check that actual result is equal to expected result
        checkBody(actual, expected, "data");
    }

    private static Stream<Arguments> provideParamsForTestsWithInvalidParams() {
        return Stream.of(
                Arguments.of(
                        " with invalid corr_id",
                        new PostB2bUserListBody(List.of(getForeignCorrId() + "qwe")),
                        "type_error.integer"
                ),
                Arguments.of(
                        "with invalid filter_by_user_status",
                        new PostB2bUserListBody(null, List.of(5)),
                        "type_error.enum"
                ),
                Arguments.of(
                        "with invalid filter_by_admin_status",
                        new PostB2bUserListBody(null, null, List.of("qwe")),
                        "type_error.enum"
                ),
                Arguments.of(
                        "with invalid filter_by_user_start_reg_date",
                        new PostB2bUserListBody(null, null, null, "20240-07", null),
                        "value_error.date"
                )
        );
    }
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideParamsForTestsWithInvalidParams")
    @DisplayName("Get B2b user list with invalid params")
    public void getB2bUserListWithInvalidCorrId(String description, PostB2bUserListBody body, String type){
        // Get user list
        ValidationError actual = getB2bUserList(
                "correct_token",
                body,
                422,
                ValidationError.class
        );

        // Check that actual result is equal to expected result
        checkBody(actual, getValidationError(type));
    }

    @Test
    @DisplayName("Get B2b user list with incorrect token")
    public void getB2bUserListWithIncorrectToken(){
        // Get user list
        BaseResponseError actual = getB2bUserList(
                "incorrect_token",
                new PostB2bUserListBody(List.of(getForeignCorrId())),
                401,
                BaseResponseError.class
        );

        // Check that actual result is equal to expected result
        checkBody(actual, getIncorrectTokenError());
    }
}
