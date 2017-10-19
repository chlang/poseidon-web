package edu.mum.se.poseidon.web.models;

public class StudentSectionModel {

    private Long id;
    private String courseFullName;
    private String blockName;
    private String location;
    private String startDate;
    private String endDate;
    private String facultyFullName;

    public String getFacultyFullName() {
        return facultyFullName;
    }

    public void setFacultyFullName(String facultyFullName) {
        this.facultyFullName = facultyFullName;
    }

    public String getCourseFullName() {
        return courseFullName;
    }

    public void setCourseFullName(String courseFullName) {
        this.courseFullName = courseFullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }
}
