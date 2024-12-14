package data;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestParams {
    private static Connection connectToDb() {
        try {
            return DriverManager.getConnection(getPostgresUrl(), getPostgresUsername(), getPostgresPassword());
        } catch (Exception e) {
            throw new AssertionError("Connection failed:\n" + e.getMessage());
        }
    }
    private static String getParam(SqlQueryEnum sqe) {
        Object paramValue = getParam(sqe.getParam(), sqe.getQuery());
        return paramValue != null ? paramValue.toString() : null;
    }

    private static Object getParam(String param, String query) {
        Connection conn = connectToDb();
        try {
            ResultSet rs = conn.createStatement().executeQuery(query);
            rs.next();
            return rs.getObject(param);
        } catch (Exception e) {
            throw new AssertionError(e.getMessage());
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Dotenv dotenv = Dotenv.load();
    private static final String b2b_admin_token = "Bearer " + dotenv.get("B2B_ADMIN_API_TOKEN");
    private static final String b2b_admin_url = dotenv.get("B2B_ADMIN_API_URL");
    private static String firebird_url = dotenv.get("FIREBIRD_URL");
    private static String firebird_username = dotenv.get("FIREBIRD_USERNAME");
    private static String firebird_password = dotenv.get("FIREBIRD_PASSWORD");
    private static String psql_url = dotenv.get("POSTGRES_URL");
    private static String psql_username = dotenv.get("POSTGRES_USERNAME");
    private static String psql_password = dotenv.get("POSTGRES_PASSWORD");
    private static final String incorrect_token = "Bearer qweqweqwe";
    private static final Integer id = Integer.parseInt(getParam(SqlQueryEnum.id));
    private static final Integer nonexistent_id_bitrix = 254123;
    private static final Integer id_bitrix = Integer.parseInt(SqlQueryEnum.id_bitrix.getParam());
    private static final Integer foreign_id_bitrix = Integer.parseInt(SqlQueryEnum.foreign_id_bitrix.getParam());
    private static final Integer rs_id = Integer.parseInt(getParam(SqlQueryEnum.rs_id));
    private static final String user_reg_date = "2020-07-03";
    private static final String user_lastname = getParam(SqlQueryEnum.user_lastname);
    private static final String user_name = getParam(SqlQueryEnum.user_name);
    private static final String user_surname = getParam(SqlQueryEnum.user_surname);
    private static final String user_phone = getParam(SqlQueryEnum.user_phone);
    private static final String crm_email = getParam(SqlQueryEnum.crm_email);
    private static final String crm_phone = getParam(SqlQueryEnum.crm_phone);
    private static final String user_email = getParam(SqlQueryEnum.user_email);
    private static final Double user_status = Double.parseDouble(getParam(SqlQueryEnum.user_status));
    private static final Integer user_isrobot = 1;
    private static final String user_ip = getParam(SqlQueryEnum.user_ip);
    private static final String user_robotlogin = getParam(SqlQueryEnum.user_robotlogin);
    private static final String user_robotpassword = getParam(SqlQueryEnum.user_robotpassword);
    private static final String rs_contact_person_id = getParam(SqlQueryEnum.rs_contact_person_id);
    private static final Integer corr_id = Integer.parseInt(getParam(SqlQueryEnum.corr_id));
    private static final Integer us_id = Integer.parseInt(getParam(SqlQueryEnum.rs_manager_id));
    private static final Integer foreign_corr_id = Integer.parseInt(getParam(SqlQueryEnum.foreign_corr_id));
    private static final String last_b2b_auth_date = getParam(SqlQueryEnum.last_b2b_auth_date);
    private static final String last_oapi_auth_date = getParam(SqlQueryEnum.last_oapi_auth_date);
    private static final String last_soap_auth_date = getParam(SqlQueryEnum.last_soap_auth_date);
    private static final String email = getParam(SqlQueryEnum.user_email);

    public static Integer getCorrId() {return corr_id;}
    public static Integer getRsId() {return rs_id;}
    public static String getUserRegDate() {return user_reg_date;}
    public static String getUserPhone() {return user_phone;}
    public static String getCrmPhone() {return crm_phone;}
    public static String getCrmEmail() {return crm_email;}
    public static String getUserEmail() {return user_email;}
    public static String getRsContactPersonId() {return rs_contact_person_id;}
    public static String getUserIp() {return user_ip;}
    public static String getUserRobotLogin() {return user_robotlogin;}
    public static String getUserRobotPassword() {return user_robotpassword;}
    public static Double getUserStatus() {return user_status;}
    public static Integer getUserIsRobot() {return user_isrobot;}

    public static Integer getForeignCorrId() {
        return foreign_corr_id;
    }

    public static Integer getIdBitrix() {
        return id_bitrix;
    }

    public static Integer getNonexistentIdBitrix() {return nonexistent_id_bitrix;}

    public static String getB2bAdminToken() { return b2b_admin_token; }
    public static String getB2bAdminUrl() { return b2b_admin_url; }
    public static String getFirebirdUrl() {
        return firebird_url;
    }
    public static String getFirebirdUsername() {
        return firebird_username;
    }
    public static String getFirebirdPassword() {
        return firebird_password;
    }
    public static String getPostgresUrl() {
        return psql_url;
    }
    public static String getPostgresUsername() {
        return psql_username;
    }
    public static String getPostgresPassword() {
        return psql_password;
    }

    public static String getIncorrectToken() {return incorrect_token;}
    public static Integer getId() {return id;}

    public static Integer getUsId() {return us_id;}
    public static String getUserFullName() {return user_lastname + " " + user_name + " " + user_surname;}

    public static String getLastB2bAuthDate() {return last_b2b_auth_date;}
    public static String getLastOapiAuthDate() {return last_oapi_auth_date;}
    public static String getLastSoapAuthDate() {return last_soap_auth_date;}
}
