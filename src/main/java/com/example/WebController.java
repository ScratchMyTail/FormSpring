package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christerhansen on 13.09.2016.
 */

@Controller
public class WebController {
    List<Person> personList = new ArrayList<>();

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView visSkjema(){
        return new ModelAndView("add", "person", new Person());
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public @ResponseBody Person save(@RequestParam(value = "fornavn") String fornavn, @RequestParam(value = "etternavn") String etternavn){
        Person person = new Person(fornavn, etternavn);
        personList.add(person);
        return person;
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public String list(ModelMap map){
        map.addAttribute("list", this.personList);
        return "list";
    }

    @RequestMapping(path = "/list/json", method = RequestMethod.GET)
    public @ResponseBody List<Person> getJson(){
        return personList;
    }

    @RequestMapping(path = "/person/json", method = RequestMethod.GET)
    public @ResponseBody Person getPerson(@RequestParam(value = "fornavn", required = false) String fornavn){
        for(Person p : personList){
            if(p.getFornavn().equals(fornavn)){
                return p;
            }
        }

        return null;
    }
}
