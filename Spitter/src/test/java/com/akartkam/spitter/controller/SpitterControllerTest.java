package com.akartkam.spitter.controller;

import static java.util.Arrays.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import org.springframework.ui.ExtendedModelMap;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.akartkam.spitter.domain.Spitter;
import com.akartkam.spitter.domain.Spittle;
import com.akartkam.spitter.service.SpitterService;

@RunWith(MockitoJUnitRunner.class)
public class SpitterControllerTest {  
	
	
  @Mock
  private SpitterService spitterService;
  
  @Test
  public void shouldDisplaySpittlesForSpitter() {
	
	Spitter expectedSpitter = new Spitter();
	expectedSpitter.setUsername("testuser");
	when(spitterService.getSpitter("testuser")).thenReturn(expectedSpitter);
	
	Spittle spittle1 = new Spittle();
    spittle1.setText("ABC");
    Spittle spittle2 = new Spittle();
    spittle2.setText("XYZ");   
    List<Spittle> expectedSpittles = asList(spittle1, spittle2);
    
    //SpitterService spitterService = mock(SpitterService.class);//<co id="co_mockSpitterService"/>

    when(spitterService.getSpittlesForSpitter("testuser")).thenReturn(expectedSpittles);
    
    SpitterController controller = 
                   new SpitterController(spitterService); //<co id="co_createController"/>
    
    ExtendedModelMap model = new ExtendedModelMap();
    String viewName = controller.listSpittlesForSpitter("testuser", model); //<co id="co_callShowHomePage"/>
    
    assertEquals("spittles/list", viewName);
    assertSame(expectedSpitter, model.get("spitter"));
    assertSame(expectedSpittles, model.get("spittleList")); //<co id="co_assertResults"/>
    verify(spitterService).getSpitter("testuser");
    verify(spitterService).getSpittlesForSpitter("testuser");
    
  }
}
