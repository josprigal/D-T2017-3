
package controllers;

import domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.ChorbiService;
import services.EventService;
import domain.Chorbi;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/event")
public class EventController extends AbstractController {

	@Autowired
	private EventService	eventService;

	@Autowired
	private ChorbiService	chorbiService;



	@RequestMapping("/all")
	public ModelAndView all() {
		final ModelAndView result = new ModelAndView("event/listAllEvents");
        Chorbi chorbi = chorbiService.findByPrincipal();
        result.addObject("principal",chorbi);
		result.addObject("events", this.eventService.findAll());

		result.addObject("RequestURI", "event/all.do");

		return result;
	}

	@RequestMapping("/listAvailableEvents")
	public ModelAndView listAvailable() {
		final ModelAndView result = new ModelAndView("event/listAvailableEvents");

		result.addObject("listAvailableEvents", this.eventService.listAvailableEvents());

		result.addObject("RequestURI", "event/listAvailableEvents.do");

		return result;
	}

	@RequestMapping("/listRegisteredEvents")
	public ModelAndView listRegistered() {
		final ModelAndView result = new ModelAndView("event/listRegisteredEvents");
		final Chorbi c = this.chorbiService.findByPrincipal();
		result.addObject("listRegisteredEvents", c.getEvents());

		result.addObject("RequestURI", "event/listRegisteredEvents.do");

		return result;
	}

	@RequestMapping("register/{event}")
	public ModelAndView register(@PathVariable("event")Event event,HttpServletRequest request) {
		final Chorbi c = this.chorbiService.findByPrincipal();
		Assert.notNull(c);
		try{
			eventService.register(event);
            return new ModelAndView("redirect:"+request.getHeader("Referer"));
		} catch (Exception e){
            return new ModelAndView("redirect:"+request.getHeader("Referer"));
        }
	}

    @RequestMapping("unregister/{event}")
    public ModelAndView unregister(@PathVariable("event")Event event, HttpServletRequest request) {
        final Chorbi c = this.chorbiService.findByPrincipal();
        Assert.notNull(c);
        try{
            eventService.unregister(event);
            return new ModelAndView("redirect:"+request.getHeader("Referer"));
        } catch (Exception e){
            return new ModelAndView("redirect:"+request.getHeader("Referer"));
        }
    }
}
