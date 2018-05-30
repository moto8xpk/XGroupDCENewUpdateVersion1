package webappgroup.webappartifact.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import webappgroup.webappartifact.entity.Question;

@Component
public class QuestionDAO {
	//
	// private SessionFactory sessionFactory;
	//
	// public void setSessionFactory(SessionFactory sessionFactory) {
	// this.sessionFactory = sessionFactory;
	// }
	@PersistenceContext
	private EntityManager em;

	// @PersistenceContext
	// private EntityManager em;
	//
	@SuppressWarnings("unchecked")
	public List<Question> getQuestionList() {
		// List<Question> questionlist=null;
		// Session session = this.sessionFactory.getCurrentSession();
		// String sql = "from Question ";
		// Query query = session.createQuery(sql);
		// questionlist = (List<Question>) query.list();
		// if (questionlist == null) {
		// return null;
		// }
		// return questionlist;
		// TypedQuery<Question> query = (TypedQuery<Question>) em.createQuery(
		// "SELECT q FROM Question q ", Question.class).getResultList();
		Query query2 = em.createQuery("SELECT q FROM Question q");
		List<Question> list = query2.getResultList();
		return list;
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