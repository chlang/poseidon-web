package edu.mum.se.poseidon.web.mapper;

import edu.mum.se.poseidon.web.models.SectionModel;
import edu.mum.se.poseidon.web.services.dto.SectionDto;
import org.springframework.stereotype.Component;

@Component
public class SectionMapper {

    public SectionDto getSectionDtoFrom(SectionModel sectionModel) {
        if (sectionModel == null) {
            return null;
        }
        SectionDto sectionDto = new SectionDto();
        sectionDto.setBlockName(sectionModel.getBlockName());
        sectionDto.setEndDate(sectionModel.getEndDate());
        sectionDto.setStartDate(sectionModel.getStartDate());
        sectionDto.setFacultyFirstName(sectionModel.getFacultyFirstName());
        sectionDto.setFacultyLastName(sectionModel.getFacultyLastName());
        sectionDto.setId(sectionModel.getId());
        sectionDto.setLocation(sectionModel.getLocation());
        sectionDto.setMaxSeats(sectionModel.getMaxSeats());
        return sectionDto;
    }

    public SectionModel getSectionModelFrom(SectionDto sectionDto) {
        if (sectionDto == null) {
            return null;
        }
        SectionModel sectionModel = new SectionModel();
        sectionModel.setBlockName(sectionDto.getBlockName());
        sectionModel.setEndDate(sectionDto.getEndDate());
        sectionModel.setFacultyFirstName(sectionDto.getFacultyFirstName());
        sectionModel.setFacultyLastName(sectionDto.getFacultyLastName());
        sectionModel.setId(sectionDto.getId());
        sectionModel.setLocation(sectionDto.getLocation());
        sectionModel.setMaxSeats(sectionDto.getMaxSeats());
        sectionModel.setStartDate(sectionDto.getStartDate());
        return sectionModel;
    }
}
