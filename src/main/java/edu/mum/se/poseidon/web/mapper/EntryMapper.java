package edu.mum.se.poseidon.web.mapper;

import edu.mum.se.poseidon.web.configs.Helper;
import edu.mum.se.poseidon.web.models.Entry;
import edu.mum.se.poseidon.web.models.EntryModel;
import edu.mum.se.poseidon.web.services.dto.EntryDto;
import org.springframework.stereotype.Component;

@Component
public class EntryMapper {

    public EntryModel getEntryModelFrom(EntryDto entryDto) {
        if (entryDto == null) {
            return null;
        }
        EntryModel entryModel = new EntryModel();
        entryModel.setId(entryDto.getId());
        entryModel.setName(entryDto.getName());
        entryModel.setnFppOpt(entryDto.getnFppOpt());
        entryModel.setnFppStudents(entryDto.getnFppStudents());
        entryModel.setnMppOpt(entryDto.getnMppOpt());
        entryModel.setnMppStudents(entryDto.getnMppStudents());
        entryModel.setStartDate(entryDto.getStartDate());
        entryModel.setUsRes(entryDto.getUsRes());
        return entryModel;
    }

    public EntryDto getEntryDtoFrom(EntryModel entryModel) {
        if (entryModel == null) {
            return null;
        }
        EntryDto entryDto = new EntryDto();
        entryDto.setId(entryModel.getId());
        entryDto.setName(entryModel.getName());
        entryDto.setnFppOpt(entryModel.getnFppOpt());
        entryDto.setnFppStudents(entryModel.getnFppStudents());
        entryDto.setnMppOpt(entryModel.getnMppOpt());
        entryDto.setnMppStudents(entryModel.getnMppStudents());
        entryDto.setStartDate(entryModel.getStartDate());
        entryDto.setUsRes(entryModel.getUsRes());
        return entryDto;
    }
    
    public Entry getEntry(EntryDto entryDto) {
    	if (entryDto == null) {
            return null;
        }
        Entry entry = new Entry();
        entry.setId(entryDto.getId());
        entry.setName(entryDto.getName());
        entry.setnFppOpt(entryDto.getnFppOpt());
        entry.setnFppStudents(entryDto.getnFppStudents());
        entry.setnMppOpt(entryDto.getnMppOpt());
        entry.setnMppStudents(entryDto.getnMppStudents());
        entry.setStartDate(entryDto.getStartDate());
        entry.setUsRes(entryDto.getUsRes());
        return entry;
    }
}
