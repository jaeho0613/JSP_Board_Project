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

/*
 * List를 보여주는 Main Controller
 */
@WebServlet("/controller/indexController")
public class IndexController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String num = null;
			
			// 페이지 번호(no)값을 저장
			if (req.getParameter("no") != null) {
				num = req.getParameter("no");
			}
			
			BoardDAO boardDao = new BoardDAO();

			// 총 데이터 베이스 갯수 가져옴
			int pageCount = boardDao.getListCount();
			
			// 페이징 처리를 하는 클래스 생성
			Paging paging = new Paging(num, pageCount);
			
			// 보여줄 리스트를 가져온다.
			ArrayList<BoardDTO> boardList = boardDao.getSelectBoardList(paging.getStartRow(), paging.getEndRow());

			// 리스트와 페이징을 저장소에 넣는다.
			req.setAttribute("boardList", boardList);
			req.setAttribute("paging", paging);
			
			resp.setContentType("text/html; charset=utf-8");
			
			// 사용자에게 보여지는 영역은 jsp에 인계
			RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
			rd.include(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
