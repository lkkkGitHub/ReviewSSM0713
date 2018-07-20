package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
            request.getSession().setAttribute("studentInfo", student);
            model.addAttribute("student", student);
            return new ModelAndView("student/showInfo");
        }
    }

    /**
     * 跳转到修改页面，初始化修改页面的信息
     * @param request 获取session对象
     * @param model 传入学生信息数据
     * @return 返回到修改信息页面
     */
    @RequestMapping(value = "/updateStu", method = {RequestMethod.GET})
    public ModelAndView updateStuInfo(HttpServletRequest request, Model model) {
        Student student = (Student) request.getSession().getAttribute("studentInfo");
        model.addAttribute("student", student);
        return new ModelAndView("student/updateStu");
    }

    /**
     * 将用户页面输入的信息，和数据库原本信息都传入到service中，判断用户时候进行了信息的修改；
     * 未修改即返回3 反馈给更新页面数据未更新； 修改了即返回！=3，重定向到用户页面信息展示界面
     * @param student 用户输入信息进行封装
     * @param request 获取session对象中的学生信息
     * @param model 将错误信息带回给页面
     * @return 页面或者重定向到信息展示方法中
     */
    @RequestMapping(value = "/updateStu", method = {RequestMethod.POST})
    public ModelAndView updateStuInfo(Student student, Model model, HttpServletRequest request) {
        Student studentInfo = (Student) request.getSession().getAttribute("studentInfo");
        int x = studentService.updateStu(student, studentInfo);
        if (x == 3) {
            model.addAttribute("msg", "未进行任何信息更新");
            return new ModelAndView("student/updateStu");
        } else {
            return new ModelAndView("forward:findStuInfo");
        }
    }
}
