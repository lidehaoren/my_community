package study.lide.community.dto;

/**
 * @author lide
 * @create 2021-06-25-18:18
 */
public class AccessTokenDTO {
    /*
        用户id和密码是用来标识我们这个应用的，网站也是一个应用
    *   code是用户登录我们网站时，其实是先登录到github，
        登录成功后，github通过redirect_uri提供给我们一个code
        我们可以通过post请求携带code，从github处得到一个token
        通过token可以得到之前登录github的用户的账户信息，主要是id，唯一

    * */
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "AccessTokenDTO{" +
                "client_id='" + client_id + '\'' +
                ", client_secret='" + client_secret + '\'' +
                ", code='" + code + '\'' +
                ", redirect_uri='" + redirect_uri + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
