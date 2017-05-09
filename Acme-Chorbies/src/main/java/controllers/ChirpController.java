package controllers;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.ChirpService;
import services.ChorbiService;
import services.ManagerService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/chirp")
public class ChirpController extends AbstractController {

    @Autowired
    ActorService actorService;

    @Autowired
    ChirpService chirpService;

    @Autowired
    ManagerService managerService;

    @Autowired
    private ChorbiService chorbiService;

    @RequestMapping("/new")
    public ModelAndView createNewChirp(){
        return createNewChirpView(new Chirp(),null);
    }


    @RequestMapping("/reply/{reply}")
    public ModelAndView reply(@PathVariable Chirp reply)
    {
        Chorbi principal = chorbiService.findByPrincipal();
        Assert.isTrue(reply.getRecipent().getId() == principal.getId());
        Chirp chirp = new Chirp();
        chirp.setRecipent(reply.getSender());
        return createNewChirpView(chirp,null);
    }

    @RequestMapping("/broadcast")
    public ModelAndView broadcast()
    {
        Manager manager = managerService.findByPrincipal();
        Assert.notNull(manager);
        Chirp chirp = new Chirp();
        return createBroadCastChirpView(chirp,null);
    }
    @RequestMapping(value = "/broadcast",method = RequestMethod.POST)
    public ModelAndView broadcastpost(@ModelAttribute Chirp chirp,
                                      BindingResult bindingResult, HttpServletRequest request){
        Manager manager = managerService.findByPrincipal();
        Assert.notNull(manager);
        if(bindingResult.hasErrors()){
            return createNewChirpView(chirp,"wrong");
        }
        try{
            chirpService.broadcast(chirp);
            return new ModelAndView("redirect:/manager/event/all.do");
        } catch (Exception e){
            return createBroadCastChirpView(chirp,"wrong");
        }
    }

    @RequestMapping("/resend/{reply}")
    public ModelAndView resend(@PathVariable Chirp reply)
    {
        CreditCardUser principal = chorbiService.findByPrincipal();
        Assert.isTrue(reply.getSender().getId() == principal.getId());
        Chirp chirp = new Chirp();
        chirp.setRecipent(null);
        chirp.setAttachments(reply.getAttachments());
        chirp.setSubject(reply.getSubject());
        chirp.setText(reply.getText());
        return createNewChirpView(chirp,null);
    }

    @RequestMapping("list")
    public ModelAndView listView(){
        ModelAndView result = new ModelAndView("chirp/list");
        Actor actor = actorService.findActorByPrincipal();
        Assert.notNull(actor);
        CreditCardUser principal = (CreditCardUser) actor;
        Assert.notNull(principal);
        result.addObject("chirpsSent",principal.getChirpsSents());
        result.addObject("chirpsReceived",principal.getChirpsReceived());
        result.addObject("RequestURI","chirp/list.do");

        return result;
    }

    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public ModelAndView createNewChirPost(@ModelAttribute @Valid Chirp chirp, BindingResult bindingResult){
        Actor actor = actorService.findActorByPrincipal();
        Assert.notNull(actor);
        Assert.isTrue(actor instanceof CreditCardUser);
        CreditCardUser principal = (CreditCardUser) actor;
        if(bindingResult.hasErrors()){
            return createNewChirpView(chirp,"wrong");
        }
        try{
            chirp.setSent(new Date());
            chirp.setSender(principal);
            chirpService.save(chirp);

            return new ModelAndView("redirect:list.do");
        } catch (Exception e){
            return createNewChirpView(chirp,"wrong");
        }
    }


    @RequestMapping(value = "/delete/{chirp}")
    public ModelAndView deleteChrip(@PathVariable Chirp chirp){
        Actor actor = actorService.findActorByPrincipal();
        Assert.notNull(actor);
        Assert.isTrue(actor instanceof CreditCardUser);
        CreditCardUser principal = (CreditCardUser) actor;
        Assert.isTrue(chirp.getRecipent()== principal || chirp.getSender()==principal);
        chirpService.delete(chirp);

        return new ModelAndView("redirect:/chirp/list.do");
    }

    private ModelAndView createBroadCastChirpView(Chirp chirp, String message){
        ModelAndView result = new ModelAndView("chirp/broadcast");
        result.addObject("chirp",chirp);
        result.addObject("message",message);

        return result;
    }
    private ModelAndView createNewChirpView(Chirp chirp, String message){
        ModelAndView result = new ModelAndView("chirp/new");
        result.addObject("chirp",chirp);
        Actor principal = actorService.findActorByPrincipal();
        Assert.notNull(principal);
        List<Chorbi> chorbis = new ArrayList<>(chorbiService.findAll());
        chorbis.remove(principal);
        result.addObject("chorbis",chorbis);
        result.addObject("message",message);

        return result;
    }
}
