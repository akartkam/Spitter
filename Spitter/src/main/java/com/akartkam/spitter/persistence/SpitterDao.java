package com.akartkam.spitter.persistence;

import java.util.List;

import com.akartkam.spitter.domain.Spitter;
import com.akartkam.spitter.domain.Spittle;

public interface SpitterDao {
  void addSpitter(Spitter spitter);

  void saveSpitter(Spitter spitter);

  Spitter getSpitterById(long id);

  List<Spittle> getRecentSpittle();
  
  void saveSpittle(Spittle spittle);
  
  List<Spittle> getSpittlesForSpitter(Spitter spitter);

  Spitter getSpitterByUsername(String username);
  
  void deleteSpittle(long id);
  
  Spittle getSpittleById(long id);
  
  List<Spitter> findAllSpitters();
}
