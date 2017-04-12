
package controllers.actor.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ChorbiService;
import controllers.AbstractController;
import domain.Chorbi;

@Controller
@RequestMapping("/actor/administrator/dashboard")
public class DashboardAdministratorController extends AbstractController {

	// SERVICES ------------------------------------------

	@Autowired
	private ChorbiService	chorbiService;


	// Constructor

	public DashboardAdministratorController() {
		super();
	}

	// Methods

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView displayDashboard() {
		ModelAndView result;

		final Integer minimunAgeChorbies = null;
		final Integer maximunAgeChorbies = null;
		final Double avgAgeChorbies = null;
		final Double ratioNotCreditCard = null;
		final Double ratioLove = null;
		final Double ratioFriendship = null;
		final Double ratioActivities = null;
		final Integer minLikesChorbi = null;
		final Integer maxLikesChorbi = null;
		final Double avgLikesChorbi = null;
		final Integer minChirpsChorbiSent = null;
		final Integer maxChirpsChorbiSent = null;
		final Double avgChirpsChorbiSent = null;
		final Integer minChirpsChorbiReceived = null;
		final Integer maxChirpsChorbiReceived = null;
		final Double avgChirpsChorbiReceived = null;
		final Integer numberOfChorbiesPerCity = null;
		final Integer numberOfChorbiesPerCountry = null;
		final Collection<Chorbi> listChorbiesNumberOfLikes = null;
		final Collection<Chorbi> listChorbiesMoreChirpsReceived = null;
		final Collection<Chorbi> listChorbiesMoreChirpsSent = null;

		result = this.createDashboardModelAndView(minimunAgeChorbies, maximunAgeChorbies, avgAgeChorbies, ratioNotCreditCard, ratioLove, ratioFriendship, ratioActivities, minLikesChorbi, maxLikesChorbi, avgLikesChorbi, minChirpsChorbiSent,
			maxChirpsChorbiSent, avgChirpsChorbiSent, minChirpsChorbiReceived, maxChirpsChorbiReceived, avgChirpsChorbiReceived, numberOfChorbiesPerCity, numberOfChorbiesPerCountry, listChorbiesNumberOfLikes, listChorbiesMoreChirpsReceived,
			listChorbiesMoreChirpsSent);
		return result;
	}
	protected ModelAndView createDashboardModelAndView(final Integer minimunAgeChorbies, final Integer maximunAgeChorbies, final Double avgAgeChorbies, final Double ratioNotCreditCard, final Double ratioLove, final Double ratioFriendship,
		final Double ratioActivities, final Integer minLikesChorbi, final Integer maxLikesChorbi, final Double avgLikesChorbi, final Integer minChirpsChorbiSent, final Integer maxChirpsChorbiSent, final Double avgChirpsChorbiSent,
		final Integer minChirpsChorbiReceived, final Integer maxChirpsChorbiReceived, final Double avgChirpsChorbiReceived, final Integer numberOfChorbiesPerCity, final Integer numberOfChorbiesPerCountry,
		final Collection<Chorbi> listChorbiesNumberOfLikes, final Collection<Chorbi> listChorbiesMoreChirpsReceived, final Collection<Chorbi> listChorbiesMoreChirpsSent) {
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

		result.addObject("requestURI", "actor/administrator/dashboard.do");

		return result;
	}

}
