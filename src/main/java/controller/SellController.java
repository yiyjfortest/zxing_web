package controller;

import clothing.vo.JournalAccout;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.ClothingService;
import service.JournalAccoutService;
import util.BaseUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SellController{

    @Resource
    private ClothingService clothingService;

    @Resource
    private JournalAccoutService journalAccoutService;

    //扫码跳转
    @RequestMapping(value = "/decode.do")
    public ModelAndView decode(HttpServletResponse response, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();

        String ean = request.getParameter("ean");

        if (ean.length() != 12 || !BaseUtil.stringIsNumber(ean)) {
            mv.setViewName("webrtc");
        } else {

            int price = BaseUtil.numberTransform(ean, 2, 10);
            mv.addObject("price", price);
            mv.setViewName("webrtc");
        }

        return mv;
    }

    //售出
    @RequestMapping(value = "/sellOut.do")
    public ModelAndView sellOut(HttpServletResponse response, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("webrtc");

        int price = Integer.parseInt(request.getParameter("price"));
        clothingService.updateClothingNumberByPrice(-1, price);

        JournalAccout journalAccout = new JournalAccout();
        journalAccout.setPrice(price);
        journalAccoutService.insertJournalAccout(journalAccout);

        return mv;
    }

    //退货
    @RequestMapping(value = "/sellIn.do")
    public ModelAndView sellIn(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("webrtc");

        int price = Integer.parseInt(request.getParameter("price"));
        clothingService.updateClothingNumberByPrice(1, price);

        JournalAccout journalAccout = new JournalAccout();
        journalAccout.setPrice(-price);
        journalAccoutService.insertJournalAccout(journalAccout);

        return mv;
    }
}
