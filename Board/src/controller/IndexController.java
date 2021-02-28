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
import paging.Paging;

@WebServlet("/controller/indexController")
public class IndexController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			BoardDAO boardDao = new BoardDAO();
			String num = null;
			
			if (req.getParameter("no") != null) {
				num = req.getParameter("no");
			}
			Paging paging = new Paging(num);
			
			req.setAttribute("boardList", boardDao.getSelectBoardList(paging.getStartRow(), paging.getEndRow()));
			req.setAttribute("paging", paging);
			resp.setContentType("text/html; charset=utf-8");
			RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
			rd.include(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
