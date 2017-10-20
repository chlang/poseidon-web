package edu.mum.se.poseidon.web.models;

/**
 * Created by Yuriy Yugay on 10/20/2017.
 *
 * @author Yuriy Yugay
 */
public class AdminSectionModel {
    private Long id;
    private Integer maxSeats;
    private String location;
    private String startDate;
    private String endDate;
    private String blockName;
    private String facultyFullName;
    private String courseFullName;

    public String getFacultyFullName() {
        return facultyFullName;
    }

    public void setFacultyFullName(String facultyFullName) {
        this.facultyFullName = facultyFullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(Integer maxSeats) {
        this.maxSeats = maxSeats;
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

    public String getCourseFullName() {
        return courseFullName;
    }

    public void setCourseFullName(String courseFullName) {
        this.courseFullName = courseFullName;
    }
}
