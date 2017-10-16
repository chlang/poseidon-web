package edu.mum.se.poseidon.web.mapper;

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
        entryDto.setName(entryModel.getName());
        entryDto.setnFppOpt(entryModel.getnFppOpt());
        entryDto.setnFppStudents(entryModel.getnFppStudents());
        entryDto.setnMppOpt(entryModel.getnMppOpt());
        entryDto.setnMppStudents(entryModel.getnMppStudents());
        entryDto.setStartDate(entryModel.getStartDate());
        entryDto.setUsRes(entryModel.getUsRes());
        return entryDto;
    }
}
