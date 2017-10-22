package edu.mum.se.poseidon.web.models.registration;

import edu.mum.se.poseidon.web.models.SectionModel;

import java.util.List;

public class RegistrationModel {

    private String name;
    private List<SectionModel> sectionModelList;

    public RegistrationModel(String name, List<SectionModel> sectionModelList) {
        this.name = name;
        this.sectionModelList = sectionModelList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SectionModel> getSectionModelList() {
        return sectionModelList;
    }

    public void setSectionModelList(List<SectionModel> sectionModelList) {
        this.sectionModelList = sectionModelList;
    }
}
