package edu.mum.se.poseidon.web.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import edu.mum.se.poseidon.web.mapper.FacultyMapper;
import edu.mum.se.poseidon.web.models.FacultyModel;
import edu.mum.se.poseidon.web.services.FacultyService;
import edu.mum.se.poseidon.web.services.dto.FacultyDto;
import edu.mum.se.poseidon.web.services.dto.FacultyProfileDto;

@Component
public class FacultyFormatter implements Formatter<FacultyModel> {

	private FacultyService facultyService;
	private FacultyMapper facultyMapper;
	
	@Autowired
	public FacultyFormatter(FacultyService facultyService, FacultyMapper facultyMapper) {
		this.facultyService = facultyService;
		this.facultyMapper = facultyMapper;
	}

	@Override
	public String print(FacultyModel arg0, Locale arg1) {
		// TODO Auto-generated method stub
		return String.valueOf(arg0.getId());
	}

	@Override
	public FacultyModel parse(String arg0, Locale arg1) throws ParseException {
		// TODO Auto-generated method stub
		long faculty_id = Long.valueOf(arg0);
		try {
			FacultyDto dto = facultyService.getAFaculty(faculty_id);
			FacultyModel fm = facultyMapper.getFaculty(dto);
			return fm;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacultyModel fm = new FacultyModel();
			fm.setId(faculty_id);
			return fm;
		}
	}
}
