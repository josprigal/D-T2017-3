package controllers;

import domain.Chorbi;
import domain.SearchTemplate;
import javafx.geometry.Pos;
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

@Controller
@RequestMapping("/chorbi/searchtemplate")
public class SearchTemplateController {

    @Autowired
    ChorbiService chorbiService;

    @Autowired
    SearchTemplateService searchTemplateService;

    @RequestMapping("/edit")
    public ModelAndView editGet(){
        Chorbi chorbi;

        chorbi = chorbiService.findByPrincipal();
        SearchTemplate searchTemplate = searchTemplateService.createIfNotExists(chorbi);

        Assert.notNull(chorbi);

        return createEditAndView(searchTemplate,null);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public ModelAndView editPost(@ModelAttribute("searchtemplate") SearchTemplate searchTemplate, BindingResult bindingResult){
        Chorbi chorbi;

        chorbi = chorbiService.findByPrincipal();
        Assert.notNull(chorbi);
        if(bindingResult.hasErrors()){
            return createEditAndView(searchTemplate,"wrong");
        }
        try{
            searchTemplateService.reconstruct(searchTemplate);
            return createEditAndView(searchTemplate,null);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return createEditAndView(searchTemplate,"wrong");
        }

    }

    public ModelAndView createEditAndView(SearchTemplate searchTemplate, String message){
        ModelAndView result = new ModelAndView("searchtemplate/edit");
        result.addObject("searchtemplate",searchTemplate);
        result.addObject("message",message);

        return result;
    }
}
