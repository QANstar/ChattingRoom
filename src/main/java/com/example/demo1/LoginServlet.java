package com.example.demo1;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class LoginServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        int maxAge;
        String[] rightEmail = new String[]{"李宗遇", "李韦德", "梁开平", "黄涛"};
        String[] rightPassword = new String[]{"201921098306", "201921098311", "201921098310", "201921098299"};
        boolean login_right = false;
        Cookie emailCookie = null;
        Cookie passwordCookie = null;
        Cookie[] cookies = request.getCookies();

        for (int i = 0; i < 4; i++) {
            if (request.getParameter("email").equals(rightEmail[i]) &&
                    request.getParameter("password").equals(rightPassword[i])) {
                login_right = true;
            }
        }

        if (login_right) {
            //加入登录会话
            //获取会话
            HttpSession session = request.getSession();

            //存入登入会话
            session.setAttribute("login", request.getParameter("email"));
            session.setMaxInactiveInterval(2);
            //加入登录人
            String loginUser = (String) request.getServletContext().getAttribute("userName");
            if (loginUser == null) {
                loginUser = request.getParameter("email");
            } else {
                List<String> nameList =  Arrays.asList(loginUser.split(","));
                if (!nameList.contains(request.getParameter("email"))){
                    loginUser += "," + request.getParameter("email");
                }
            }
            request.getServletContext().setAttribute("userName",loginUser);


            if (cookies != null) {
                for (int j = 0; j < cookies.length; j++) {
                    if (cookies[j].getName().equals("email")) {
                        emailCookie = cookies[j];
                    }
                    if (cookies[j].getName().equals("password")) {
                        passwordCookie = cookies[j];
                    }
                }
            }
            if (request.getParameter("remember") != null) {
                maxAge = 7 * 24 * 60 * 60 * 1000;//记录一个月
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
            response.sendRedirect("takingPage.jsp");
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    public void destroy() {
    }
}