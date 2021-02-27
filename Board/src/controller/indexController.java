package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modle.BoardDAO;

@WebServlet("/controller/indexController")
public class indexController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			BoardDAO boardDao = new BoardDAO();
			
			req.setAttribute("boardList", boardDao.getBoardList());
			resp.setContentType("text/html; charset=utf-8");
			RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
			rd.include(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
