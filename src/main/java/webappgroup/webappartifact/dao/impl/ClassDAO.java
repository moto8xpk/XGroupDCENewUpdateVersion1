package webappgroup.webappartifact.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import webappgroup.webappartifact.entity.Classes;

@Component
@Transactional
public class ClassDAO {

	// private SessionFactory sessionFactory;
	//
	// public void setSessionFactory(SessionFactory sessionFactory) {
	// this.sessionFactory = sessionFactory;
	// }

	@PersistenceContext
	private EntityManager em;

	public List<Classes> getClassList() {
		// List<Class> classlist = null;
		// Session session = this.sessionFactory.getCurrentSession();
		// String sql = "from Class ";
		// Query query = session.createQuery(sql);
		// classlist = (List<Class>) query.list();
		// if (classlist == null) {
		return null;
		// }
		// return classlist;
	}
	// public void createDepartment(String name, String location) {
	// Integer deptId = getMaxDeptId() + 1;
	// Department dept = new Department();
	// dept.setDeptId(deptId);
	// dept.setDeptNo("D" + deptId);
	// dept.setDeptName(name);
	// dept.setLocation(location);
	// Session session = this.sessionFactory.getCurrentSession();
	// session.persist(dept);
	// }
}
