package com.example.demo1;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Arrays;
import java.util.List;

@WebListener()
public class PeopleListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        String current = (String) se.getSession().getServletContext().getAttribute("online");
        if (current == null) {
            current = "0";
        }
        int num = Integer.parseInt(current);
        num++;
        current = String.valueOf(num);
        se.getSession().getServletContext().setAttribute("online", current);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String current = (String) se.getSession().getServletContext().getAttribute("online");
        if (current == null) {
            current = "0";
        }
        int num = Integer.parseInt(current);
        num--;
        current = String.valueOf(num);
        se.getSession().getServletContext().setAttribute("online", current);

        //删除登录人
        String loginUser = (String) se.getSession().getServletContext().getAttribute("userName");
        if (loginUser != null) {
            String userName = (String) se.getSession().getAttribute("login");
            String loginUserNow = loginUser.replaceAll("," + userName + "|" + userName + ",", "");
            se.getSession().getServletContext().setAttribute("userName", loginUserNow);
        }

    }
}
