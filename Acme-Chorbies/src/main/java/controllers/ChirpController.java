package controllers;

import domain.Actor;
import domain.Chirp;
import domain.Chorbi;
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


    @RequestMapping("/resend/{reply}")
    public ModelAndView resend(@PathVariable Chirp reply)
    {
        Chorbi principal = chorbiService.findByPrincipal();
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
        Chorbi principal = chorbiService.findByPrincipal();
        Assert.notNull(principal);
        result.addObject("chirpsSent",principal.getChirpsSents());
        result.addObject("chirpsReceived",principal.getChirpsReceived());
        result.addObject("RequestURI","chirp/list.do");

        return result;
    }

    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public ModelAndView createNewChirPost(@ModelAttribute Chirp chirp, BindingResult bindingResult){
        Actor actor = actorService.findActorByPrincipal();
        Assert.notNull(actor);
        Assert.isTrue(actor instanceof Chorbi);
        Chorbi principal = (Chorbi) actor;
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
        Assert.isTrue(actor instanceof Chorbi);
        Chorbi principal = (Chorbi) actor;
        Assert.isTrue(chirp.getRecipent()== principal || chirp.getSender()==principal);
        chirpService.delete(chirp);

        return new ModelAndView("redirect:/chirp/list.do");
    }


    private ModelAndView createNewChirpView(Chirp chirp, String message){
        ModelAndView result = new ModelAndView("chirp/new");
        result.addObject("chirp",chirp);
        Chorbi principal = chorbiService.findByPrincipal();
        Assert.notNull(principal);
        List<Chorbi> chorbis = new ArrayList<>(chorbiService.findAll());
        chorbis.remove(principal);
        result.addObject("chorbis",chorbis);
        result.addObject("message",message);

        return result;
    }
}
