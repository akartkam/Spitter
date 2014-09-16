package com.akartkam.spitter.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.akartkam.spitter.service.SpitterService;
import com.akartkam.spitter.domain.Spitter;

@Controller
public class HomeController {

  private SpitterService spitterService;

  @Inject
  public HomeController(SpitterService spitterService) {
    this.spitterService = spitterService;
  }
  
  @RequestMapping(value={"/","/home"}, method=RequestMethod.GET)
  public String showHomePage(Map<String, Object> model) {
    model.put("spittles", 
              spitterService.getRecentSpittles(spittlesPerPage));
    return "home";
  }
  
  @RequestMapping(value = "/downloadSpittersWithSpittles.xls", method = RequestMethod.GET)
  public ModelAndView downloadExcel() {
      // create some sample data
      List<Spitter> listSpitters = new ArrayList<Spitter>();
      listSpitters = spitterService.getAllSpittersWithSpittles();
      // return a view which will be resolved by an excel view resolver
      return new ModelAndView("spitterListExcelView", "listSpitters", listSpitters);
  }


  //<start id="spittlesPerPage"/> 
  public static final int DEFAULT_SPITTLES_PER_PAGE = 25;
  
  private int spittlesPerPage = DEFAULT_SPITTLES_PER_PAGE;
  
  public void setSpittlesPerPage(int spittlesPerPage) {
    this.spittlesPerPage = spittlesPerPage;
  }
  
  public int getSpittlesPerPage() {
    return spittlesPerPage;
  }
  //<end id="spittlesPerPage"/> 
}
