package pl.beata.zarzadzanie_2;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("newTask", new Duty());
        model.addAttribute("categories", Category.values());
        return "index";
    }

    @GetMapping("/allFinishedDuties")
    public String allFinishedDuties(Model model) {
        model.addAttribute("tasks", taskService.fetchAllFinishedDuties());
        return "allFinishedDuties";
    }

    @GetMapping("/allUnfinishedDuties")
    public String allUnfinishedDuties(Model model) {
        model.addAttribute("tasks", taskService.fetchAllUnfinishedDuties());
        return "allUnfinishedDuties";
    }

    @PostMapping("/add")
    @ResponseBody
    public String addTransaction(@RequestParam(required = true) String title, @RequestParam(required = true) String description, @RequestParam(required = true) String date, @RequestParam(required = true) int status, @RequestParam(required = true) Category category) {
        Duty duty = new Duty(title, description, LocalDate.parse(date), status, category);
        taskService.insertDuty(duty);
        return "Dodano task";
    }

}
