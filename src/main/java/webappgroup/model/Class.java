package webappgroup.model;

public class Class {
	private String ClassId;
	private String ClassName;

	public String getClassId() {
		return ClassId;
	}

	public void setClassId(String classId) {
		ClassId = classId;
	}

	public String getClassName() {
		return ClassName;
	}

	public void setClassName(String className) {
		ClassName = className;
	}

	public Class(String classId, String className) {
		super();
		ClassId = classId;
		ClassName = className;
	}

	public Class(String classId) {
		super();
		ClassId = classId;
	}

	public Class() {
		super();
	}
}
