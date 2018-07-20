package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pojo.Account;
import service.AccountService;
import tools.DrawPictures;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
     * 生成图片验证码，传入到页面，同时添加到session中
     *
     * @param request  获取session对象
     * @param response 向页面发送图片验证码
     */
    @RequestMapping("/setPicture")
    public void setPicture(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("images/jpeg"); //设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache"); //设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);

        //调用编写的工具类生成验证码图片，传入到客户端以及session中
        DrawPictures drawPictures = new DrawPictures();
        try {
            drawPictures.randCode(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登陆post方法，将页面填写的数据传入到service中；同时根据service的返回信息跳转到相应的页面
     * 同时变量名后缀  带0的和账号有关   1和密码有关
     *
     * @param loginId  页面用户填写的账号，controller将值传入service中进行查询匹配
     * @param loginPwd 页面用户填写的密码，同上
     * @param model    框架中自带参数，将错误信息带入到页面
     * @param check0   页面记住账号单选按钮，为yes时记住账号，无论何时都记住账号；记住信息就是将信息存入到cookie中
     * @param check1   页面记住密码单选按钮，为yes时记住密码，仅当点击了记住账号且账号正确，密码正确时；记住信息原理同上
     * @param response response对象，向页面添加cookie值
     * @param request  request对象，向服务器端session中存储用户账号数据
     * @param code     用户输入的图片验证码
     * @return 返回页面，根据service返回的信息不同，返回到的页面不同
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ModelAndView login(String loginId, String loginPwd,
                              Model model, String check0, String check1, String code,
                              HttpServletResponse response, HttpServletRequest request) {
        //msg将错误信息反馈给登陆界面
        String msg;
        if ((code.toUpperCase()).equals(request.getSession().getAttribute("SessionPictures"))) {
            //      将cookie值存在时间设置为20s是为了之后测试方便
            final int maxTimeCookie = 20;
            //从controller传入到service中，service根据查询到的信息进行赋值
            StringBuffer message = new StringBuffer();
            Account account = service.login(loginId, loginPwd, message);
            Cookie cookieId = new Cookie("cookieId", loginId);
            Cookie cookiePwd = new Cookie("cookiePwd", loginPwd);
            cookieId.setMaxAge(maxTimeCookie);
            cookiePwd.setMaxAge(maxTimeCookie);
            if ("yes".equals(check0)) {
                response.addCookie(cookieId);
            }
            if (account == null) {
                if (message.toString().equals("0")) {
                    msg = "账号未注册";
                    model.addAttribute("msgId", msg);
                    return new ModelAndView("logIn");
                } else {
                    msg = "密码错误";
                    model.addAttribute("msgPwd", msg);
                    return new ModelAndView("logIn");
                }
            } else {
                //将用户账号信息存入session中，方便之后通过session来调用用户信息
                request.getSession().setAttribute("sessionAccount", account);
                if ("yes".equals(check0)) {
                    if ("yes".equals(check1)) {
                        response.addCookie(cookiePwd);
                    }
                }
                //通过账号角色返回到不同的菜单中
                if (account.getType() == 1) {
                    return new ModelAndView("student/studentIndex");
                } else {
                    return new ModelAndView("teacher/teacherIndex");
                }
            }
        } else if (code == "") {
            msg = "请输入验证码";
            model.addAttribute("msgCode", msg);
            return new ModelAndView("logIn");
        } else {
            msg = "验证码错误";
            model.addAttribute("msgCode", msg);
            return new ModelAndView("logIn");
        }
    }

    /**
     * 用户注销登陆，清除session中存储的账户信息
     *
     * @param request 获取session对象
     * @return 返回到主页
     */
    @RequestMapping("/exit")
    public ModelAndView exit(HttpServletRequest request) {
        request.getSession().removeAttribute("sessionAccount");
        return new ModelAndView("index");
    }
}
