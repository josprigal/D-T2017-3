package controllers;

import domain.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.BannerService;

@Controller
@RequestMapping("/banner")
public class BannerController extends AbstractController {


    @Autowired
    BannerService bannerService;
    @RequestMapping("/edit")
    public ModelAndView editView() {
        ModelAndView result;
        Banner banner = new Banner();
        result = new ModelAndView("banner/edit");
        result.addObject("banners",bannerService.findAll());
        int i = 0;
        for(Banner e: bannerService.findAll()){
        result.addObject("banner"+i, e);
        i++;
        }

        return result;
    }

    @RequestMapping(value = "/edit/{banner2}", method = RequestMethod.POST)
    public ModelAndView editPost(@ModelAttribute("banner") Banner banner, @PathVariable Banner banner2) {
        Assert.notNull(banner);
        Assert.notNull(banner2);
        try{
            banner2.setUrl(banner.getUrl());
            bannerService.save(banner2);

            return new ModelAndView("redirect:/banner/edit.do");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ModelAndView("redirect:/banner/edit.do");
        }
    }
}
