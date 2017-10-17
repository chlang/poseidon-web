package edu.mum.se.poseidon.web.models;

import java.util.List;

public class FacultyModel {

	private String academicDegree;

    private List<SectionModel> sections;
    private List<Course> courses;
    
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public void addCourse(Course course) {
		courses.add(course);
	}
	
	public List<SectionModel> getSections() {
		return sections;
	}

	public void setSections(List<SectionModel> sections) {
		this.sections = sections;
	}

    public void addSection(SectionModel section) {
    	sections.add(section);
    }
    
    public String getAcademicDegree() {
		return academicDegree;
	}

	public void setAcademicDegree(String academicDegree) {
		this.academicDegree = academicDegree;
	}
}
