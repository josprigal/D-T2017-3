package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/info")
public class InfoController extends AbstractController {

    @RequestMapping(value = "/cookies")
    public ModelAndView cookies() {
        ModelAndView result;
        result = new ModelAndView("misc/cookies");

        return result;
    }

    @RequestMapping(value = "/legalinformation")
    public ModelAndView legal() {
        ModelAndView result;
        result = new ModelAndView("misc/legalinformation");

        return result;
    }
}
