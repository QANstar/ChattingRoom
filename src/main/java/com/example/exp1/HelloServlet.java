package com.example.exp1;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/helloServlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        Cookie cookie = null;
        Cookie cookieSecond = null;
        Cookie[] cookies = request.getCookies();
        String username = request.getParameter("email");
        String isChecked = request.getParameter("vehicle");
        String password = request.getParameter("password");

        if("yes".equals(isChecked)){    //勾选记住密码
            if(cookies!= null){
                //boolean flag = false;
                for(int i =0 ;i<cookies.length;i++){
                    if(cookies[i].getName().equals("email")){
//                        String v=cookies[i].getValue();
//                        int value = Integer.parseInt(v)+1;
//                        cookies[i].setValue(Integer.toString(value));
//                        response.addCookie(cookies[i]);
//                        flag = true;
                        cookie = cookies[i];
                    }       //有cookie 则保存下来
                    if(cookies[i].getName().equals("pwd")){

                        cookieSecond = cookies[i];
                    }
                }//end for  结束for循环 没有找到
            }
            //cookie等于空则创建
            if(cookie == null){
                int maxAge = 10000000;
                cookie = new Cookie("email",username);
                cookieSecond = new Cookie("password",password);
                cookie.setPath(request.getContextPath());
                cookie.setMaxAge(maxAge);
                response.addCookie(cookie);
                cookieSecond.setPath(request.getContextPath());
                cookieSecond.setMaxAge(maxAge);
                response.addCookie(cookieSecond);
            }
            //System.out.println("111111");
        }else{      //没有勾选复选框记住密码
            int maxAge = 0;     //cookie清除
            cookie = new Cookie("email",username);
            cookieSecond = new Cookie("password",password);
            cookie.setPath(request.getContextPath());
            cookie.setMaxAge(maxAge);
            response.addCookie(cookie);
            cookieSecond.setPath(request.getContextPath());
            cookieSecond.setMaxAge(maxAge);
            response.addCookie(cookieSecond);
        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet First</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Student Data</h1>");
            out.println("<span>username：</span>"+request.getParameter("email")+"<br>");
            out.println("<span>password：</span>"+request.getParameter("password")+"<br>");

        }
    }

    public void destroy() {
    }
}