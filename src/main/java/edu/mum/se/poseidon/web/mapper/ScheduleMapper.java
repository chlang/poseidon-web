package edu.mum.se.poseidon.web.mapper;

import edu.mum.se.poseidon.web.models.schedule.ScheduleEditModel;
import edu.mum.se.poseidon.web.models.schedule.ScheduleModel;
import edu.mum.se.poseidon.web.models.schedule.ScheduleStatus;
import edu.mum.se.poseidon.web.services.dto.ScheduleDto;
import org.springframework.stereotype.Component;
import sun.util.resources.LocaleData;

import java.time.LocalDate;

@Component
public class ScheduleMapper {

    // TODO: Add more Information To DTO for Display
    public ScheduleModel getSchedule(ScheduleDto dto) {
        ScheduleModel model = new ScheduleModel();

        model.setDisplayName(dto.getName());
        model.setGeneratedDate(LocalDate.now());
        model.setId(dto.getId());
        model.setSections(dto.getSections());

        ScheduleStatus status = dto.getStatus() == 1 ? ScheduleStatus.ACTIVE : ScheduleStatus.DRAFT;
        model.setStatus(status);

        return  model;
    }

    // TODO: revisit Schedule Model and DTO
    public  ScheduleDto getScheduleDto(ScheduleModel model){
        ScheduleDto dto = new ScheduleDto();

        dto.setId(model.getId());
        dto.setName(model.getDisplayName());
        dto.setStatus(model.getStatus().getValue());
        dto.setSections(model.getSections());

        return  dto;
    }

    public ScheduleDto getScheduleDto(ScheduleEditModel model){
        ScheduleDto dto = new ScheduleDto();
        dto.setId(model.getId());
        dto.setStatus(model.getStatus().getValue());

        return dto;
    }
}
