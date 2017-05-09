package controllers;

import domain.Event;
import domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.EventService;
import services.ManagerService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @Autowired
    EventService eventService;

    @RequestMapping("/event/all")
    public ModelAndView allEvents(){
        Manager manager = managerService.findByPrincipal();
        Assert.notNull(manager);
        ModelAndView result = new ModelAndView("manager/event/all");
        result.addObject("events",manager.getEvents());
        result.addObject("requestURI","manager/event/all.do");

        return result;
    }

    @RequestMapping(value = "/event/edit/{event2}",method = RequestMethod.POST)
    public ModelAndView editEventPost(@PathVariable("event2") Event event2,@ModelAttribute("event") Event event ,
                                      BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return newEventView(event,null);
        }
        try{
            eventService.editEvent(event,event2);

            return new ModelAndView("redirect:../all.do");
        } catch (Exception e){
            System.out.println(e.getMessage());
            return newEventView(event,"wrong");
        }
    }

    @RequestMapping("/event/edit/{event}")
    public ModelAndView editEvent(@PathVariable Event event){
        Assert.notNull(event);
        Manager manager = managerService.findByPrincipal();
        Assert.notNull(manager);
        Assert.isTrue(event.getManager()==manager);
        return newEventView(event,null);
    }

    @RequestMapping("/event/new")
    public ModelAndView newEvent(){
        return newEventView(new Event(),null);
    }

    @RequestMapping(value = "/event/new", method = RequestMethod.POST)
    public ModelAndView newEventPost(@ModelAttribute("event") @Valid Event event, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return newEventView(event,"wrong");
        }
        try{
            eventService.newEvent(event);
            return new ModelAndView("redirect:all.do");
        } catch (Exception e){
            return newEventView(event,"wrong");
        }
    }

    public ModelAndView newEventView(Event event, String message){
        Manager manager = managerService.findByPrincipal();
        ModelAndView result = new ModelAndView("manager/event/new");
        Assert.notNull(manager);
        boolean hasCreditCard = true;
        if(manager.getCreditCard() == null){
            hasCreditCard = false;
        }else{
            if(!manager.getCreditCard().checkValidity()){
                hasCreditCard = false;
            }
        }
        result.addObject("hascreditcard",hasCreditCard);
        result.addObject("event",event);
        result.addObject("message",message);

        return result;
    }

    @RequestMapping(value = "/event/delete/{event}")
    public ModelAndView deleteEvent(@PathVariable("event") Event event, HttpServletRequest request){
        Manager manager = managerService.findByPrincipal();
        Assert.notNull(manager);
        Assert.notNull(event);
        Assert.isTrue(event.getManager() == manager);
        try{
            eventService.deleteChirp(event);
            return new ModelAndView("redirect:../../../");
        } catch (Exception e){
            return newEventView(event,"wrong");
        }
    }
}
