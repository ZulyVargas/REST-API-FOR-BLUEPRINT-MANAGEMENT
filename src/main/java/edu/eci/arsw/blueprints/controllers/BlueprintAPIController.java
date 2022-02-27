/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package edu.eci.arsw.blueprints.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import static java.util.logging.Level.*;

/**
*
* @author hcadavid
*/
//@Service
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {

 @Autowired
 BlueprintsServices bps;


 @RequestMapping(method = RequestMethod.GET)
 public ResponseEntity<?> getBluePrintsInJSON(){
      try {
       //obtener datos que se enviarán a través del API
           return new ResponseEntity<>(bps.getAllBlueprints(), HttpStatus.ACCEPTED);
       } catch (Exception ex) {
           Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
           return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
       }
 }

 @RequestMapping( value = "/{author}" , method = RequestMethod.GET)
 public ResponseEntity<?> getBluePrintsByAuthorInJSON(@PathVariable("author") String author){
      try {
       //obtener datos que se enviarán a través del API dado el autor
           Set<Blueprint> results = bps.getBlueprintsByAuthor(author);
           if (results.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }
           return new ResponseEntity<>(results, HttpStatus.ACCEPTED);
      } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
  }
 }


@RequestMapping( value = "/{author}/{bpname}" , method = RequestMethod.GET)
public ResponseEntity<?> getBluePrintsByAuthorAndNameInJSON(@PathVariable("author") String author,@PathVariable("author") String bpname ){
    try {
        //obtener datos que se enviarán a través del API dado el autor
        Set<Blueprint> results = bps.getBlueprintsByAuthor(author);
        if (results.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(results.toArray()[0], HttpStatus.ACCEPTED);
    } catch (Exception ex) {
        Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
        return new ResponseEntity<>("Error bla bla bla",HttpStatus.NOT_FOUND);
    }
}





}


