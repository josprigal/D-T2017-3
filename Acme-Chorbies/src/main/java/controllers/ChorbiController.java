
package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;

import domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ChorbiService;
import services.LikesService;
import domain.Chorbi;
import domain.Chorbi.Gender;
import domain.Chorbi.Relationship;
import domain.Likes;
import services.LikesService;
import services.SearchTemplateService;


@Controller
@RequestMapping("/chorbi")
public class ChorbiController {

	@Autowired
	UserDetailsService	userDetailsService;

	@Autowired
	ChorbiService		chorbiService;

	@Autowired
	SearchTemplateService searchTemplateService;

	@Autowired
	LikesService likesService;

	@Autowired
	private ActorService actorService;



	@RequestMapping(value = "/chorbies", method = RequestMethod.GET)
	public ModelAndView listOffer() {
		final ModelAndView result = new ModelAndView("chorbies");

		final Collection<Chorbi> chorbies = this.chorbiService.findAll();

		result.addObject("chorbies", chorbies);
		Actor actor = actorService.findActorByPrincipal();
		if(actor instanceof Chorbi){
			result.addObject("principal", actor);
		}
		result.addObject("requestURI", "chorbies.do");

		return result;
	}

	@RequestMapping(value = "/c/{chorbi}/likes", method = RequestMethod.GET)
	public ModelAndView listLikes(@PathVariable final Chorbi chorbi) {
		final ModelAndView result = new ModelAndView("chorbies/likes");

		final Collection<Likes> likes = this.likesService.findAll();
		final Collection<Chorbi> chorbiesLike = new ArrayList<Chorbi>();

		for (final Likes l : likes)
			if (l.getRecipent().equals(chorbi))
				chorbiesLike.add(l.getSender());

		result.addObject("chorbiesLike", chorbiesLike);
		result.addObject("requestURI", "chorbies/likes.do");

		return result;
	}

	@RequestMapping(value = "/register")
	public ModelAndView index() {
		return this.createEditModelAndView(new Chorbi(), null);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "register")
	public ModelAndView create(@ModelAttribute("chorbi") final Chorbi chorbi, final BindingResult binding) {

		final Calendar actual = Calendar.getInstance();

		final Calendar nacimiento = Calendar.getInstance();
		nacimiento.setTime(chorbi.getBirth());

		ModelAndView result;

		if (binding.hasErrors()) {
			for (final ObjectError e : binding.getAllErrors()) {
				System.out.println(e.getDefaultMessage());
				System.out.println(e.getObjectName());
				System.out.println(e.getCodes());
			}
			result = this.createEditModelAndView(chorbi, "wrong");

		} else if (actual.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR) < 18)
			result = this.createEditModelAndView(chorbi, "wrongbirth");
		else
			try {

				final Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();

				chorbi.getUserAccount().setPassword(md5PasswordEncoder

				.encodePassword(chorbi.getUserAccount().getPassword(), null));

				this.chorbiService.create(chorbi);

				final UserDetails userDetails = this.userDetailsService.loadUserByUsername(chorbi.getUserAccount().getUsername());

				final UsernamePasswordAuthenticationToken auth =

				new UsernamePasswordAuthenticationToken(userDetails, chorbi.getUserAccount().getPassword(),

				userDetails.getAuthorities());

				if (auth.isAuthenticated())
					SecurityContextHolder.getContext().setAuthentication(auth);
				result = new ModelAndView("redirect:../");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				result = this.createEditModelAndView(chorbi, "wrong");
			}

		return result;

	}

	protected ModelAndView createEditModelAndView(final Chorbi chorbi, final String message) {

		ModelAndView result;

		result = new ModelAndView("chorbi/register");
		result.addObject("chorbi", chorbi);
		result.addObject("message", message);
		result.addObject("relationships", Arrays.asList(Relationship.values()));
		result.addObject("genders", Arrays.asList(Gender.values()));
		return result;

	}

	protected ModelAndView createEditView(final Chorbi chorbi, final String message) {

		ModelAndView result;

		result = new ModelAndView("chorbi/edit");
		result.addObject("chorbi", chorbi);
		result.addObject("message", message);
		result.addObject("relationships", Arrays.asList(Relationship.values()));
		result.addObject("genders", Arrays.asList(Gender.values()));
		return result;

	}

	@RequestMapping(value = "/edit")
	public ModelAndView editView() {
		final Chorbi chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi);
		return this.createEditView(chorbi, null);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView edit(@ModelAttribute("chorbi") final Chorbi chorbi, final BindingResult binding) {

		final Calendar actual = Calendar.getInstance();

		final Calendar nacimiento = Calendar.getInstance();
		nacimiento.setTime(chorbi.getBirth());

		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditView(chorbi, "wrong");
		else if (actual.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR) < 18)
			result = this.createEditView(chorbi, "wrongbirth");
		else {
            try {

                final Chorbi chorbi1 = this.chorbiService.reconstruct(chorbi);

                result = this.createEditView(chorbi1, null);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                result = this.createEditView(chorbi, "wrong");
            }
        }
		return result;
	}

    @RequestMapping(value = "/search")
	public ModelAndView createResultsSearchView() {

		ModelAndView result;
		Chorbi chorbi = chorbiService.findByPrincipal();
		result = new ModelAndView("chorbies/search");
		Assert.notNull(chorbi);
		if(chorbi.getCreditCard().checkValidity()){
			result.addObject("chorbies",searchTemplateService.search());
			result.addObject("requestURI","chorbi/search.do");
		}else{
			result.addObject("creditcard","notvalid");
		}

		return result;

	}

	@RequestMapping(value = "/like/{chorbi}")
	public ModelAndView likeView(@PathVariable Chorbi chorbi, @ModelAttribute("likes") Likes likes) {
		return createLikeView(new Likes(),null);
	}

	@RequestMapping(value = "/dislike/{chorbi}")
	public ModelAndView dislike(@PathVariable Chorbi chorbi) {

		ModelAndView result = new ModelAndView("redirect:/chorbi/chorbies.do");
		Assert.notNull(chorbi);
		Chorbi principal = chorbiService.findByPrincipal();
		Assert.isTrue(chorbi.getId() != principal.getId());

        likesService.dislike(chorbi);

		return result;
	}

	@RequestMapping(value = "/like/{chorbi}",method = RequestMethod.POST)
	public ModelAndView like(@PathVariable Chorbi chorbi, @ModelAttribute("likes") Likes likes) {

		ModelAndView result = new ModelAndView("redirect:/chorbi/chorbies.do");
		Assert.notNull(chorbi);
		Assert.notNull(likes);
		Chorbi principal = chorbiService.findByPrincipal();
		Assert.isTrue(chorbi.getId() != principal.getId());
		try{
			likes.setRecipent(chorbi);
			likesService.like(likes);
		}catch (Exception e){
		    System.out.println(e.getMessage());
			return createLikeView(likes,null);
		}
			return result;
	}

	private ModelAndView createLikeView(Likes likes, String message) {
		ModelAndView result = new ModelAndView("chorbies/likeDo");
		result.addObject("likes",likes);
		result.addObject("message",message);

		return result;
	}


}
