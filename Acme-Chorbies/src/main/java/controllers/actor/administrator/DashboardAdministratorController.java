
package controllers.actor.administrator;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ChorbiService;
import services.ManagerService;
import controllers.AbstractController;
import domain.Chorbi;
import domain.Manager;

@Controller
@RequestMapping("/administrator/dashboard")
public class DashboardAdministratorController extends AbstractController {

	// SERVICES ------------------------------------------

	@Autowired
	private ChorbiService	chorbiService;
	@Autowired
	private ManagerService	managerService;


	// Constructor

	public DashboardAdministratorController() {
		super();
	}

	// Methods

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView displayDashboard() {
		ModelAndView result;

		final Integer minimunAgeChorbies = this.chorbiService.minimunAgeChorbies();
		final Integer maximunAgeChorbies = this.chorbiService.maximunAgeChorbies();
		final Double avgAgeChorbies = this.chorbiService.avgAgeChorbies();

		final Double ratioNotCreditCard = this.chorbiService.ratioNotCreditCard();
		final Double ratioLove = this.chorbiService.ratioLove();
		final Double ratioFriendship = this.chorbiService.ratioFriendship();
		final Double ratioActivities = this.chorbiService.ratioActivities();

		final Integer minLikesChorbi = this.chorbiService.minLikesChorbi();
		final Integer maxLikesChorbi = this.chorbiService.maxLikesChorbi();
		final Double avgLikesChorbi = this.chorbiService.avgLikesChorbi();
		final Integer minChirpsChorbiSent = this.chorbiService.minChirpsChorbiSent();
		final Integer maxChirpsChorbiSent = this.chorbiService.maxChirpsChorbiSent();
		final Double avgChirpsChorbiSent = this.chorbiService.avgChirpsChorbiSent();
		final Integer minChirpsChorbiReceived = this.chorbiService.minChirpsChorbiReceived();
		final Integer maxChirpsChorbiReceived = this.chorbiService.maxChirpsChorbiReceived();
		final Double avgChirpsChorbiReceived = this.chorbiService.avgChirpsChorbiReceived();

		final Map<String, Integer> numberOfChorbiesPerCity = this.chorbiService.numberOfChorbiesPerCity();
		final Map<String, Integer> numberOfChorbiesPerCountry = this.chorbiService.numberOfChorbiesPerCountry();

		final Collection<Chorbi> listChorbiesNumberOfLikes = this.chorbiService.listChorbiesNumberOfLikes();
		final Collection<Chorbi> listChorbiesMoreChirpsReceived = this.chorbiService.listChorbiesMoreChirpsReceived();
		final Collection<Chorbi> listChorbiesMoreChirpsSent = this.chorbiService.listChorbiesMoreChirpsSent();

		final Collection<Manager> listManagersMoreEventsCreated = this.managerService.listManagersMoreEventsCreated();
		final Collection<Manager> listManagersFees = this.managerService.listManagersFees();
		final Collection<Chorbi> listChorbiesMoreEventsRegistered = this.chorbiService.listChorbiesMoreEventsRegistered();
		final Collection<Chorbi> listChorbiesFees = this.chorbiService.listChorbiesFees();
		final Collection<Chorbi> listChorbiesAvgStars = this.chorbiService.listChorbiesAvgStars();
		final Double avgStarsChorbi = this.chorbiService.avgStarsChorbi();
		final Double maxStarsChorbi = this.chorbiService.maxStarsChorbi();
		final Double minStarsChorbi = this.chorbiService.minStarsChorbi();

		result = this.createDashboardModelAndView(minimunAgeChorbies, maximunAgeChorbies, avgAgeChorbies, ratioNotCreditCard, ratioLove, ratioFriendship, ratioActivities, minLikesChorbi, maxLikesChorbi, avgLikesChorbi, minChirpsChorbiSent,
			maxChirpsChorbiSent, avgChirpsChorbiSent, minChirpsChorbiReceived, maxChirpsChorbiReceived, avgChirpsChorbiReceived, numberOfChorbiesPerCity, numberOfChorbiesPerCountry, listChorbiesNumberOfLikes, listChorbiesMoreChirpsReceived,
			listChorbiesMoreChirpsSent, listManagersMoreEventsCreated, listManagersFees, listChorbiesMoreEventsRegistered, listChorbiesFees, listChorbiesAvgStars, avgStarsChorbi, maxStarsChorbi, minStarsChorbi);
		return result;
	}
	protected ModelAndView createDashboardModelAndView(final Integer minimunAgeChorbies, final Integer maximunAgeChorbies, final Double avgAgeChorbies, final Double ratioNotCreditCard, final Double ratioLove, final Double ratioFriendship,
		final Double ratioActivities, final Integer minLikesChorbi, final Integer maxLikesChorbi, final Double avgLikesChorbi, final Integer minChirpsChorbiSent, final Integer maxChirpsChorbiSent, final Double avgChirpsChorbiSent,
		final Integer minChirpsChorbiReceived, final Integer maxChirpsChorbiReceived, final Double avgChirpsChorbiReceived, final Map<String, Integer> numberOfChorbiesPerCity, final Map<String, Integer> numberOfChorbiesPerCountry,
		final Collection<Chorbi> listChorbiesNumberOfLikes, final Collection<Chorbi> listChorbiesMoreChirpsReceived, final Collection<Chorbi> listChorbiesMoreChirpsSent, final Collection<Manager> listManagersMoreEventsCreated,
		final Collection<Manager> listManagersFees, final Collection<Chorbi> listChorbiesMoreEventsRegistered, final Collection<Chorbi> listChorbiesFees, final Collection<Chorbi> listChorbiesAvgStars, final Double avgStarsChorbi,
		final Double maxStarsChorbi, final Double minStarsChorbi) {
		ModelAndView result;

		result = new ModelAndView("administrator/dashboard");

		result.addObject("minimunAgeChorbies", minimunAgeChorbies);
		result.addObject("maximunAgeChorbies", maximunAgeChorbies);
		result.addObject("avgAgeChorbies", avgAgeChorbies);
		result.addObject("ratioNotCreditCard", ratioNotCreditCard);
		result.addObject("ratioLove", ratioLove);
		result.addObject("ratioFriendship", ratioFriendship);
		result.addObject("ratioActivities", ratioActivities);
		result.addObject("minLikesChorbi", minLikesChorbi);
		result.addObject("maxLikesChorbi", maxLikesChorbi);
		result.addObject("avgLikesChorbi", avgLikesChorbi);
		result.addObject("minChirpsChorbiSent", minChirpsChorbiSent);
		result.addObject("maxChirpsChorbiSent", maxChirpsChorbiSent);
		result.addObject("avgChirpsChorbiSent", avgChirpsChorbiSent);
		result.addObject("minChirpsChorbiReceived", minChirpsChorbiReceived);
		result.addObject("minChirpsChorbiReceived", minChirpsChorbiReceived);
		result.addObject("maxChirpsChorbiReceived", maxChirpsChorbiReceived);
		result.addObject("avgChirpsChorbiReceived", avgChirpsChorbiReceived);
		result.addObject("numberOfChorbiesPerCity", numberOfChorbiesPerCity);
		result.addObject("numberOfChorbiesPerCountry", numberOfChorbiesPerCountry);
		result.addObject("listChorbiesNumberOfLikes", listChorbiesNumberOfLikes);
		result.addObject("listChorbiesMoreChirpsReceived", listChorbiesMoreChirpsReceived);
		result.addObject("listChorbiesMoreChirpsSent", listChorbiesMoreChirpsSent);

		result.addObject("listManagersMoreEventsCreated", listManagersMoreEventsCreated);
		result.addObject("listManagersFees", listManagersFees);
		result.addObject("listChorbiesMoreEventsRegistered", listChorbiesMoreEventsRegistered);
		result.addObject("listChorbiesFees", listChorbiesFees);
		result.addObject("listChorbiesAvgStars", listChorbiesAvgStars);
		result.addObject("avgStarsChorbi", avgStarsChorbi);
		result.addObject("maxStarsChorbi", maxStarsChorbi);
		result.addObject("minStarsChorbi", minStarsChorbi);

		result.addObject("requestURI", "administrator/dashboard.do");

		return result;
	}

}
