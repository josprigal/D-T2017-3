
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.EventService;

@Controller
@RequestMapping("/event")
public class EventController extends AbstractController {

	@Autowired
	private EventService	eventService;


	@RequestMapping("listAvailableEvents")
	public ModelAndView listView() {
		final ModelAndView result = new ModelAndView("event/listAvailableEvents");

		result.addObject("listAvailableEvents", this.eventService.listAvailableEvents());

		result.addObject("RequestURI", "event/listAvailableEvents.do");

		return result;
	}

}
