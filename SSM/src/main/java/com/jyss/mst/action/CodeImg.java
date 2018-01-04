package com.jyss.mst.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jyss.mst.utils.ValidateCode;

public class CodeImg extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// codeImg
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		ValidateCode vCode = new ValidateCode(100, 30, 4, 100);
		HttpSession session = request.getSession();
		session.removeAttribute("codeImg");
		vCode.write(response.getOutputStream());
		session.setAttribute("codeImg", vCode.getCode());
		vCode.write(response.getOutputStream());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
