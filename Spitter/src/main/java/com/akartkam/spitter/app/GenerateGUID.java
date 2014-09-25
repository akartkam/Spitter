package com.akartkam.spitter.app;

import java.util.UUID;



public class GenerateGUID {
  
    public static final void main(String[] args) {
        //generate random UUIDs
    	UUID id1;
    	for (int i=0; i<100; i++) {
        id1 = UUID.randomUUID();
        System.out.println(id1);
    	}
    	

     }
}    
