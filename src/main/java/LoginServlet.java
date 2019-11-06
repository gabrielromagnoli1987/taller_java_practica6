import java.io.IOException;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.dao.UserDAO;
import model.utils.DAOFactoryUtils;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		UserDAO userDAO = DAOFactoryUtils.getUserDAO();
		try {
			User user = userDAO.getByName(name);
			if (user.getPassword().equals(password)){
				HttpSession session = req.getSession();
				session.setAttribute("username", user.getName());
				resp.sendRedirect("showMessages.jsp");
			} else {
				resp.sendRedirect("error.jsp");
			}
		} catch (NoResultException e) {
			resp.sendRedirect("error.jsp");
		}
	}

}
