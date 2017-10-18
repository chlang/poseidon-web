package edu.mum.se.poseidon.web.models.schedule;

import edu.mum.se.poseidon.web.models.EntryModel;
import edu.mum.se.poseidon.web.services.dto.EntryDto;

import java.util.List;

public class ScheduleCreateModel {
    private long entryId;
    private List<EntryModel> entries;

    public long getEntryId() {
        return entryId;
    }

    public void setEntryId(long entryId) {
        this.entryId = entryId;
    }

    public List<EntryModel> getEntries() {
        return entries;
    }

    public void setEntries(List<EntryModel> entries) {
        this.entries = entries;
    }
}
