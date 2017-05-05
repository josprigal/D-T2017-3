
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.ChorbiService;
import services.EventService;
import domain.Chorbi;

@Controller
@RequestMapping("/event")
public class EventController extends AbstractController {

	@Autowired
	private EventService	eventService;

	@Autowired
	private ChorbiService	chorbiService;


	@RequestMapping("listAvailableEvents")
	public ModelAndView listAvailable() {
		final ModelAndView result = new ModelAndView("event/listAvailableEvents");

		result.addObject("listAvailableEvents", this.eventService.listAvailableEvents());

		result.addObject("RequestURI", "event/listAvailableEvents.do");

		return result;
	}

	@RequestMapping("listRegisteredEvents")
	public ModelAndView listRegistered() {
		final ModelAndView result = new ModelAndView("event/listRegisteredEvents");
		final Chorbi c = this.chorbiService.findByPrincipal();
		result.addObject("listRegisteredEvents", c.getEvents());

		result.addObject("RequestURI", "event/listRegisteredEvents.do");

		return result;
	}
}
