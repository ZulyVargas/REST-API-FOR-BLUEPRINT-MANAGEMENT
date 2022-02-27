/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package edu.eci.arsw.blueprints.controllers;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
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
    public ResponseEntity<?> getBluePrintsInJSON() {
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(bps.getAllBlueprints(), HttpStatus.ACCEPTED);
        } catch (BlueprintPersistenceException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al consultar todos los planos", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{author}", method = RequestMethod.GET)
    public ResponseEntity<?> getBluePrintsByAuthorInJSON(@PathVariable("author") String author) {
        try {
            //obtener datos que se enviarán a través del API dado el autor
            Set<Blueprint> results = bps.getBlueprintsByAuthor(author);
            if (results.isEmpty()) {
                return new ResponseEntity<>("NO ENCONTRADO",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(results, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al consultar por autor", HttpStatus.NOT_FOUND);
        }
    }



    @RequestMapping(value = "/{author}/{bpname}", method = RequestMethod.GET)
    public ResponseEntity<?> getBluePrintsByAuthorAndNameInJSON(@PathVariable("author") String author, @PathVariable("bpname") String bpname) {
        try {
            //obtener datos que se enviarán a través del API dado el autor y nombre del plano
            Blueprint results = bps.getBlueprint(author, bpname);
            if (results == null) {
                return new ResponseEntity<>("NO ENCONTRADO", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(results, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al consultar por autor y nombre plano", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public ResponseEntity<?> addBluePrintsWithJSON(@RequestBody Blueprint blueprint) {
        try {
            //Enviar datos de creación para el plano
            bps.addNewBlueprint(blueprint);
            return new ResponseEntity<>("CREADO :D", HttpStatus.ACCEPTED);
        } catch (BlueprintPersistenceException be) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, be.getMessage(), be);
            return new ResponseEntity<>(be.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{author}/{bpname}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBluePrintsWithJSON(@PathVariable("author") String author, @PathVariable("bpname") String bpname, @RequestBody Blueprint blueprint) {
        try {
            //Enviar datos de creación para el plano
            List<Point> points = new ArrayList<>(blueprint.getPoints());
            bps.getBlueprint(author, bpname).setPoints(points);
            return new ResponseEntity<>("ACTUALIZADO :D", HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al actualizar el plano, no fue posible encontrarlo", HttpStatus.NOT_FOUND);
        }
    }

}