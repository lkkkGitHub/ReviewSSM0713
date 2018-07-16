package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pojo.Account;
import service.AccountService;

/**
 * @author lk
 * 2018/7/16 10:14
 * @description: 账户信息控制器
 */
@Controller
@RequestMapping("/Account")
public class AccountController {
    /**
     * 自动添加AccountService
     */
    @Autowired
    private AccountService service;

    /**
     * get方法，跳转到登陆界面
     * @return 返回一个携带页面名称的ModelAndView对象
     */
    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public ModelAndView login() {
        return new ModelAndView("logIn");
    }

    /**
     * 登陆post方法，将页面填写的数据传入到service中；同时根据service的返回信息跳转到相应的页面
     * @param loginId 页面用户填写的账号，controller将值传入service中进行查询匹配
     * @param loginPwd 页面用户填写的密码，同上
     * @param model 框架中自带参数，将错误信息带入到页面
     * @return 返回页面，根据service返回的信息不同，返回到的页面不同
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ModelAndView login(String loginId, String loginPwd, Model model) {
        //从controller传入到service中，service根据查询到的信息进行赋值
        StringBuffer message = new StringBuffer();
        Account account = service.login(loginId, loginPwd, message);
        String msg;
        if (account == null) {
            if (message.toString().equals("0")) {
                msg = "账号未注册";
                model.addAttribute("msg0", msg);
                return new ModelAndView("logIn");
            } else {
                msg = "密码错误";
                model.addAttribute("msg1", msg);
                return new ModelAndView("logIn");
            }
        } else {
            return new ModelAndView("index");
        }
    }
}
