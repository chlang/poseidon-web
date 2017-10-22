package edu.mum.se.poseidon.web.mapper;

import edu.mum.se.poseidon.web.models.registration.RegistrationModel;
import edu.mum.se.poseidon.web.services.dto.SectionDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class RegistrationMapper {

    private SectionMapper sectionMapper = new SectionMapper();

    public List<RegistrationModel> getRegistrationModelFrom(List<SectionDto> sectionDtoList) {
        if (sectionDtoList == null) {
            return null;
        }
        HashMap<String, List<SectionDto>> hash = new HashMap<>();
        List<RegistrationModel> registrationModel = new ArrayList<>();
        for (SectionDto sectionDto : sectionDtoList) {
            if (hash.get(sectionDto.getName()) == null) {
                List<SectionDto> list = new ArrayList<>();
                list.add(sectionDto);
                hash.put(sectionDto.getName(), list);
            } else {
                hash.get(sectionDto.getName()).add(sectionDto);
            }
        }
        for (String key : hash.keySet()) {
        	RegistrationModel rm = new RegistrationModel();
        	rm.setName(key);
        	rm.setSectionModelList(sectionMapper.getSectionModelListFrom(hash.get(key)));
            registrationModel.add(rm);
        }
        return registrationModel;
    }
}
