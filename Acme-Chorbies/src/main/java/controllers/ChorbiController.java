
package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ChorbiService;
import domain.Chorbi;
import domain.CreditCard.BrandName;

@Controller
@RequestMapping("/chorbi")
public class ChorbiController {

	@Autowired
	UserDetailsService	userDetailsService;

	@Autowired
	ChorbiService		chorbiService;


	@RequestMapping(value = "/chorbies", method = RequestMethod.GET)
	public ModelAndView listOffer() {
		final ModelAndView result = new ModelAndView("actor/chorbies");

		final Collection<Chorbi> chorbies = this.chorbiService.findAll();

		result.addObject("chorbies", chorbies);
		result.addObject("requestURI", "actor/chorbies.do");

		return result;
	}

	@RequestMapping(value = "/register")
	public ModelAndView index() {
		return this.createEditModelAndView(new Chorbi(), null);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "register")
	public ModelAndView create(@ModelAttribute("chorbi") final Chorbi chorbi, final BindingResult binding) {

		ModelAndView result;
		if (binding.hasErrors()) {
			for (final ObjectError e : binding.getAllErrors()) {
				System.out.println(e.getDefaultMessage());
				System.out.println(e.getObjectName());
				System.out.println(e.getCodes());
			}
			result = this.createEditModelAndView(chorbi, "wrong");
		} else
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
		final Collection<BrandName> brandNames = new ArrayList<BrandName>(Arrays.asList(BrandName.values()));

		result = new ModelAndView("chorbi/register");
		result.addObject("chorbi", chorbi);
		result.addObject("message", message);
		result.addObject("brandnames", brandNames);
		return result;

	}

}
