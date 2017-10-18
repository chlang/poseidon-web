package edu.mum.se.poseidon.web.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.mum.se.poseidon.web.models.EntryModel;
import edu.mum.se.poseidon.web.models.User;
import edu.mum.se.poseidon.web.models.schedule.ScheduleCreateModel;
import edu.mum.se.poseidon.web.models.schedule.ScheduleEditModel;
import edu.mum.se.poseidon.web.models.schedule.ScheduleModel;
import edu.mum.se.poseidon.web.models.schedule.ScheduleStatus;
import edu.mum.se.poseidon.web.services.dto.UserDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class ScheduleController {

    @RequestMapping(path = "/admin/schedule", method = RequestMethod.GET)
    public String index(Model model) throws Exception {
        List<ScheduleModel> sched = mockSchedules();
        ScheduleCreateModel scheduleCreateModel = new ScheduleCreateModel();
        scheduleCreateModel.setEntries(mockEntry());

        model.addAttribute("schedules", sched);
        model.addAttribute("scheduleCreateModel", scheduleCreateModel);
        return "admin/schedule/index";
    }

    @RequestMapping(path = "/admin/schedule/generate", method = RequestMethod.POST)
    public String generate(@ModelAttribute("scheduleCreateModel") @Valid ScheduleCreateModel schedule,
                           BindingResult bindingResult, @Valid Model model) throws Exception {
        return "redirect:/admin/schedule";
    }

    @RequestMapping(path = "/admin/schedule/{id}/edit")
    public String edit(@PathVariable long id, Model model) throws Exception {
        ScheduleEditModel schedule = new ScheduleEditModel();
        schedule.setId(id);
        model.addAttribute("scheduleEditModel", schedule);
        return "admin/schedule/edit";
    }

    @RequestMapping(path = "/admin/schedule/{id}/delete")
    public String delete(@PathVariable long id, Model model) throws Exception {

        return "redirect:/admin/schedule";
    }

    private List<ScheduleModel> mockSchedules() {
        List<ScheduleModel> s = new ArrayList<>();
        s.add(mockSchedule("August", 2));
        s.add(mockSchedule("Jan", 4));
        return s;
    }

    private ScheduleModel mockSchedule(String name, long id) {
        ScheduleModel scheduleModel = new ScheduleModel();
        scheduleModel.setDisplayName("Schedule" + name);
        scheduleModel.setId(id);
        scheduleModel.setGeneratedDate(LocalDate.now().toString());
        scheduleModel.setStatus(ScheduleStatus.ACTIVE);
        return scheduleModel;
    }

    private List<EntryModel> mockEntry(){
        EntryModel ent = new EntryModel();
        EntryModel ent1 = new EntryModel();

        ent.setId(1L);
        ent1.setId(2L);
        ent.setName("August");
        ent1.setName("April");
        return Arrays.asList(ent, ent1);
    }
}
