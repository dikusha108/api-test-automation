package steps;

import client.B2bUsersHttpClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.b2b_user.B2bUserElement;
import model.b2b_user.PostB2bUserListBody;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import static data.TestParams.*;
import static steps.BaseSteps.checkStatusCode;

public class B2bUserSteps {
    @Step("Get B2b user element")
    public static <T> T getB2bUserElement(String token, HashMap<String, Object> queryParams, Integer statusCode, Class<T> responseClass) {
        Response response = B2bUsersHttpClient.getB2bUserElement(token, queryParams);
        checkStatusCode(response, statusCode);
        return response.body().as(responseClass);
    }

    @Step("Get B2b user list")
    public static <T> T getB2bUserList(String token, PostB2bUserListBody body, Integer statusCode, Class<T> responseClass) {
        Response response = B2bUsersHttpClient.getB2bUserList(token, body);
        checkStatusCode(response, statusCode);
        return response.body().as(responseClass);
    }

    @Step("Create B2b user")
    public static <T> T postB2bUserCreate(String token, B2bUserElement body, Integer statusCode, Class<T> responseClass) {
        Response response = B2bUsersHttpClient.postB2bUserCreate(token, body);
        checkStatusCode(response, statusCode);
        return response.body().as(responseClass);
    }

    @Step("Edit B2b user")
    public static <T> T patchB2bUserUpdate(String token, B2bUserElement body, HashMap<String, Object> queryParams, Integer statusCode, Class<T> responseClass) {
        Response response = B2bUsersHttpClient.patchB2bUserUpdate(token, body, queryParams);
        checkStatusCode(response, statusCode);
        return response.body().as(responseClass);
    }

    @Step("Get B2b user list from DB")
    public static List<B2bUserElement> getB2bUsersFromDb(
            List<String> corr_id_list, List<String> filter_by_user_status, List<String> filter_by_admin_status,
            String filter_by_user_start_reg_date, String filter_by_end_last_auth_date) {
        List<B2bUserElement> users = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("select * from users_b2buser ubb where ");

        // Create query
        List<String> conditions = new ArrayList<>();
        if (corr_id_list != null && !corr_id_list.isEmpty()) {
            conditions.add("corr_id in (" + convertListToString(corr_id_list) + ")");
        }
        if (filter_by_user_status != null && !filter_by_user_status.isEmpty()) {
            conditions.add("user_status in (" + String.join(", ", convertListToString(filter_by_user_status)) + ")");
        }
        if (filter_by_admin_status != null && !filter_by_admin_status.isEmpty()) {
            conditions.add("holding_admin_process_status in ('" + String.join("', '", convertListToString(filter_by_admin_status)) + "')");
        }
        if (filter_by_user_start_reg_date != null) {
            conditions.add("user_reg_date >= '" + filter_by_user_start_reg_date + "'");
        }
        String conditionString = String.join(" AND ", conditions);
        queryBuilder.append(conditionString);
        System.out.println("Executing query: " + queryBuilder);

        try {
            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(getPostgresUrl(), getPostgresUsername(), getPostgresPassword());

            if (conn == null) {
                System.err.println("Could not connect to database");
            } else {
                Statement stmt1 = conn.createStatement();
                ResultSet res = stmt1.executeQuery(queryBuilder.toString());

                while (res.next()) {
                    B2bUserElement newItem = new B2bUserElement(
                        res.getDouble("rs_id"),
                        res.getInt("id_bitrix"),
                        res.getString("user_reg_date"),
                        res.getString("user_lastname"),
                        res.getString("user_name"),
                        res.getString("user_surname"),
                        res.getString("user_email"),
                        res.getString("comment"),
                        res.getInt("corr_id"),
                        res.getString("rs_contact_person_id"),
                        res.getString("rs_manager_id"),
                        res.getDouble("user_status"),
                        res.getString("user_org_name"),
                        res.getString("user_lockreason"),
                        res.getBoolean("user_isrobot"),
                        res.getString("user_ip"),
                        res.getString("user_robotlogin"),
                        res.getString("user_robotpassword"),
                        res.getString("note"),
                        res.getString("holding_admin_process_status"),
                        res.getString("registration_scenario"),
                        res.getString("decision_type_status"),
                        res.getString("user_extra_phone"),
                        res.getBoolean("is_phone_verified"),
                        res.getBoolean("is_email_verified"),
                        res.getString("crm_phone"),
                        res.getString("crm_email"),
                        res.getString("user_lockreason_type"),
                        res.getString("last_b2b_auth_date"),
                        res.getString("last_oapi_auth_date"),
                        res.getString("last_soap_auth_date")
                    );
                    users.add(newItem);
                }

                stmt1.close();
                conn.close();
            }
        } catch (Exception e) {
            throw new AssertionError("Connection failed:\n" + Arrays.toString(e.getStackTrace()));
        }

        return users;
    }

    @Step("Get max id_bitrix from Postgres DB users_b2buser")
    public static Integer getMaxUid() {
        int max_uid = 0;

        // Connect to DB
        try {
            Class.forName("org.postgresql.Driver");

            Connection conn = DriverManager.getConnection(getPostgresUrl(), getPostgresUsername(), getPostgresPassword());

            if (conn == null) {
                System.err.println("Could not connect to database");
            } else {
                Statement stmt = conn.createStatement();
                // Get max id_bitrix
                ResultSet res = stmt.executeQuery("SELECT MAX(id_bitrix) as id_bitrix_max FROM public.users_b2buser where id_bitrix < 80000;");

                while (res.next()) {
                    max_uid = res.getInt("id_bitrix_max");
                }

                stmt.close();
                conn.close();
            }
        } catch (Exception e) {
            throw new AssertionError("Connection failed:\n" + e.getMessage());
        }

        return max_uid + 1;
    }

    private static String convertListToString(List<String> list) {
        StringBuilder result = new StringBuilder();
        for (Object item : list) {
            if (result.length() > 0) {
                result.append(", ");
            }
            result.append(item);
        }
        return result.toString();
    }

}
