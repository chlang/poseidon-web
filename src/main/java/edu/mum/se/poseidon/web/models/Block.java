package edu.mum.se.poseidon.web.models;

import java.time.LocalDate;

public class Block {

	private long id;
	private String name;
    private LocalDate startDate;
    private LocalDate endDate;
	private EntryModel entryModel;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public EntryModel getEntryModel() {
		return entryModel;
	}
	public void setEntryModel(EntryModel entryModel) {
		this.entryModel = entryModel;
	}
}
