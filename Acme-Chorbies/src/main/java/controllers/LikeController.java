package controllers;

import domain.Chorbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import services.ChorbiService;
import services.LikesService;

@Controller
@RequestMapping("/like")
public class LikeController {


    @Autowired
    ChorbiService chorbiService;

    @Autowired
    LikesService likesService;

    @RequestMapping("/chorbiesLikedMe")
    public ModelAndView userLiked(){
        Chorbi chorbi = chorbiService.findByPrincipal();
        ModelAndView result = new ModelAndView("like/usersLikedMe");
        Assert.notNull(chorbi);
        boolean hasCreditCard = true;
        if(chorbi.getCreditCard() == null){
            hasCreditCard = false;
        }else{
            if(!chorbi.getCreditCard().checkValidity()){
                hasCreditCard = false;
            }
        }
        result.addObject("hascreditcard",hasCreditCard);
        if(hasCreditCard){
            result.addObject("chorbies",likesService.findChorbiesLikedPrincipal(chorbi));
        }

       return result;
    }
}
