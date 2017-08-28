package eventbrite.action;

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport{
    private static final long serialVersionUID = 1L;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
