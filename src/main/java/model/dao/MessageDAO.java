package model.dao;

import java.util.List;

import model.Message;

public interface MessageDAO extends GenericDAO<Message> {

	List<Message> getAll();

}
