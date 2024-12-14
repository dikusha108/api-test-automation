package model.b2b_user;

import java.util.List;

public class PostB2bUserListResponse {
    List<B2bUserElement> data;

    public PostB2bUserListResponse(List<B2bUserElement> data) {
        this.data = data;
    }

    public List<B2bUserElement> getData() {
        return data;
    }
}
