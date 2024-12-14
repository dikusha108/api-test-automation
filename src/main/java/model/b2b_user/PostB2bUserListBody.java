package model.b2b_user;

import java.util.List;

public class PostB2bUserListBody {
    private List<Object> corr_id_list;
    private List<Integer> filter_by_user_status;
    private List<String> filter_by_admin_status;
    private String filter_by_user_start_reg_date;
    private String filter_by_end_last_auth_date;

    public PostB2bUserListBody(List<Object> corr_id_list) {
        this.corr_id_list = corr_id_list;
    }

    public PostB2bUserListBody(List<Object> corr_id_list, List<Integer> filter_by_user_status) {
        this.corr_id_list = corr_id_list;
        this.filter_by_user_status = filter_by_user_status;
    }

    public PostB2bUserListBody(List<Object> corr_id_list, List<Integer> filter_by_user_status, List<String> filter_by_admin_status) {
        this.corr_id_list = corr_id_list;
        this.filter_by_user_status = filter_by_user_status;
        this.filter_by_admin_status = filter_by_admin_status;
    }

    public PostB2bUserListBody(String filter_by_user_start_reg_date) {
        this.filter_by_user_start_reg_date = filter_by_user_start_reg_date;
    }

    public PostB2bUserListBody(List<Object> corr_id_list, List<Integer> filter_by_user_status, List<String> filter_by_admin_status,
                               String filter_by_user_start_reg_date, String filter_by_end_last_auth_date) {
        this.corr_id_list = corr_id_list;
        this.filter_by_user_status = filter_by_user_status;
        this.filter_by_admin_status = filter_by_admin_status;
        this.filter_by_user_start_reg_date = filter_by_user_start_reg_date;
        this.filter_by_end_last_auth_date = filter_by_end_last_auth_date;
    }
}
