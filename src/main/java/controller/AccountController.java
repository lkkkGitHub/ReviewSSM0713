package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pojo.Account;
import service.AccountService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

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
     *
     * @return 返回一个携带页面名称的ModelAndView对象
     */
    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public ModelAndView login() {
        return new ModelAndView("logIn");
    }

    /**
     * 登陆post方法，将页面填写的数据传入到service中；同时根据service的返回信息跳转到相应的页面
     * 同时变量名后缀  带0的和账号有关   1和密码有关
     * @param loginId  页面用户填写的账号，controller将值传入service中进行查询匹配
     * @param loginPwd 页面用户填写的密码，同上
     * @param model    框架中自带参数，将错误信息带入到页面
     * @param check0   页面记住账号单选按钮，为yes时记住账号，无论何时都记住账号；记住信息就是将信息存入到cookie中
     * @param check1   页面记住密码单选按钮，为yes时记住密码，仅当点击了记住账号且账号正确，密码正确时；记住信息原理同上
     * @param response response对象，向页面添加cookie值
     * @return 返回页面，根据service返回的信息不同，返回到的页面不同
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ModelAndView login(String loginId, String loginPwd,
                              Model model, String check0, String check1,
                              HttpServletResponse response) {
        //从controller传入到service中，service根据查询到的信息进行赋值
        StringBuffer message = new StringBuffer();
        Account account = service.login(loginId, loginPwd, message);
        Cookie cookie0 = new Cookie("cookieId", loginId);
        Cookie cookie1 = new Cookie("cookiePwd", loginPwd);
//      将cookie值存在时间设置为20s是为了之后测试方便
        cookie0.setMaxAge(20);
        cookie1.setMaxAge(20);
        if ("yes".equals(check0)) {
            response.addCookie(cookie0);
        }
        //msg将错误信息反馈给登陆界面
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
            if ("yes".equals(check0)) {
                if ("yes".equals(check1)) {
                    response.addCookie(cookie1);
                }
            }
            //通过账号角色返回到不同的菜单中
            if (account.getType() == 1) {
                return new ModelAndView("student/studentIndex");
            } else {
                return new ModelAndView("teacher/teacherIndex");
            }

        }
    }
}