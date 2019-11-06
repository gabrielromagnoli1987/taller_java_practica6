package model.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryUtils {

	private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");

	public static EntityManagerFactory getEntityManagerFactory () {
		return entityManagerFactory;
	}
}
