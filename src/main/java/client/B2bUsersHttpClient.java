package client;

import io.restassured.response.Response;
import model.b2b_user.B2bUserElement;
import model.b2b_user.PostB2bUserListBody;

import java.util.HashMap;

import static client.BaseHttpClient.*;
import static data.TestParams.*;

public class B2bUsersHttpClient {
    private static final String BASE_URL = getB2bAdminUrl();

    private static String getToken(String token) {
        if (token.equals("correct_token")) {
            return getB2bAdminToken();
        } else {
            return getIncorrectToken();
        }
    }

    public static Response getB2bUserElement(String token, HashMap<String, Object> queryParams) {
        return doGetRequest(getToken(token), BASE_URL + "/api/b2b_user/element", queryParams);
    }
    public static Response getB2bUserList(String token, PostB2bUserListBody body) {
        return doPostRequest(getToken(token), BASE_URL + "/api/b2b_user/list", body);
    }
    public static Response postB2bUserCreate(String token, B2bUserElement body) {
        return doPostRequest(getToken(token), BASE_URL + "/api/b2b_user/create", body);
    }
    public static Response patchB2bUserUpdate(String token, B2bUserElement body, HashMap<String, Object> queryParams) {
        return doPatchRequest(getToken(token), BASE_URL + "/api/b2b_user/update", body, queryParams);
    }
}
