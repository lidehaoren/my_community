package study.lide.community.controller;

import org.h2.engine.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.lide.community.mapper.UserMapper;
import study.lide.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author lide
 * @create 2021-06-24-9:41
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            //当第一次登录之后，会在数据库中保存token，浏览器也是有cookie保存token
            if ("token".equals(cookie.getName())){
                String token = cookie.getValue();
                //如果数据库中存在对应的token，表示该用户最近登录过，可以不用再次登录了
                //这里与session不同，这里实现的是当所有浏览器关闭，一次session结束之后
                //的持久化登录
                User user = userMapper.findByToken(token);
                if(user != null){
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }


        return "index";
    }
}
