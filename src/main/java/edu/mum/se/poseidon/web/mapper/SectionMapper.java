package edu.mum.se.poseidon.web.mapper;

import edu.mum.se.poseidon.web.models.AdminSectionModel;
import edu.mum.se.poseidon.web.models.FacultySectionModel;
import edu.mum.se.poseidon.web.models.SectionModel;
import edu.mum.se.poseidon.web.models.StudentSectionModel;
import edu.mum.se.poseidon.web.services.dto.AdminSectionDto;
import edu.mum.se.poseidon.web.services.dto.FacultySectionDto;
import edu.mum.se.poseidon.web.services.dto.SectionDto;
import edu.mum.se.poseidon.web.services.dto.StudentSectionDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;
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

    public List<StudentSectionModel> getStudentSectionModelSTUBList() {
        StudentSectionModel sectionModel1 = new StudentSectionModel();
        sectionModel1.setId(1l);
        sectionModel1.setFacultyFullName("Michael Jordan");
        sectionModel1.setBlockName("September");
        sectionModel1.setStartDate("09/01/2017");
        sectionModel1.setEndDate("09/30/2017");
        sectionModel1.setLocation("Verill Hall 115");
        sectionModel1.setCourseFullName("Basketball 495");

        StudentSectionModel sectionModel2 = new StudentSectionModel();
        sectionModel2.setId(2l);
        sectionModel2.setFacultyFullName("Eric Cantona");
        sectionModel2.setBlockName("October");
        sectionModel2.setStartDate("10/01/2017");
        sectionModel2.setEndDate("10/30/2017");
        sectionModel2.setLocation("Verill Hall 120");
        sectionModel2.setCourseFullName("Football 505");

        StudentSectionModel sectionModel3 = new StudentSectionModel();
        sectionModel3.setId(3l);
        sectionModel3.setFacultyFullName("Tiger Woods");
        sectionModel3.setBlockName("November");
        sectionModel3.setStartDate("11/01/2017");
        sectionModel3.setEndDate("11/30/2017");
        sectionModel3.setLocation("Verill Hall 135");
        sectionModel3.setCourseFullName("Golf 530");

        StudentSectionModel sectionModel4 = new StudentSectionModel();
        sectionModel4.setId(4l);
        sectionModel4.setFacultyFullName("Mike Tyson");
        sectionModel4.setBlockName("December");
        sectionModel4.setStartDate("12/01/2017");
        sectionModel4.setEndDate("12/30/2017");
        sectionModel4.setLocation("Verill Hall 143");
        sectionModel4.setCourseFullName("Boxing 580");

        return Arrays.asList(sectionModel1, sectionModel2, sectionModel3, sectionModel4);
    }

    public List<StudentSectionModel> getStudentSectionModelList(List<StudentSectionDto> studentSectionDtoList) {
        if (studentSectionDtoList == null) {
            return null;
        }

        return studentSectionDtoList.stream()
                .map(dto -> getStudentSectionModel(dto))
                .filter(model -> model != null)
                .collect(Collectors.toList());
    }

    public StudentSectionModel getStudentSectionModel(StudentSectionDto studentSectionDto) {
        if (studentSectionDto == null) {
            return null;
        }

        StudentSectionModel sectionModel = new StudentSectionModel();
        sectionModel.setId(studentSectionDto.getId());
        sectionModel.setFacultyFullName(studentSectionDto.getFacultyFirstName()
                + " " + studentSectionDto.getFacultyLastName());
        sectionModel.setBlockName(studentSectionDto.getBlockName());
        sectionModel.setStartDate(studentSectionDto.getStartDate());
        sectionModel.setEndDate(studentSectionDto.getEndDate());
        sectionModel.setLocation(studentSectionDto.getLocation());
        sectionModel.setCourseFullName(studentSectionDto.getCourseName()
                + " " + studentSectionDto.getCourseNumber());

        return sectionModel;
    }

    public List<FacultySectionModel> getFacultySectionModelList(List<FacultySectionDto> facultySectionDtoList) {
        if (facultySectionDtoList == null) {
            return null;
        }

        return facultySectionDtoList.stream()
                .map(dto -> getFacultySectionModel(dto))
                .filter(model -> model != null)
                .collect(Collectors.toList());
    }

    public FacultySectionModel getFacultySectionModel(FacultySectionDto facultySectionDto) {
        if (facultySectionDto == null) {
            return null;
        }

        FacultySectionModel sectionModel = new FacultySectionModel();
        sectionModel.setBlockName(facultySectionDto.getBlockName());
        sectionModel.setEndDate(facultySectionDto.getEndDate());
        sectionModel.setId(facultySectionDto.getId());
        sectionModel.setLocation(facultySectionDto.getLocation());
        sectionModel.setMaxSeats(facultySectionDto.getMaxSeats());
        sectionModel.setStartDate(facultySectionDto.getStartDate());
        sectionModel.setCourseFullName(facultySectionDto.getCourseName()
                + " " + facultySectionDto.getCourseNumber());

        return sectionModel;
    }

    public List<AdminSectionModel> getAdminSectionModelList(List<AdminSectionDto> adminSectionDtoList) {
        if (adminSectionDtoList == null) {
            return null;
        }

        return adminSectionDtoList.stream()
                .map(dto -> getAdminSectionModel(dto))
                .filter(model -> model != null)
                .collect(Collectors.toList());
    }

    public AdminSectionModel getAdminSectionModel(AdminSectionDto adminSectionDto) {
        if (adminSectionDto == null) {
            return null;
        }

        AdminSectionModel sectionModel = new AdminSectionModel();
        sectionModel.setBlockName(adminSectionDto.getBlockName());
        sectionModel.setEndDate(adminSectionDto.getEndDate());
        sectionModel.setId(adminSectionDto.getId());
        sectionModel.setLocation(adminSectionDto.getLocation());
        sectionModel.setMaxSeats(adminSectionDto.getMaxSeats());
        sectionModel.setStartDate(adminSectionDto.getStartDate());
        sectionModel.setFacultyFullName(adminSectionDto.getFacultyFirstName()
                + " " + adminSectionDto.getFacultyLastName());
        sectionModel.setCourseFullName(adminSectionDto.getCourseName()
                + " " + adminSectionDto.getCourseNumber());

        return sectionModel;
    }

    public List<SectionModel> getSectionModelListFrom(List<SectionDto> sectionDtoList) {
        if (sectionDtoList == null) {
            return null;
        }
        return sectionDtoList.stream().map(sd -> getSectionModelFrom(sd)).collect(Collectors.toList());
    }

    public List<SectionDto> getSectionDtoListFrom(List<SectionModel> sectionModelList) {
        if (sectionModelList == null) {
            return null;
        }
        return sectionModelList.stream().map(sm -> getSectionDtoFrom(sm)).collect(Collectors.toList());
    }
}
