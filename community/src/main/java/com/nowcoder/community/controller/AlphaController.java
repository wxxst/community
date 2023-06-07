package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot.";
    }

    //声明
    @RequestMapping("/data")
    @ResponseBody
    //注入service，把find结果返回给浏览器
    public String getData(){
        return alphaService.find();
    }

    //获得请求、响应对象
    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取请求数据
        //打印请求方式 Get/Post
        System.out.println(request.getMethod());
        //请求路径
        System.out.println(request.getServletPath());
        //请求消息头
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements())
        {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ":" + value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset = utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("<h1>牛客网</h1>");
        writer.close();
    }

    //GET请求，向服务器获取数据，默认的是GET请求
    // /students?current=1&limit=20
    //获取浏览器中的参数
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
        @RequestParam(name = "current", required = false, defaultValue = "1") int current,
        @RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    //当路径中没有参数名称，获取参数值时，要使用注解 @PathVariable("参数名称")
    // /student/123
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }

    //Post请求  向服务器提交数据
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //服务器向浏览器响应HTML数据
    //浏览器查询一个老师，服务器就查到了这个老师的数据，然后需要把老师相关的数据响应给浏览器
    //不加ResponseBody默认返回HTML
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "张三");
        mav.addObject("age", 30);
        mav.setViewName("/demo/view");
        return mav;
    }

    //查询学校
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","BJ");
        model.addAttribute("age",80);
        return "/demo/view";
    }

    //响应JSON数据，在异步请求当中
    //Java对象 -> JSON字符串 -> JS对象
    //DispathServlet调用这个方法时，看到加了ResponseBody注解，并且返回Map类型，会自动将Map类型转成JSON字符串发送给浏览器
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age","23");
        emp.put("salary",8000.00);
        return emp;
    }

    //返回多个员工
    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",8000.00);
        list.add(emp);

        Map<String, Object> emp1 = new HashMap<>();
        emp1.put("name","里斯");
        emp1.put("age",24);
        emp1.put("salary",80055.00);
        list.add(emp1);

        Map<String, Object> emp2 = new HashMap<>();
        emp2.put("name","王五");
        emp2.put("age",25);
        emp2.put("salary",8098.00);
        list.add(emp2);

        return list;
    }
}
