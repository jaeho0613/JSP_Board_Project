package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modle.UserDAO;
import modle.UserDTO;
import util.SHA256;

/*
 * 회원가입 컨트롤러
 */
@WebServlet("/controller/signUp")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		RequestDispatcher rd = req.getRequestDispatcher("/auth/SignUpForm.jsp");
		rd.include(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		try {
			UserDAO userDAO = new UserDAO();
			
			String password = req.getParameter("userPassword");
			String psswordHash = SHA256.getSHA256(password);

			userDAO.insert(new UserDTO()
					.setUserID(req.getParameter("userID"))
					.setUserName(req.getParameter("userName"))
					.setUserPassword(password)
					.setUserPasswordHash(psswordHash)
					);

			resp.sendRedirect("indexController");
		} catch (Exception e) {
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
		}
	}
}
