package com.example.demo1;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet2", value = "/helloServlet2")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        //正确的账号和密码
        String rightEmail = "1435385924@qq.com";
        String rightPassword = "111";

        //判断输入的学号和姓名是否正确
        if (request.getParameter("email").equals(rightEmail) && request.getParameter("password").equals(rightPassword)) {

            Cookie emailCookie = null;
            Cookie passwordCookie = null;
            Cookie[] cookies = request.getCookies();
            //获取学号和姓名的cookie
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals("email")) {
                        emailCookie = cookies[i];
                    }
                    if (cookies[i].getName().equals("password")) {
                        passwordCookie = cookies[i];
                    }

                }
            }

            int maxAge;
            //判断是否记住账号密码
            if (request.getParameter("remember")!=null) {
                maxAge = 30*24*60*60*1000;//记录一个月
            } else {
                maxAge = 0;//删除
            }
            //判断cookie是否存在
            if (emailCookie == null) {
                emailCookie = new Cookie("email", request.getParameter("email"));
                emailCookie.setPath(request.getContextPath());
                emailCookie.setMaxAge(maxAge);
                response.addCookie(emailCookie);
            } else {
                emailCookie.setValue(request.getParameter("email"));
                emailCookie.setMaxAge(maxAge);
                response.addCookie(emailCookie);
            }

            //判断cookie是否存在
            if (passwordCookie == null) {
                passwordCookie = new Cookie("password", request.getParameter("password"));
                passwordCookie.setPath(request.getContextPath());
                passwordCookie.setMaxAge(maxAge);
                response.addCookie(passwordCookie);
            } else {
                passwordCookie.setValue(request.getParameter("password"));
                passwordCookie.setMaxAge(maxAge);
                response.addCookie(passwordCookie);
            }


            //输出信息
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<span>姓名：</span>");
            out.println("<span>" + request.getParameter("email") + "</span><br>");
            out.println("<span>学号：</span>");
            out.println("<span>" + request.getParameter("password") + "</span><br>");
            out.println("<span>是否记住学号：</span>");
            out.println("<span>" + request.getParameter("remember") + "</span>");
            out.println("</body></html>");
        } else {
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<span>学号或姓名输入错误</span><br>");
            out.println("</body></html>");
        }


    }

    public void destroy() {
    }
}