package com.example.firstspringboot;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StuffController {
  List <Stuff> allStuff = new ArrayList<>();

  //constructor
  public StuffController() {
    allStuff.add(new Stuff(1,"amany", "developer", 30.000));
    allStuff.add(new Stuff(2,"ahmed", "tester", 25.000));
    allStuff.add(new Stuff(3,"ali", "devops", 20.000));
  }

  //index
  @GetMapping("/")
  public String getAllStuff (Model model){

    model.addAttribute("allStuff",allStuff);
    return "allStuff";
  }

  //view / read
  @GetMapping("/stuffDetails")
  public String getStuffDetails(Model model){

    Stuff myStuff = new Stuff(4,"amany","developer",20.000);
    model.addAttribute("stuff",myStuff);

    return "stuffDetails";
  }

  //create
  @GetMapping("/createNewStuff")
  public  String creatStuffView(Model model){
    model.addAttribute("stuff", new Stuff());

    return "createNewStuff";
  }


  //store
  @PostMapping("/addNewStuff")
  public String addNewStuff(@Valid Stuff stuff , BindingResult result){
    if(result.hasErrors())
      return "createNewStuff";
    allStuff.add(stuff);
    return "redirect:/";
  }

  //edit view
  @GetMapping("/editStuff")
  public String editStuff(@RequestParam int id,  Model model){

    Stuff stuff = allStuff.stream()
      .filter(s -> s.getId() == id)
      .findFirst()
      .orElse(null);

    model.addAttribute("stuff",stuff);
    return "editStuff";
  }

  //update
  @PostMapping("/updateStuff")
  public String updateStuff(@Valid Stuff stuff , BindingResult result){

    if(result.hasErrors())
      return "editStuff";

    Stuff myStuff = allStuff.stream()
      .filter(s -> s.getId() == stuff.getId())
      .findFirst()
      .orElse(null);

    if (myStuff == null) {
      return "redirect:/";
    }

    myStuff.setName(stuff.getName());
    myStuff.setPosition(stuff.getPosition());
    myStuff.setSalary(stuff.getSalary());

    return "redirect:/";
  }


  //destroy
  @PostMapping("/deleteStuff")
  public String delete(@RequestParam int id){
    Stuff myStuff = allStuff.stream()
      .filter(s -> s.getId() == id)
      .findFirst()
      .orElse(null);

    if (myStuff == null) {
      return "redirect:/";
    }

    allStuff.remove(myStuff);

    return "redirect:/";
  }



}
