package com.shixi.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description: Response工具类
 * @author: hw
 * @date: 2018年3月22日 下午12:05:17
 */
public class ResponseUtil {

	public static void write(HttpServletResponse response, Object o) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		response.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		out.println(o.toString());
		out.flush();
		out.close();
	}
}
