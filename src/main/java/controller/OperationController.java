package controller;

import clothing.vo.Clothing;
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
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Controller
public class OperationController {

    @Resource
    private ClothingService clothingService;

    @Resource
    private JournalAccoutService journalAccoutService;

    //增加clothing
    @RequestMapping(value = "/insertClothind.do")
    public ModelAndView insertClothing(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("webrtc");

        String number = request.getParameter("number");
        String price = request.getParameter("price");

        if (BaseUtil.stringIsNumber(number) && BaseUtil.stringIsNumber(price)) {

            Clothing clothing = new Clothing();
            clothing.setNumber(Integer.valueOf(request.getParameter("number")));
            clothing.setPrice(Integer.valueOf(request.getParameter("price")));
            clothingService.insertClothing(clothing);
            mv.setViewName("webrtc");

        } else {
            mv.setViewName("webrtc");
        }

        return mv;
    }

    //查询所有clothing
    @RequestMapping(value = "/getAllClothing.do")
    public ModelAndView getAllClothing(HttpServletResponse response, HttpServletRequest request) {

        ModelAndView mv = new ModelAndView("webrtc");

        List<Clothing> clothingList = clothingService.getAllClothingForString("price desc");

        mv.addObject("clothings", clothingList);

        return mv;

    }

    //删除clothing
    @RequestMapping(value = "/deleteClothing.do")
    public ModelAndView deleteClothing(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView();

        String price = request.getParameter("price");
        if (BaseUtil.stringIsNumber(price)) {
            clothingService.deleteClothingByPrice(Integer.parseInt(price));
            mv.setViewName("webrtc");
        } else {
            mv.setViewName("webrtc");
        }

        return mv;
    }

    //根据日期查询流水账
    @RequestMapping(value = "/getJournalAccoutByDate.do")
    public ModelAndView getJournalAccoutByDate(HttpServletResponse response, HttpServletRequest request) throws ParseException {

        ModelAndView mv = new ModelAndView("webrtc");

        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");

        Date startDate = BaseUtil.stringToDate(startTime, "start");
        Date endDate = BaseUtil.stringToDate(endTime, "end");
        List<JournalAccout> journalAccoutList = journalAccoutService.getJournalAccoutByDate(startDate, endDate);
        mv.addObject("journalAccoutList", journalAccoutList);

        return mv;
    }
}
