package com.framelib.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Title:AjaxUtils Description:数据输出工具类
 * 
 * @Create_by:Zhangy Yan
 * @Create_date:2013-12-2
 * @Last_Edit_By:
 * @Edit_Description
 * @version:ShareWithUs 1.0
 *
 */
public class AjaxUtils {

	public static void write(HttpServletResponse response, String message) throws IOException {
		// response.setContentType("application/json; charset=utf-8");
		response.setContentType("text/html; charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out = response.getWriter();
		out.println(message);
		out.flush();
		out.close();
	}

	public static void write(HttpServletResponse response, int message) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(message);
		out.flush();
		out.close();
	}
}
