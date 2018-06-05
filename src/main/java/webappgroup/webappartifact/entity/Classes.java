package webappgroup.webappartifact.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="Class", uniqueConstraints={@UniqueConstraint(columnNames={"ClassId"})})
public class Classes {
	private String ClassId;
	private String ClassName;

	@Id
	@Column(name="ClassId")
	public String getClassId() {
		return ClassId;
	}

	public void setClassId(String classId) {
		ClassId = classId;
	}
	@Column(name="ClassName",nullable=false)
	public String getClassName() {
		return ClassName;
	}

	public void setClassName(String className) {
		ClassName = className;
	}

	public Classes(String classId, String className) {
		super();
		ClassId = classId;
		ClassName = className;
	}

	public Classes(String classId) {
		super();
		ClassId = classId;
	}

	public Classes() {
		super();
	}
}
