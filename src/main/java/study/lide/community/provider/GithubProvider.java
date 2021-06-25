package study.lide.community.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;
import study.lide.community.dto.AccessTokenDTO;
import study.lide.community.dto.GithubUser;

import java.io.IOException;

/**
 * @author lide
 * @create 2021-06-25-18:16
 */
@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            /*获取到token之后，对token进行解析
            * access_token=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&scope=user&token_type=bearer
            * */
            String value = response.body().string();
            System.out.println("value:" + value);
            String[] split = value.split("&");
            String tokenStr = split[0];
            String token = tokenStr.split("=")[1];

            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String value = response.body().string();
            System.out.println(value);
            /*将string类型的json对象给转换成object对象*/
            GithubUser githubUser = JSON.parseObject(value, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
