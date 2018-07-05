package webappgroup.webappartifact.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import webappgroup.webappartifact.entity.Question;
import webappgroup.webappartifact.model.Answer;
import webappgroup.webappartifact.model.Answer2;

@Component
@Transactional
public class QuestionDAO {

	@PersistenceContext
	private EntityManager em;
	

	@SuppressWarnings("unchecked")
	public List<Question> getQuestionList() {
		Query query2 = em.createQuery("SELECT q FROM Question q");
		List<Question> list = query2.getResultList();
		return list;
	}
	public String getQuestionCount() {
		Query query2 = em.createQuery("SELECT count(questionId) "
									+ " FROM Question ");
		String number = query2.getSingleResult().toString();
		return number;
	}

	public String getQuestionAnswer(String quesId) {
		Integer questionId=Integer.parseInt(quesId);
		Query query2 = em.createQuery("SELECT answer "
									+ " FROM Question "
									+" WHERE questionId= :questionId ").setParameter("questionId", questionId);
		String list = (String) query2.getSingleResult().toString();
		return list;
	}
	
	public void delete(Integer questionId) {
		Question question = em.getReference(Question.class, questionId);
		em.remove(question);
	}

	public void save(List<Question> questionList) {
		EntityManager entityManager=em.getEntityManagerFactory().createEntityManager();
		Session session=entityManager.unwrap(Session.class);
		for (Question question : questionList) {
			session.save(question);
		}
	}
}