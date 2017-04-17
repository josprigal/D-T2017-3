
package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ChorbiService;
import services.SearchTemplateService;
import domain.Chorbi;
import domain.SearchTemplate;

@Controller
@RequestMapping("/chorbi/searchtemplate")
public class SearchTemplateController {

	@Autowired
	ChorbiService			chorbiService;

	@Autowired
	SearchTemplateService	searchTemplateService;


	@RequestMapping("/edit")
	public ModelAndView editGet() {
		Chorbi chorbi;

		chorbi = this.chorbiService.findByPrincipal();
		final SearchTemplate searchTemplate = this.searchTemplateService.createIfNotExists(chorbi);

		Assert.notNull(chorbi);

		return this.createEditAndView(searchTemplate, null);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView editPost(@ModelAttribute("searchtemplate") final SearchTemplate searchTemplate, final BindingResult bindingResult) {
		Chorbi chorbi;

		chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi);
		if (bindingResult.hasErrors())
			return this.createEditAndView(searchTemplate, "wrong");
		try {
			this.searchTemplateService.reconstruct(searchTemplate);
			return this.createEditAndView(searchTemplate, null);
		} catch (final Exception e) {
			System.out.println(e.getMessage());
			return this.createEditAndView(searchTemplate, "wrong");
		}

	}

	public ModelAndView createEditAndView(final SearchTemplate searchTemplate, final String message) {
		final ModelAndView result = new ModelAndView("searchtemplate/edit");
		result.addObject("searchtemplate", searchTemplate);
		result.addObject("message", message);

		return result;
	}
}
