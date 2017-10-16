package edu.mum.se.poseidon.web.models;

import java.time.LocalDate;

public class EntryModel {

    private LocalDate startDate;
    private Integer nFppStudents;
    private Integer nMppStudents;
    private Integer nFppOpt;
    private Integer nMppOpt;
    private Integer usRes;
    private String name;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Integer getnFppStudents() {
        return nFppStudents;
    }

    public void setnFppStudents(Integer nFppStudents) {
        this.nFppStudents = nFppStudents;
    }

    public Integer getnMppStudents() {
        return nMppStudents;
    }

    public void setnMppStudents(Integer nMppStudents) {
        this.nMppStudents = nMppStudents;
    }

    public Integer getnFppOpt() {
        return nFppOpt;
    }

    public void setnFppOpt(Integer nFppOpt) {
        this.nFppOpt = nFppOpt;
    }

    public Integer getnMppOpt() {
        return nMppOpt;
    }

    public void setnMppOpt(Integer nMppOpt) {
        this.nMppOpt = nMppOpt;
    }

    public Integer getUsRes() {
        return usRes;
    }

    public void setUsRes(Integer usRes) {
        this.usRes = usRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
