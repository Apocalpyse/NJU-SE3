package com.runoob.test;

import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by thinkpad on 2017/5/27.
 */
@WebServlet(name = "MyOwnServlet")
public class MyOwnServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String jsonStr = "[ { 'date': '17/5/24', 'open': '10', 'high': '11', 'low': '9.8', 'close': '10.2', 'code': '60001', 'name': 'kl', 'market': 'sa', 'increaseOrDecrease': '1%', 'preAdjClose': '1.2', 'plate': '1'},{'date': '17/5/25', 'open': '10', 'high': '11', 'low': '9.8', 'close': '10.2', 'code': '60001', 'name': 'kl', 'market': 'sa', 'increaseOrDecrease': '1%', 'preAdjClose': '1.2', 'plate': '1'},{'date': '17/5/26', 'open': '10', 'high': '11', 'low': '9.8', 'close': '10.2', 'code': '60001', 'name': 'kl', 'market': 'sa', 'increaseOrDecrease': '1%', 'preAdjClose': '1.2', 'plate': '1'},{'date': '17/5/27', 'open': '10', 'high': '11', 'low': '9.8', 'close': '10.2', 'code': '60001', 'name': 'kl', 'market': 'sa', 'increaseOrDecrease': '1%', 'preAdjClose': '1.2', 'plate': '1'}]";
        JSONArray jsonArray= JSONArray.fromObject(jsonStr);
        System.out.println("here");
        PrintWriter out = null;
        try {
            // 输出数据
            out = response.getWriter();
            out.println(jsonArray);

        }catch (Exception e) {
            out.print("get data error!");
            e.printStackTrace();
        }


}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
