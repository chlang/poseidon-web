package edu.mum.se.poseidon.web.controllers;

import edu.mum.se.poseidon.web.mapper.SectionMapper;
import edu.mum.se.poseidon.web.models.AdminScheduleModel;
import edu.mum.se.poseidon.web.models.AdminSectionModel;
import edu.mum.se.poseidon.web.services.AdminScheduleService;
import edu.mum.se.poseidon.web.services.dto.AdminScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuriy Yugay on 10/20/2017.
 *
 * @author Yuriy Yugay
 */
@Controller
public class AdminScheduleController {

    private AdminScheduleService adminScheduleService;
    private SectionMapper sectionMapper;

    @Autowired
    public AdminScheduleController(AdminScheduleService adminScheduleService,
                                     SectionMapper sectionMapper) {
        this.adminScheduleService = adminScheduleService;
        this.sectionMapper = sectionMapper;
    }

    @RequestMapping(path = "/admin/scheduleview", method = RequestMethod.GET)
    public String getSchedule(Model model) throws Exception {
        try {
            List<AdminScheduleDto> adminScheduleDtoList
                    = adminScheduleService.getAllSchedule();

            List<AdminScheduleModel> models = new ArrayList<>();
            for(AdminScheduleDto dto: adminScheduleDtoList) {
                AdminScheduleModel mod = new AdminScheduleModel();
                List<AdminSectionModel> sectionModelList = sectionMapper.getAdminSectionModelList(dto.getAdminSectionDtoList());
                mod.setEntryName(dto.getEntryName());
                mod.setSectionModelList(sectionModelList);

                models.add(mod);
            }
            // STUB!
//            List<AdminScheduleModel> models = createStubScheduleModel();

            model.addAttribute("entries", models);
            return "faculty/schedule";
        } catch(Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    private List<AdminScheduleModel> createStubScheduleModel() {
        List<AdminScheduleModel> list = new ArrayList<>();

        List<AdminSectionModel> modelList = new ArrayList<>();
        AdminSectionModel sectionModel1 = new AdminSectionModel();
        sectionModel1.setId(1l);
        sectionModel1.setBlockName("September");
        sectionModel1.setStartDate("09/01/2017");
        sectionModel1.setEndDate("09/30/2017");
        sectionModel1.setLocation("Verill Hall 115");
        sectionModel1.setCourseFullName("Basketball 495");
        sectionModel1.setFacultyFullName("Michael Jordan");
        sectionModel1.setMaxSeats(25);

        AdminSectionModel sectionModel2 = new AdminSectionModel();
        sectionModel2.setId(2l);
        sectionModel2.setBlockName("October");
        sectionModel2.setStartDate("10/01/2017");
        sectionModel2.setEndDate("10/30/2017");
        sectionModel2.setLocation("Verill Hall 120");
        sectionModel2.setCourseFullName("Football 505");
        sectionModel2.setFacultyFullName("Michael Jordan");
        sectionModel2.setMaxSeats(25);

        AdminSectionModel sectionModel3 = new AdminSectionModel();
        sectionModel3.setId(3l);
        sectionModel3.setBlockName("November");
        sectionModel3.setStartDate("11/01/2017");
        sectionModel3.setEndDate("11/30/2017");
        sectionModel3.setLocation("Verill Hall 135");
        sectionModel3.setCourseFullName("Golf 530");
        sectionModel3.setFacultyFullName("Michael Jordan");
        sectionModel3.setMaxSeats(25);

        modelList.add(sectionModel1);
        modelList.add(sectionModel2);
        modelList.add(sectionModel3);

        AdminScheduleModel model1 = new AdminScheduleModel();
        model1.setEntryName("August 2017");
        model1.setSectionModelList(modelList);

        AdminScheduleModel model2 = new AdminScheduleModel();
        model2.setEntryName("October 2017");
        model2.setSectionModelList(modelList);

        AdminScheduleModel model3 = new AdminScheduleModel();
        model3.setEntryName("January 2018");
        model3.setSectionModelList(modelList);

        list.add(model1);
        list.add(model2);
        list.add(model3);

        return list;
    }
}
