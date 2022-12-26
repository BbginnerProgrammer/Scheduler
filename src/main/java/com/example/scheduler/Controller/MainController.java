package com.example.scheduler.Controller;

import com.example.scheduler.Entity.Tasks;
import com.example.scheduler.repository.TasksRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static com.example.scheduler.Other.StartEndWeek.StartENdWeek;

@Controller
public class MainController {

    @Autowired
    private TasksRepository tasksRepository;

    @GetMapping("/")
    public String greeting(Model model){
        int year = LocalDate.now().getYear();
        Month month = LocalDate.now().getMonth();
        ArrayList<String> days = StartENdWeek();
        for(int i = 0; i < 5; i++) {
            days.add(i+1, String.valueOf(Integer.parseInt(days.get(i)) + 1));
        }

        List<Tasks> tasks = tasksRepository.findAll();
        ArrayList<String> list = new ArrayList<>();

        for(int i = 0; i < tasks.size(); i++) {
            list.add(tasks.get(i).getDescription());
        }
        model.addAttribute("year", year);
        model.addAttribute("month", month.name());
        model.addAttribute("month_number", month.getValue());
        model.addAttribute("days", days);
        model.addAttribute("list", list);



        return "greeting";
    }

    @RequestMapping("/testpost" )
    @PostMapping
    @ResponseBody
    public String testPost(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String[] line = name.split("_");
        System.out.println(Arrays.stream(line).toList());

        return name;

    }

    @RequestMapping("/testget" )
    @PostMapping
    @ResponseBody
    public String testGet(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String[] line = name.split("_");
        List<Tasks> tasks = tasksRepository.findAllByName(line[0]);
        for(int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getName().equals(line[2])){
                Tasks task = tasksRepository.findByNameAndLine_id(line[0], line[2]);
                task.setDescription(line[1]);
                tasksRepository.save(task);
                break;
            }
            else if( i == tasks.size() - 1 && !tasks.get(i).getName().equals(line[2])){
                Tasks task = new Tasks(line[0], line[1], line[2]);
                tasksRepository.save(task);
            }
        }

        System.out.println(Arrays.stream(line).toList());

        return name;
    }


}
