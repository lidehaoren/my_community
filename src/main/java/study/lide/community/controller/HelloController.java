package study.lide.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lide
 * @create 2021-06-24-9:41
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "UserName") String name, Model model){
        model.addAttribute("UserName", name);
        return "hello";
    }
}
