import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Message;
import model.User;
import model.dao.MessageDAO;
import model.dao.UserDAO;
import model.utils.DAOFactoryUtils;

@WebServlet("/addMessage")
public class AddMessageServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDAO userDAO = DAOFactoryUtils.getUserDAO();
		MessageDAO messageDAO = DAOFactoryUtils.getMessageDAO();

		HttpSession session = req.getSession(false);
		String username = (String) session.getAttribute("username");
		User user = userDAO.getByName(username);

		String text = req.getParameter("message").trim();
		Message message = new Message(text, user, LocalDateTime.now());
		messageDAO.create(message);
		// List<Message> messages = messageDAO.getAll(); from DB
		List<Message> messages = (List<Message>) getServletContext().getAttribute("messages"); // cache-like
		messages.add(message);
		getServletContext().setAttribute("messages", messages);
		resp.sendRedirect("showMessages.jsp");
	}

}
