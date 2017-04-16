/* AdministratorController.java
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import domain.Banner;
import domain.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.BannerService;
import services.ConfigurationService;

import java.util.ArrayList;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------
	
	public AdministratorController() {
		super();
	}

	@Autowired
	BannerService bannerService;

	@Autowired
    ConfigurationService configurationService;

	@RequestMapping("/configuration/edit")
	public ModelAndView editView() {
		ModelAndView result;
		result = new ModelAndView("administrator/configuration");
		result.addObject("banners",bannerService.findAll());
		result.addObject("configuration", new ArrayList<>(configurationService.findAll()).get(0));
		int i = 0;
		for(Banner e: bannerService.findAll()){
			result.addObject("banner"+i, e);
			i++;
		}

		return result;
	}

	@RequestMapping(value = "/configuration/edit/banner/{banner2}", method = RequestMethod.POST)
	public ModelAndView editBannerPost(@ModelAttribute("banner") Banner banner, @PathVariable Banner banner2) {
		Assert.notNull(banner);
		Assert.notNull(banner2);
		try{
			banner2.setUrl(banner.getUrl());
			bannerService.save(banner2);

			return new ModelAndView("redirect:/administrator/configuration/edit.do");
		}catch (Exception e){

			return new ModelAndView("redirect:/administrator/configuration/edit.do");
		}
	}

    @RequestMapping(value = "/configuration/edit/configuration", method = RequestMethod.POST)
    public ModelAndView editConfigurationPost(@ModelAttribute("configuration") Configuration configuration) {
	    Configuration configuration1 = new ArrayList<>(configurationService.findAll()).get(0);
        Assert.notNull(configuration1);
        Assert.notNull(configuration);
        try{
            configurationService.reconstruct(configuration,configuration1);

            return new ModelAndView("redirect:/administrator/configuration/edit.do");
        }catch (Exception e){

            return new ModelAndView("redirect:/administrator/configuration/edit.do");
        }
    }
}