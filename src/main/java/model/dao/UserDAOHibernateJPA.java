package model.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.User;
import model.utils.EntityManagerFactoryUtils;

public class UserDAOHibernateJPA extends GenericDAOHibernateJPA<User> implements UserDAO {

	public UserDAOHibernateJPA() {
		super(User.class);
	}

	@Override
	public User getByName(String name) {
		EntityManager entityManager = EntityManagerFactoryUtils.getEntityManagerFactory().createEntityManager();
		Query query = entityManager.createQuery("select u from User u where u.name = ?");
		query.setParameter(0, name);
		User user = (User) query.getSingleResult();
		entityManager.close();
		return user;
	}
}
