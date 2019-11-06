package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Message;
import model.utils.EntityManagerFactoryUtils;

public class MessageDAOHibernateJPA extends GenericDAOHibernateJPA<Message> implements MessageDAO {

	public MessageDAOHibernateJPA() {
		super(Message.class);
	}

	@Override
	public List<Message> getAll() {
		EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
		Query query = entityManager.createQuery("select m from Message m");
		List<Message> messages = (List<Message>) query.getResultList();
		entityManager.close();
		return messages;
	}
}
