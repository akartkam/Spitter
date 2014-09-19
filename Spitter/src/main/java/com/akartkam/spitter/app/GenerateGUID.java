package com.akartkam.spitter.app;

import java.util.UUID;

public class GenerateGUID {
  
    public static final void main(String[] args) {
        //generate random UUIDs
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        System.out.println(id1);
        System.out.println(id2);
    }
}
