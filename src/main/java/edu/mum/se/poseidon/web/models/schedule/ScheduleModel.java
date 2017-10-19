package edu.mum.se.poseidon.web.models.schedule;

import edu.mum.se.poseidon.web.models.SectionModel;
import edu.mum.se.poseidon.web.services.dto.SectionDto;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.List;

public class ScheduleModel {
    private Long id;
    private String displayName;
    private ScheduleStatus status;
    private LocalDate generatedDate;
    private List<SectionDto> sections;

    private DisplayItem displayItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(LocalDate generatedDate) {
        this.generatedDate = generatedDate;
    }

    public DisplayItem getDisplayItem() {
        return displayItem;
    }

    public void setDisplayItem(DisplayItem displayItem) {
        this.displayItem = displayItem;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public ScheduleStatus getStatus() {
        return status;
    }

    public void setStatus(ScheduleStatus status) {
        this.status = status;
    }

    public void setSections(List<SectionDto> sections) {
        this.sections = sections;
    }

    public List<SectionDto> getSections() {

        return sections;
    }

    public class DisplayItem {
        private String startDate;
        private String endDate;
        private String courseName;
        private String facultyName;
        private String courseNumber;
        private String blockName;

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

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getFacultyName() {
            return facultyName;
        }

        public void setFacultyName(String facultyName) {
            this.facultyName = facultyName;
        }

        public String getBlockName() {
            return blockName;
        }

        public void setBlockName(String blockName) {
            this.blockName = blockName;
        }

        public String getCourseNumber() {

            return courseNumber;
        }

        public void setCourseNumber(String courseNumber) {
            this.courseNumber = courseNumber;
        }
    }
}
