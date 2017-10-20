package edu.mum.se.poseidon.web.models;

import java.util.List;

/**
 * Created by Yuriy Yugay on 10/20/2017.
 *
 * @author Yuriy Yugay
 */
public class AdminScheduleModel {

    private String entryName;
    private List<AdminSectionModel> sectionModelList;

    public String getEntryName() {
        return entryName;
    }

    public void setEntryName(String entryName) {
        this.entryName = entryName;
    }

    public List<AdminSectionModel> getSectionModelList() {
        return sectionModelList;
    }

    public void setSectionModelList(List<AdminSectionModel> sectionModelList) {
        this.sectionModelList = sectionModelList;
    }
}
