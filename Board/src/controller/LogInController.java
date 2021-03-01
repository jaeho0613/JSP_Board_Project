package controller;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modle.UserDAO;
import modle.UserDTO;
import util.SHA256;

/*
 * 로그인 컨트롤러
 */
@WebServlet("/controller/login")
public class LogInController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/auth/LogInForm.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			// 파라미터로 넘어온 userPassword 저장
			String password = req.getParameter("userPassword");
			
			// SHA256로 암호화
			password = SHA256.getSHA256(password);
			
			UserDAO userDao = new UserDAO();
			
			// userID와 암호화 된 password로 로그인 정보 확인 
			UserDTO userDTO = userDao.exist(req.getParameter("userID"), password);

			// 넘어온 데이터가 있다면 로그인 성공
			if (userDTO != null) {
				HttpSession session = req.getSession();
				
				// 저장소에 저장 
				session.setAttribute("userDTO", userDTO);

				resp.sendRedirect("../controller/indexController");
			} else { // 넘어온 데이터가 없다면 로그인 실패
				RequestDispatcher rd = req.getRequestDispatcher("/auth/LogInFail.jsp");
				rd.forward(req, resp);
			}
		} catch (Exception e) {
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		}
	}
}
