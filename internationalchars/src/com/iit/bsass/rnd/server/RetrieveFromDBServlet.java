package com.iit.bsass.rnd.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddlab.rnd.util.DatabaseUtil;

/**
 * Servlet implementation class RetrieveFromDBServlet
 */
public class RetrieveFromDBServlet extends HttpServlet {
       
	private static final long serialVersionUID = -4440843694347659958L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveFromDBServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			String result = DatabaseUtil.retrieveFromDB();
			out.print(result);
		} catch (Exception e) {
			e.printStackTrace();
			out.print("Operation unsuccessfull, "+e.getMessage());
		}
	}

}
