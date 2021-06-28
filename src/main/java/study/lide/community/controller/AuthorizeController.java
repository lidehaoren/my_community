package study.lide.community.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.lide.community.dto.AccessTokenDTO;
import study.lide.community.dto.GithubUser;
import study.lide.community.mapper.UserMapper;
import study.lide.community.model.User;
import study.lide.community.provider.GithubProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author lide
 * @create 2021-06-25-16:54
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirect;



    @GetMapping("/callback")
    /*用户选择授权给我们之后，github会调用我们的回调地址，并携带一些参数，如code*/
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_secret(clientSecret);
        /*我们可以根据我们app的client_id以及code向github发送post请求申请一个Token*/
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        /*通过该token向github发起get请求，获取用户github的账户信息*/
        GithubUser githubUser = githubProvider.getUser(accessToken);

        if(githubUser != null){
            User user = new User();
            //将用户编号作为cookie
            String token = UUID.randomUUID().toString();


            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModify(user.getGmtCreate());
            /*将用户信息保存到数据库中*/
            userMapper.insert(user);
            /*
            * 实现登录持久化操作，因为当一个浏览器的session结束之后，理论上用户在一定期内
            * 是不需要再次登录的
            * 1.将用户编号作为cookie
            * */
            response.addCookie(new Cookie("token", token));

            //登录成功，重定向回到主页
            return "redirect:/";
        }else{
            return "redirect:/";
        }


    }
}
