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
import modle.BoardDTO;
import paging.Paging;

@WebServlet("/controller/indexController")
public class IndexController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String num = null;
			
			if (req.getParameter("no") != null) {
				num = req.getParameter("no");
			}
			
			BoardDAO boardDao = new BoardDAO();
			
			int pageCount = boardDao.getListCount();
			Paging paging = new Paging(num, pageCount);
			
			ArrayList<BoardDTO> boardList = boardDao.getSelectBoardList(paging.getStartRow(), paging.getEndRow());
			System.out.println(pageCount);
			System.out.println(paging.getTotalRow());
			
			req.setAttribute("boardList", boardList);
			req.setAttribute("paging", paging);
			
			resp.setContentType("text/html; charset=utf-8");
			RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
			rd.include(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
