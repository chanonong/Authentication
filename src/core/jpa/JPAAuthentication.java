package core.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class JPAAuthentication {
	private static final String PERSISTENCE_UNIT = "JDBCAuthentication";
	private static EntityManagerFactory emf;
	private static EntityManager em;
	static {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		em = emf.createEntityManager();
	}
	
	public static User Authenticate(String username,String password) {
		String q = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password";
		Query query = em.createQuery(q);
		query.setParameter("username", username);
		query.setParameter("password", password);
		try {
			return (User) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
