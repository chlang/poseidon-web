package edu.mum.se.poseidon.web.models;

import java.util.List;

/**
 * Created by Yuriy Yugay on 10/19/2017.
 *
 * @author Yuriy Yugay
 */
public class FacultyScheduleModel {

    private String entryName;
    private List<FacultySectionModel> sectionModelList;

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public List<FacultySectionModel> getSectionModelList() {
        return sectionModelList;
    }

    public void setSectionModelList(List<FacultySectionModel> sectionModelList) {
        this.sectionModelList = sectionModelList;
    }
}
