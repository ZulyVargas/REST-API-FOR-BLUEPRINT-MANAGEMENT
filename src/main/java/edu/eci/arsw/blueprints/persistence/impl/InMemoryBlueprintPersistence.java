/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author hcadavid
 */
@Component
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {

        //load stub data
        Point[] pts=new Point[]{new Point(14, 140),new Point(123, 199)};
        Blueprint bp=new Blueprint("jose", "casa",pts);

        Point[] pts1=new Point[]{new Point(154, 140),new Point(115, 115)};
        Blueprint bp1=new Blueprint("zuly", "plano1 ",pts1);

        Point[] pts2=new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp2=new Blueprint("david", "carro",pts2);

        Point[] pts3=new Point[]{new Point(456, 456),new Point(324, 324)};
        Blueprint bp3=new Blueprint("valentina", "plano2",pts3);

        Point[] pts4=new Point[]{new Point(834, 834),new Point(63, 63)};
        Blueprint bp4=new Blueprint("valentina", "plano2",pts4);

        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        blueprints.put(new Tuple<>(bp1.getAuthor(),bp1.getName()), bp1);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);
        blueprints.put(new Tuple<>(bp4.getAuthor(),bp4.getName()), bp4);
        
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{

        Set<Blueprint> res = new HashSet<Blueprint>();
        for (Tuple<String,String> key : blueprints.keySet()){
            if (key.getElem1().equals(author)){ res.add(blueprints.get(key)); }
        }
        return res;
    }

    @Override
    public Set<Blueprint> getAllBlueprints(){
        Set<Blueprint> res = new HashSet<Blueprint>();
        for (Tuple<String,String> key : blueprints.keySet()){
            res.add(blueprints.get(key));
        }
        return res;
    }
    
}
