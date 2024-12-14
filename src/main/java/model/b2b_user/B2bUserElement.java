package model.b2b_user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class B2bUserElement {
    private Object rs_id;
    private String id_bitrix;
    private String user_reg_date;
    private String user_lastname;
    private String user_name;
    private String user_surname;
    private String user_phone;
    private String user_email;
    private String comment;
    private String corr_id;
    private String rs_contact_person_id;
    private Object user_status;
    private String user_org_name;
    private String user_lockreason;
    private Object user_isrobot;
    private String user_ip;
    private String user_robotlogin;
    private String user_robotpassword;
    private String note;
    private String registration_scenario;
    private String holding_admin_process_status;
    private String decision_type_status;
    private String user_extra_phone;
    private Object is_phone_verified;
    private Object is_email_verified;
    private String crm_phone;
    private String crm_email;
    private String user_lockreason_type = null;
    private String last_b2b_auth_date = null;
    private String last_oapi_auth_date = null;
    private String last_soap_auth_date = null;

    public B2bUserElement() {
    }

    public B2bUserElement(Double rs_id, Integer id_bitrix, String user_reg_date, String user_lastname, String user_name, String user_surname,
                          String user_phone, String user_email, String comment, Integer corr_id,
                          Integer rs_manager_id, Double user_status, String user_org_name, String user_lockreason,
                          boolean user_isrobot, String user_ip, String user_robotlogin, String user_robotpassword, String note, String registration_scenario,
                          String user_extra_phone, Boolean is_phone_verified, Boolean is_email_verified,
                          String crm_phone, String crm_email, String user_lockreason_type,
                          String last_b2b_auth_date, String last_oapi_auth_date, String last_soap_auth_date) throws ParseException {
        this.rs_id = rs_id;
        this.id_bitrix = id_bitrix.toString();
        this.user_reg_date = user_reg_date;
        this.user_lastname = user_lastname;
        this.user_name = user_name;
        this.user_surname = user_surname;
        this.user_phone = user_phone;
        this.user_email = user_email;
        this.comment = comment;
        this.corr_id = corr_id.toString();
        this.user_status = user_status;
        this.user_org_name = user_org_name;
        this.user_lockreason = user_lockreason;
        this.user_isrobot = user_isrobot;
        this.user_ip = user_ip;
        this.user_robotlogin = user_robotlogin;
        this.user_robotpassword = user_robotpassword;
        this.note = note;
        this.registration_scenario = registration_scenario;
        this.user_extra_phone = user_extra_phone;
        this.is_phone_verified = is_phone_verified;
        this.is_email_verified = is_email_verified;
        this.crm_email = crm_email;
        this.crm_phone = crm_phone;
        this.user_lockreason_type = user_lockreason_type;
        if (last_b2b_auth_date != null) this.last_b2b_auth_date = changeDateFormat(last_b2b_auth_date, "yyyy-MM-dd HH:mm:ss.S");
        if (last_oapi_auth_date != null) this.last_oapi_auth_date = changeDateFormat(last_oapi_auth_date, "yyyy-MM-dd HH:mm:ss.SSSSSS");
        if (last_soap_auth_date != null) this.last_soap_auth_date = changeDateFormat(last_soap_auth_date, "yyyy-MM-dd HH:mm:ss.S");
    }

    public B2bUserElement(Double rs_id, Integer id_bitrix, String user_reg_date, String user_lastname, String user_name, String user_surname,
                          String user_email, String comment, Integer corr_id, String rs_contact_person_id, String rs_manager_id,
                          Double user_status, String user_org_name, String user_lockreason, boolean user_isrobot, String user_ip,
                          String user_robotlogin, String user_robotpassword, String note, String holding_admin_process_status, String registration_scenario,
                          String decision_type_status, String user_extra_phone, Boolean is_phone_verified, Boolean is_email_verified,
                          String crm_phone, String crm_email,
                          String user_lockreason_type, String last_b2b_auth_date, String last_oapi_auth_date, String last_soap_auth_date) {
        this.rs_id = rs_id;
        this.id_bitrix = id_bitrix.toString();
        this.user_reg_date = user_reg_date;
        this.user_lastname = user_lastname;
        this.user_name = user_name;
        this.user_surname = user_surname;
        this.user_email = user_email;
        this.comment = comment;
        this.corr_id = corr_id.toString();
        //this.rs_contact_person_id = rs_contact_person_id;
        //this.rs_manager_id = rs_manager_id;
        this.user_status = user_status;
        this.user_org_name = user_org_name;
        this.user_lockreason = user_lockreason;
        this.user_isrobot = user_isrobot;
        this.user_ip = user_ip;
        this.user_robotlogin = user_robotlogin;
        this.user_robotpassword = user_robotpassword;
        this.note = note;
        this.holding_admin_process_status = holding_admin_process_status;
        this.registration_scenario = registration_scenario;
        this.decision_type_status = decision_type_status;
        this.user_extra_phone = user_extra_phone;
        this.is_phone_verified = is_phone_verified;
        this.is_email_verified = is_email_verified;
        this.crm_email = crm_email;
        this.crm_phone = crm_phone;
        this.user_lockreason_type = user_lockreason_type;
        this.last_b2b_auth_date = last_b2b_auth_date;
        this.last_oapi_auth_date = last_oapi_auth_date;
        this.last_soap_auth_date = last_soap_auth_date;
    }

    public B2bUserElement(Integer id_bitrix, String user_lastname, String user_name, String user_surname, String user_phone,
                          String user_email, String comment, Object corr_id, Object rs_contact_person_id, Object rs_manager_id,
                          Double user_status, String user_org_name, String user_lockreason, Object user_isrobot, String user_ip,
                          String user_robotlogin, String user_robotpassword, String note, String crm_phone, String crm_email) {
        this.id_bitrix = id_bitrix.toString();
        this.user_lastname = user_lastname;
        this.user_name = user_name;
        this.user_surname = user_surname;
        this.user_phone = user_phone;
        this.user_email = user_email;
        this.comment = comment;
        this.corr_id = corr_id.toString();
        //this.rs_contact_person_id = rs_contact_person_id.toString();
        //this.rs_manager_id = rs_manager_id.toString();
        this.user_status = user_status;
        this.user_org_name = user_org_name;
        this.user_lockreason = user_lockreason;
        this.user_isrobot = user_isrobot;
        this.user_ip = user_ip;
        this.user_robotlogin = user_robotlogin;
        this.user_robotpassword = user_robotpassword;
        this.note = note;
        this.crm_email = crm_email;
        this.crm_phone = crm_phone;
    }

    public B2bUserElement(Integer id_bitrix, String user_lastname, String user_name, String user_surname, String user_phone,
                          String user_email, String comment, Object corr_id, Object rs_manager_id,
                          Double user_status, String user_org_name, String user_lockreason, Object user_isrobot, String user_ip,
                          String user_robotlogin, String user_robotpassword, String note, String registration_scenario, String holding_admin_process_status,
                          String user_extra_phone, Boolean is_phone_verified, Boolean is_email_verified, String crm_phone, String crm_email,
                          String user_lockreason_type, String last_b2b_auth_date, String last_oapi_auth_date, String last_soap_auth_date) {
        this.id_bitrix = id_bitrix.toString();
        this.user_lastname = user_lastname;
        this.user_name = user_name;
        this.user_surname = user_surname;
        this.user_phone = user_phone;
        this.user_email = user_email;
        this.comment = comment;
        this.corr_id = corr_id.toString();
        //this.rs_manager_id = rs_manager_id.toString();
        this.user_status = user_status;
        this.user_org_name = user_org_name;
        this.user_lockreason = user_lockreason;
        this.user_isrobot = user_isrobot;
        this.user_ip = user_ip;
        this.user_robotlogin = user_robotlogin;
        this.user_robotpassword = user_robotpassword;
        this.note = note;
        this.holding_admin_process_status = holding_admin_process_status;
        this.registration_scenario = registration_scenario;
        this.user_extra_phone = user_extra_phone;
        this.is_phone_verified = is_phone_verified;
        this.is_email_verified = is_email_verified;
        this.crm_email = crm_email;
        this.crm_phone = crm_phone;
        this.user_lockreason_type = user_lockreason_type;
        this.last_b2b_auth_date = last_b2b_auth_date;
        this.last_oapi_auth_date = last_oapi_auth_date;
        this.last_soap_auth_date = last_soap_auth_date;
    }

    public B2bUserElement(String user_lastname, String user_name, String user_surname, String user_phone, String user_email, String comment, Integer corr_id,
                          Integer rs_contact_person_id, Integer rs_manager_id, Double user_status, String user_org_name, String user_lockreason,
                          boolean user_isrobot, String user_ip, String user_robotlogin, String user_robotpassword, String note, String registration_scenario,
                          String holding_admin_process_status, String decision_type_status, String user_extra_phone, Boolean is_phone_verified,
                          Boolean is_email_verified, String crm_phone, String crm_email,
                          String user_lockreason_type, String last_b2b_auth_date, String last_oapi_auth_date, String last_soap_auth_date) {
        this.user_lastname = user_lastname;
        this.user_name = user_name;
        this.user_surname = user_surname;
        this.user_phone = user_phone;
        this.user_email = user_email;
        this.comment = comment;
        this.corr_id = corr_id.toString();
        //this.rs_contact_person_id = rs_contact_person_id.toString();
        //this.rs_manager_id = rs_manager_id.toString();
        this.user_status = user_status;
        this.user_org_name = user_org_name;
        this.user_lockreason = user_lockreason;
        this.user_isrobot = user_isrobot;
        this.user_ip = user_ip;
        this.user_robotlogin = user_robotlogin;
        this.user_robotpassword = user_robotpassword;
        this.note = note;
        this.registration_scenario = registration_scenario;
        this.holding_admin_process_status = holding_admin_process_status;
        this.decision_type_status = decision_type_status;
        this.user_extra_phone = user_extra_phone;
        this.is_phone_verified = is_phone_verified;
        this.is_email_verified = is_email_verified;
        this.crm_email = crm_email;
        this.crm_phone = crm_phone;
        this.user_lockreason_type = user_lockreason_type;
        this.last_b2b_auth_date = last_b2b_auth_date;
        this.last_oapi_auth_date = last_oapi_auth_date;
        this.last_soap_auth_date = last_soap_auth_date;
    }

    public B2bUserElement(String user_lastname, String user_name, String user_surname, String user_phone, String user_email,
                          String comment, Integer corr_id, Integer rs_contact_person_id, Integer rs_manager_id, Double user_status,
                          String user_org_name, String user_lockreason, boolean user_isrobot, String user_ip, String user_robotlogin,
                          String user_robotpassword, String note, String registration_scenario, String holding_admin_process_status,
                          String decision_type_status, Boolean is_phone_verified, Boolean is_email_verified, String crm_phone, String crm_email) {
        this.user_lastname = user_lastname;
        this.user_name = user_name;
        this.user_surname = user_surname;
        this.user_phone = user_phone;
        this.user_email = user_email;
        this.comment = comment;
        this.corr_id = corr_id.toString();
        //this.rs_contact_person_id = rs_contact_person_id.toString();
        //this.rs_manager_id = rs_manager_id.toString();
        this.user_status = user_status;
        this.user_org_name = user_org_name;
        this.user_lockreason = user_lockreason;
        this.user_isrobot = user_isrobot;
        this.user_ip = user_ip;
        this.user_robotlogin = user_robotlogin;
        this.user_robotpassword = user_robotpassword;
        this.note = note;
        this.holding_admin_process_status = holding_admin_process_status;
        this.decision_type_status = decision_type_status;
        this.user_extra_phone = user_extra_phone;
        this.is_phone_verified = is_phone_verified;
        this.is_email_verified = is_email_verified;
        this.crm_email = crm_email;
        this.crm_phone = crm_phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        B2bUserElement that = (B2bUserElement) o;
        return this.getUserIsrobot() == that.getUserIsrobot()
                && Objects.equals(this.getRsId(), that.getRsId())
                && Objects.equals(this.getIdBitrix(), that.getIdBitrix())
                && Objects.equals(this.getUserRegDate(), that.getUserRegDate())
                && Objects.equals(this.getUserEmail(), that.getUserEmail())
                && Objects.equals(this.getComment(), that.getComment())
                && Objects.equals(this.getCorrId(), that.getCorrId())
                //&& Objects.equals(this.getRsContactPersonId(), that.getRsContactPersonId())
                //&& Objects.equals(this.getRsManagerId(), that.getRsManagerId())
                && Objects.equals(this.getUserStatus(), that.getUserStatus())
                && Objects.equals(this.getUserOrgName(), that.getUserOrgName())
                && Objects.equals(this.getUserLockreason(), that.getUserLockreason())
                && Objects.equals(this.getUserIp(), that.getUserIp())
                && Objects.equals(this.getUserRobotlogin(), that.getUserRobotlogin())
                && Objects.equals(this.getHoldingAdminProcessStatus(), that.getHoldingAdminProcessStatus())
                && Objects.equals(this.getUserRobotpassword(), that.getUserRobotpassword())
                && Objects.equals(this.getRegistrationScenario(), that.getRegistrationScenario())
                && Objects.equals(this.getDecisionTypeStatus(), that.getDecisionTypeStatus())
                && Objects.equals(this.getIsEmailVerified(), that.getIsEmailVerified())
                && Objects.equals(this.getIsPhoneVerified(), that.getIsPhoneVerified())
                && Objects.equals(this.getUserExtraPhone(), that.getUserExtraPhone())
                && Objects.equals(this.getCrmEmail(), that.getCrmEmail())
                && Objects.equals(this.getCrmPhone(), that.getCrmPhone())
                && Objects.equals(this.getNote(), that.getNote());
    }

    @Override
    public String toString() {
        return "B2bUserElement{" +
                "rs_id=" + rs_id +
                ", id_bitrix='" + id_bitrix + '\'' +
                ", user_reg_date='" + user_reg_date + '\'' +
                ", user_lastname='" + user_lastname + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_surname='" + user_surname + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", user_email='" + user_email + '\'' +
                ", comment='" + comment + '\'' +
                ", corr_id='" + corr_id + '\'' +
                ", rs_contact_person_id='" + rs_contact_person_id + '\'' +
                ", user_status=" + user_status +
                ", user_org_name='" + user_org_name + '\'' +
                ", user_lockreason='" + user_lockreason + '\'' +
                ", user_isrobot=" + user_isrobot +
                ", user_ip='" + user_ip + '\'' +
                ", user_robotlogin='" + user_robotlogin + '\'' +
                ", user_robotpassword='" + user_robotpassword + '\'' +
                ", note='" + note + '\'' +
                ", registration_scenario='" + registration_scenario + '\'' +
                ", holding_admin_process_status='" + holding_admin_process_status + '\'' +
                ", decision_type_status='" + decision_type_status + '\'' +
                ", user_extra_phone='" + user_extra_phone + '\'' +
                ", is_phone_verified=" + is_phone_verified +
                ", is_email_verified=" + is_email_verified +
                ", crm_phone='" + crm_phone + '\'' +
                ", crm_email='" + crm_email + '\'' +
                '}';
    }

    public String getIdBitrix() {
        return id_bitrix;
    }

    public Object getRsId() {
        if (rs_id == null) return 0.0;
        return rs_id;
    }

    public String getUserRegDate() {
        return user_reg_date;
    }

    public String getUserLastname() {
        return user_lastname;
    }

    public String getUserName() {
        return user_name;
    }

    public String getUserSurname() {
        return user_surname;
    }

    public String getUserPhone() {
        return user_phone;
    }

    public String getUserEmail() {
        return user_email;
    }

    public String getComment() {
        return comment;
    }

    public String getCorrId() {
        return corr_id;
    }

    public String getRsContactPersonId() {
        return rs_contact_person_id;
    }

    public String getRsManagerId() {
        return "";
    }

    public Object getUserStatus() {
        return user_status;
    }

    public String getUserOrgName() {
        return user_org_name;
    }

    public String getUserLockreason() {
        return user_lockreason;
    }

    public Boolean getUserIsrobot() {
        if (user_isrobot instanceof Integer) return (Integer)user_isrobot == 1;
        else return false;
    }

    public String getUserIp() {
        return user_ip;
    }

    public String getUserRobotlogin() {
        return user_robotlogin;
    }

    public String getUserRobotpassword() {
        return user_robotpassword;
    }

    public String getNote() {
        return note;
    }

    public String getRegistrationScenario() {
        return registration_scenario;
    }

    public String getHoldingAdminProcessStatus() {
        return holding_admin_process_status;
    }

    public String getDecisionTypeStatus() {
        return decision_type_status;
    }

    public String getUserExtraPhone() {
        return user_extra_phone;
    }

    public void setUserExtraPhone(String user_extra_phone) {
        this.user_extra_phone = user_extra_phone;
    }

    public Object getIsPhoneVerified() {
        return is_phone_verified;
    }

    public void setIsPhoneVerified(Object is_phone_verified) {
        this.is_phone_verified = is_phone_verified;
    }

    public Object getIsEmailVerified() {
        return is_email_verified;
    }

    public void setIsEmailVerified(Object is_email_verified) {
        this.is_email_verified = is_email_verified;
    }

    public void setIdBitrix(String id_bitrix) {
        this.id_bitrix = id_bitrix;
    }

    public void setUserLastname(String user_lastname) {
        this.user_lastname = user_lastname;
    }

    public void setUserName(String user_name) {
        this.user_name = user_name;
    }

    public void setUserSurname(String user_surname) {
        this.user_surname = user_surname;
    }

    public void setUserPhone(String user_phone) {
        this.user_phone = user_phone;
    }

    public void setUserEmail(String user_email) {
        this.user_email = user_email;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCorrId(String corr_id) {
        this.corr_id = corr_id;
    }

    public void setRsContactPersonId(String rs_contact_person_id) {
        this.rs_contact_person_id = rs_contact_person_id;
    }

    public void setRsManagerId(String rs_manager_id) {
        //this.rs_manager_id = rs_manager_id;
    }

    public void setUserStatus(Object user_status) {
        this.user_status = user_status;
    }

    public void setUserOrgName(String user_org_name) {
        this.user_org_name = user_org_name;
    }

    public void setUserLockreason(String user_lockreason) {
        this.user_lockreason = user_lockreason;
    }

    public void setUserIsrobot(Object user_isrobot) {
        this.user_isrobot = user_isrobot;
    }

    public void setUserIp(String user_ip) {
        this.user_ip = user_ip;
    }

    public void setUserRobotlogin(String user_robotlogin) {
        this.user_robotlogin = user_robotlogin;
    }

    public void setUserRobotpassword(String user_robotpassword) {
        this.user_robotpassword = user_robotpassword;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setRegistrationScenario(String registration_scenario) {
        this.registration_scenario = registration_scenario;
    }

    public void setHoldingAdminProcessStatus(String holding_admin_process_status) {
        this.holding_admin_process_status = holding_admin_process_status;
    }

    public void setDecisionTypeStatus(String decision_type_status) {
        this.decision_type_status = decision_type_status;
    }

    public String getCrmPhone() {
        return crm_phone;
    }

    public String getCrmEmail() {
        return crm_email;
    }

    public void setCrmPhone(String crm_phone) {
        this.crm_phone = crm_phone;
    }

    public void setCrmEmail(String crm_email) {
        this.crm_email = crm_email;
    }

    public String getLastB2bAuthDate() {
        return last_b2b_auth_date;
    }

    public String getLastOapiAuthDate() {
        return last_oapi_auth_date;
    }

    public String getLastSoapAuthDate() {
        return last_soap_auth_date;
    }

     public void setLastB2bAuthDate(String last_b2b_auth_date) {
        this.last_b2b_auth_date = last_b2b_auth_date;
    }

    public void setLastOapiAuthDate(String last_oapi_auth_date) {
        this.last_oapi_auth_date = last_oapi_auth_date;
    }

    public void setLastSoapAuthDate(String last_soap_auth_date) {
        this.last_soap_auth_date = last_soap_auth_date;
    }

    private static String changeDateFormat(String date, String input) {
        DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern(input);
        LocalDateTime localDateTime = LocalDateTime.parse(date, dbFormatter);

        LocalDateTime truncatedDateTime = localDateTime.withNano((localDateTime.getNano() / 1_000_000) * 1_000_000);
        return truncatedDateTime + "Z";
    }
}
