package model.dao;

import model.User;

public interface UserDAO extends GenericDAO<User> {

	User getByName(String name);
}
