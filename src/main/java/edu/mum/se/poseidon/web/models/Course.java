package edu.mum.se.poseidon.web.models;

import java.util.List;

public class Course {

	private String name;
    private Integer number;
    
    private List<Course> prerequisites;
    private List<FacultyModel> faculties;
    private List<SectionModel> sections;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }
    
    public void addPrerequisite(Course prerequisite) {
    	prerequisites.add(prerequisite);
    }

    public List<SectionModel> getSections() {
        return sections;
    }

    public void setSectionList(List<SectionModel> sections) {
        this.sections = sections;
    }
    
    public List<FacultyModel> getFaculties(){
    	return faculties;
    }
    
    public void setFaculties(List<FacultyModel> faculties) {
    	this.faculties = faculties;
    }
    
    public void addFaculty(FacultyModel faculty) {
    	faculties.add(faculty);
    }
}
