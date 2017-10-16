package edu.mum.se.poseidon.web.services.dto;

import java.time.LocalDate;

import edu.mum.se.poseidon.web.models.Entry;

public class BlockDto {
	private long id;
	private String name;
    private LocalDate startDate;
    private LocalDate endDate;
	private Entry entry;
	
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
	public Entry getEntry() {
		return entry;
	}
	public void setEntry(Entry entry) {
		this.entry = entry;
	}
}