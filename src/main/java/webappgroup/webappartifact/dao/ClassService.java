package webappgroup.webappartifact.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webappgroup.webappartifact.dao.impl.ClassDAO;

import webappgroup.webappartifact.entity.Class;

@Service
public class ClassService {

	@Autowired
	private ClassDAO classDAOImpl;

	public List<Class> getClasses() {
		return classDAOImpl.getClassList();
	}


}
