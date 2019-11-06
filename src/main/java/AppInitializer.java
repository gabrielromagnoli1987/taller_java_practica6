import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import model.Message;
import model.User;
import model.dao.UserDAO;
import model.utils.DAOFactoryUtils;

@WebListener
public class AppInitializer implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		User user = new User("user", "pass");
		User user2 = new User("user2", "pass2");

		UserDAO userDAO = DAOFactoryUtils.getUserDAO();
		userDAO.create(user);
		userDAO.create(user2);
		
		List<Message> messages = new ArrayList<>();
		sce.getServletContext().setAttribute("messages", messages);
	}
}
