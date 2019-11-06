package model.utils;

import model.dao.MessageDAO;
import model.dao.MessageDAOHibernateJPA;
import model.dao.UserDAO;
import model.dao.UserDAOHibernateJPA;

public class DAOFactoryUtils {

	public static UserDAO getUserDAO() {
		return new UserDAOHibernateJPA();
	}

	public static MessageDAO getMessageDAO() {
		return new MessageDAOHibernateJPA();
	}
}
