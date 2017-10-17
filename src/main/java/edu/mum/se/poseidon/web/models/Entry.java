package edu.mum.se.poseidon.web.models;

import javax.validation.constraints.NotNull;

/* 
 * It's used for create block. Pls dont delete Mogi
 * */
public class Entry {
	
	private Long id;
    @NotNull
    private String startDate;
    @NotNull
    private Integer nFppStudents;
    @NotNull
    private Integer nMppStudents;
    @NotNull
    private Integer nFppOpt;
    @NotNull
    private Integer nMppOpt;
    @NotNull
    private Integer usRes;
    @NotNull
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
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
