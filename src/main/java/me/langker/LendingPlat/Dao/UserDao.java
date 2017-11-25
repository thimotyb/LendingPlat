package me.langker.LendingPlat.Dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;
import me.langker.LendingPlat.Entity.User;

@Stateless
public class UserDao {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<User> findByEmailAndPassword(String email, String password) {
		String sql = "Select * from User where email=? and password=?";
		Query query = em.createNativeQuery(sql, User.class).setParameter(1, email).setParameter(2, password);
		return (List<User>)query.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<User> findByEmail(String email) {
		String sql = "Select * from User where email=?";
		Query query = em.createNativeQuery(sql, User.class).setParameter(1,email);
		return (List<User>)query.getResultList();
	}
	public User createUser(String email, String password, String address, String credential) {
		User user = new User();
		user.setAddress(address);
		user.setCredential(credential);
		user.setEmail(email);
		user.setIsAdmin(false);
		user.setPassword(password);
		em.persist(user);
		return user;
	}
	public User findUserProfile(String email) {
		String sql = "Select * from User where email=?";
		Query query = em.createNativeQuery(sql, User.class).setParameter(1,email);
		return (User)query.getSingleResult();
	}
}
