package edu.mum.se.poseidon.web.mapper;

import edu.mum.se.poseidon.web.models.FacultySectionModel;
import edu.mum.se.poseidon.web.models.SectionModel;
import edu.mum.se.poseidon.web.models.StudentSectionModel;
import edu.mum.se.poseidon.web.services.dto.FacultySectionDto;
import edu.mum.se.poseidon.web.services.dto.SectionDto;
import edu.mum.se.poseidon.web.services.dto.StudentSectionDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<StudentSectionModel> getStudentSectionModelList(List<StudentSectionDto> studentSectionDtoList) {
        if(studentSectionDtoList == null) {
            return null;
        }

        return studentSectionDtoList.stream()
                .map(dto -> getStudentSectionModel(dto))
                .filter(model -> model != null)
                .collect(Collectors.toList());
    }

    public StudentSectionModel getStudentSectionModel(StudentSectionDto studentSectionDto) {
        if(studentSectionDto == null) {
            return null;
        }

        StudentSectionModel sectionModel = new StudentSectionModel();
        sectionModel.setBlockName(studentSectionDto.getBlockName());
        sectionModel.setEndDate(studentSectionDto.getEndDate());
        sectionModel.setFacultyFirstName(studentSectionDto.getFacultyFirstName());
        sectionModel.setFacultyLastName(studentSectionDto.getFacultyLastName());
        sectionModel.setId(studentSectionDto.getId());
        sectionModel.setLocation(studentSectionDto.getLocation());
        sectionModel.setMaxSeats(studentSectionDto.getMaxSeats());
        sectionModel.setStartDate(studentSectionDto.getStartDate());
        sectionModel.setCourseName(studentSectionDto.getCourseName());
        sectionModel.setCourseNumber(studentSectionDto.getCourseNumber());

        return sectionModel;
    }

    public List<FacultySectionModel> getFacultySectionModelList(List<FacultySectionDto> facultySectionDtoList) {
        if(facultySectionDtoList == null) {
            return null;
        }

        return facultySectionDtoList.stream()
                .map(dto -> getFacultySectionModel(dto))
                .filter(model -> model != null)
                .collect(Collectors.toList());
    }

    public FacultySectionModel getFacultySectionModel(FacultySectionDto facultySectionDto) {
        if(facultySectionDto == null) {
            return null;
        }

        FacultySectionModel sectionModel = new FacultySectionModel();
        sectionModel.setBlockName(facultySectionDto.getBlockName());
        sectionModel.setEndDate(facultySectionDto.getEndDate());
        sectionModel.setId(facultySectionDto.getId());
        sectionModel.setLocation(facultySectionDto.getLocation());
        sectionModel.setMaxSeats(facultySectionDto.getMaxSeats());
        sectionModel.setStartDate(facultySectionDto.getStartDate());
        sectionModel.setCourseName(facultySectionDto.getCourseName());
        sectionModel.setCourseNumber(facultySectionDto.getCourseNumber());

        return sectionModel;
    }
}
