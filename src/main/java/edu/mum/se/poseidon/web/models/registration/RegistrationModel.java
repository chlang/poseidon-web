package edu.mum.se.poseidon.web.models.registration;

import edu.mum.se.poseidon.web.models.SectionModel;

import java.util.ArrayList;
import java.util.List;

public class RegistrationModel {

    private String name;
    private List<SectionModel> sectionModelList = new ArrayList<>();

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
