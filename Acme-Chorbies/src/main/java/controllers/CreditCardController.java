package controllers;

import controllers.AbstractController;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.ChirpService;
import services.ChorbiService;
import services.CreditCardService;

import javax.validation.Valid;
import java.util.Arrays;

@Controller
@RequestMapping("/creditcard")
public class CreditCardController extends AbstractController{

    @Autowired
    ChorbiService chorbiService;
    @Autowired
    CreditCardService creditCardService;

    @Autowired
    ActorService actorService;

    @RequestMapping("/edit")
    public ModelAndView edit(){
        Actor actor = actorService.findActorByPrincipal();
        Assert.notNull(actor);
        Assert.isTrue(actor instanceof CreditCardUser);
        CreditCardUser principal = (CreditCardUser) actor;
        CreditCard creditCard = principal.getCreditCard();
        if(creditCard==null){
            creditCard = new CreditCard();
        }

        return editView(creditCard,null);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView post(@ModelAttribute CreditCard creditCard, BindingResult bindingResult){
        Assert.notNull(creditCard);
        Actor actor = actorService.findActorByPrincipal();
        Assert.isTrue(actor instanceof CreditCardUser);
        creditCard = creditCardService.reconstruct(creditCard, bindingResult);
        Assert.notNull(actor);
        if(bindingResult.hasErrors()){
            return editView(creditCard,"wrong");
        }
        try{

            creditCardService.save(creditCard);
            return new ModelAndView("redirect:/creditcard/edit.do");
        }catch (Exception e){
            return editView(creditCard,"wrong");
        }
    }



    private ModelAndView editView(CreditCard creditCard, String message){
        ModelAndView result = new ModelAndView("creditcard/edit");
        result.addObject("creditCard", creditCard);
        result.addObject("message", message);
        result.addObject("brandnames", Arrays.asList(CreditCard.BrandName.values()));
        return result;
    }
}
