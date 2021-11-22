package com.example.demo1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "addServlet", value = "/addServlet")
public class AddMessageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String message = req.getParameter("message");
        String talkMessage = name + "," +message;

        //加入消息
        String messStr = (String) req.getServletContext().getAttribute("talkMessage");
        if (messStr == null) {
            messStr = talkMessage;
        } else {
            messStr += ";" + talkMessage;
        }
        req.getServletContext().setAttribute("talkMessage",messStr);
        PrintWriter out = resp.getWriter();
        out.println("成功");
    }
}
