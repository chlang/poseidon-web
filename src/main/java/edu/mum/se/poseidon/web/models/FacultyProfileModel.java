package edu.mum.se.poseidon.web.models;

import edu.mum.se.poseidon.web.services.dto.CourseDto;

import java.util.List;

/**
 * Created by Yuriy Yugay on 10/18/2017.
 *
 * @author Yuriy Yugay
 */
public class FacultyProfileModel {

    private long id;
    private String firstName;
    private String lastName;
    private String type;
    private String username;
    private String password;
    private List<CourseInfo> courseList;

    public List<CourseInfo> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseInfo> courseList) {
        this.courseList = courseList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
