package controllers.actor.administrator;

import controllers.AbstractController;
import domain.Chorbi;
import domain.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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

    @RequestMapping("/edit")
    public ModelAndView edit(){
        Chorbi principal = chorbiService.findByPrincipal();
        Assert.notNull(principal);
        CreditCard creditCard = principal.getCreditCard();
        if(creditCard==null){
            creditCard = new CreditCard();
        }

        return editView(creditCard,null);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView post(@ModelAttribute CreditCard creditCard, BindingResult bindingResult){
        Assert.notNull(creditCard);
        Chorbi principal = chorbiService.findByPrincipal();
        creditCard = creditCardService.reconstruct(creditCard, bindingResult);
        Assert.notNull(principal);
        if(bindingResult.hasErrors()){
            return editView(creditCard,"wrong");
        }
        try{
            creditCard.setChorbi(principal);

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
