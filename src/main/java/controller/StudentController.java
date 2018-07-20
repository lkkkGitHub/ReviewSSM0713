package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pojo.Account;
import pojo.Student;
import service.StudentService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lk
 * 2018/7/19 20:52
 * @description: 连接service和页面传输数据
 */
@RequestMapping("/Student")
@Controller
public class StudentController {
    /**
     * 实例化service对象
     */
    @Autowired
    private StudentService studentService;

    /**
     * 获取session中存储的账号信息，传入service中，查询学生的个人信息，
     * 根据查询的信息是否为空，来返回不同的页面
     * @param request 获取session对象
     * @param model 将信息返回到页面
     * @return 带回查询信息返回到的页面中
     */
    @RequestMapping("/findStuInfo")
    public ModelAndView findStuInfo(HttpServletRequest request, Model model) {
        Student student = studentService.findStuInfo(
                ((Account) request.getSession().getAttribute("sessionAccount"))
                        .getAccountId());
        if (student == null) {
            return new ModelAndView("student/studentIndex");
        } else {
            model.addAttribute("student", student);
            return new ModelAndView("student/showInfo");
        }
    }
}
