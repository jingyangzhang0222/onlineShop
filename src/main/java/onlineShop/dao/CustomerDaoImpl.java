package onlineShop.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import onlineShop.model.Authorities;
import onlineShop.model.Cart;
import onlineShop.model.Customer;
import onlineShop.model.User;

@Repository
public class CustomerDaoImpl implements CustomerDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addCustomer(Customer customer) {
		// 出于security的考虑, 先设置为true
		customer.getUser().setEnabled(true);
		Authorities authorities = new Authorities();
		// 分配权限
		authorities.setAuthorities("ROLE_USER");
		authorities.setEmailId(customer.getUser().getEmailId());
		
		// 分配购物车
		Cart cart = new Cart();
		customer.setCart(cart);
		cart.setCustomer(customer);
		
		try (Session session = sessionFactory.openSession()) {
			session.beginTransaction();
			session.save(authorities);
			session.save(customer);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Customer getCustomerByName(String userName) {
		User user = null;
		try (Session session = sessionFactory.getCurrentSession()) {
			CriteriaBuilder criteriaBuildeder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = criteriaBuildeder.createQuery(User.class);
			//MySQL基于红黑树实现, 由root开始查询
			Root<User> root = criteriaQuery.from(User.class);
			
			criteriaQuery.select(root).where(criteriaBuildeder.equal(root.get("emailId"), userName));
			user = session.createQuery(criteriaQuery).getSingleResult();
		} catch (Exception e) {
			// TODO
			e.printStackTrace();
		}
		
		if (user != null) {
			return user.getCustomer();
		}
		
		return null;
	}
}
