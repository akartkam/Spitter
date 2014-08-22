package com.akartkam.spitter.controller;

import static com.akartkam.spitter.controller.HomeController.*;
import static java.util.Arrays.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.InjectMocks;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.akartkam.spitter.domain.Spittle;
import com.akartkam.spitter.service.SpitterService;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {  
	
	
  @Mock
  private SpitterService spitterService;
  
  @Test
  public void shouldDisplayRecentSpittles() {
    List<Spittle> expectedSpittles = 
      asList(new Spittle(), new Spittle(), new Spittle());
    
    //SpitterService spitterService = mock(SpitterService.class);//<co id="co_mockSpitterService"/>

    when(spitterService.getRecentSpittles(DEFAULT_SPITTLES_PER_PAGE)).
        thenReturn(expectedSpittles);
    
    HomeController controller = 
                   new HomeController(spitterService); //<co id="co_createController"/>
    
    HashMap<String, Object> model = new HashMap<String, Object>();
    String viewName = controller.showHomePage(model); //<co id="co_callShowHomePage"/>
    
    assertEquals("home", viewName);
    
    assertSame(expectedSpittles, model.get("spittles")); //<co id="co_assertResults"/>
    verify(spitterService).getRecentSpittles(DEFAULT_SPITTLES_PER_PAGE);
    


    
  }
}
