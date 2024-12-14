package data;

public enum SqlQueryEnum {
    id_bitrix("5553", ""),
    foreign_id_bitrix("254", ""),
    corr_id("corr_id", "select corr_id from users_b2buser where id_bitrix = " + id_bitrix.getParam() + ";"),
    id("id", "select id from users_b2buser where id_bitrix = " + id_bitrix.getParam() + ";"),
    foreign_corr_id("corr_id", "select corr_id from users_b2buser where id_bitrix = " + foreign_id_bitrix.getParam() + ";"),
    user_name("user_name", "select user_name from users_b2buser where id_bitrix = " + id_bitrix.getParam() + ";"),
    user_lastname("user_lastname", "select user_lastname from users_b2buser where id_bitrix = " + id_bitrix.getParam() + ";"),
    user_surname("user_surname", "select user_surname from users_b2buser where id_bitrix = " + id_bitrix.getParam() + ";"),
    user_email("user_email", "select user_email from users_b2buser where id_bitrix = " + id_bitrix.getParam() + ";"),
    user_status("user_status", "select user_status from users_b2buser where id_bitrix = " + id_bitrix.getParam() + ";"),
    user_ip("user_ip", "select user_ip from users_b2buser where id_bitrix = " + id_bitrix.getParam() + ";"),
    user_robotlogin("user_robotlogin", "select user_robotlogin from users_b2buser where id_bitrix = " + id_bitrix.getParam() + ";"),
    user_robotpassword("user_robotpassword", "select user_robotpassword from users_b2buser where id_bitrix = " + id_bitrix.getParam() + ";"),
    rs_contact_person_id("rs_contact_person_id", "select rs_contact_person_id from users_b2buser where id_bitrix = " + id_bitrix.getParam() + ";"),
    rs_manager_id("rs_manager_id", "select rs_manager_id from users_b2buser where id_bitrix = " + id_bitrix.getParam() + ";"),
    user_phone("user_phone", "select user_phone from users_b2buser where id_bitrix = " + id_bitrix.getParam() + ";"),
    crm_phone("crm_phone", "select crm_phone from users_b2buser where id_bitrix = " + id_bitrix.getParam() + ";"),
    crm_email("crm_email", "select crm_email from users_b2buser where id_bitrix = " + id_bitrix.getParam() + ";"),
    rs_id("rs_id", "select rs_id from users_b2buser where id_bitrix = " + id_bitrix.getParam() + ";"),
    last_b2b_auth_date("last_b2b_auth_date", "select last_b2b_auth_date from users_b2buser where id_bitrix = " + id_bitrix.getParam() + ";"),
    last_oapi_auth_date("last_oapi_auth_date", "select last_oapi_auth_date from users_b2buser where id_bitrix = " + id_bitrix.getParam() + ";"),
    last_soap_auth_date("last_soap_auth_date", "select last_soap_auth_date from users_b2buser where id_bitrix = " + id_bitrix.getParam() + ";");
    private final String param;
    private final String query;

    SqlQueryEnum(String param, String query) {
        this.param = param;
        this.query = query;
    }

    public String getParam() {
        return param;
    }

    public String getQuery() {
        return query;
    }

}