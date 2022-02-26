package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Set;

public class Main {

    public static void main(String a[]) throws BlueprintPersistenceException, BlueprintNotFoundException {

        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = ac.getBean(BlueprintsServices.class);

        bps.addNewBlueprint(new Blueprint("zuly", "firstPrint"));
        bps.addNewBlueprint(new Blueprint("jose", "secondPrint", new Point[]{new Point(35,35), new Point(75,75)}));
        bps.addNewBlueprint(new Blueprint("zuly", "thirdPrint", new Point[]{new Point(23,23), new Point(46,35)}));
        System.out.println(bps.getAllBlueprints());
        System.out.println(bps.getBlueprintsByAuthor("zuly"));

        //redundancyFilter();
        subsampligFilter();
    }

    private static void subsampligFilter() throws BlueprintNotFoundException, BlueprintPersistenceException {
        //Filter Subsampling:
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = ac.getBean(BlueprintsServices.class);
        Blueprint bp0 = new Blueprint("mia", "firstPrint", new Point[]{new Point(40, 40),new Point(40, 40),new Point(44, 47),new Point(60, 70), new Point(80, 47),new Point(45, 47) });
        System.out.println("Before the subsampling filter " + bp0.toString() );
        for (Point p : bp0.getPoints() ){
            System.out.print("(" + p.getX() + "," + p.getY() + ")" + ",");
        }

        System.out.println();
        bps.addNewBlueprint(bp0);
        System.out.println("After the subsampling filter " +bps.getBlueprint("mia", "firstPrint").toString() );
        for (Point p : bp0.getPoints()){
            System.out.print("(" + p.getX() + "," + p.getY() + ")" + ",");
        }
    }

    private static void redundancyFilter() throws BlueprintPersistenceException, BlueprintNotFoundException {
        //Filter Redyndancy:
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = ac.getBean(BlueprintsServices.class);
        Blueprint bp0 = new Blueprint("jon", "firstPrint", new Point[]{new Point(40, 40),new Point(40, 40),new Point(44, 47),new Point(60, 70), new Point(80, 47),new Point(80, 47) });
        System.out.println("Before the redundancy filter " + bp0.toString() );
        for (Point p : bp0.getPoints() ){
            System.out.print("(" + p.getX() + "," + p.getY() + ")" + ",");
        }
        System.out.println();
        bps.addNewBlueprint(bp0);
        System.out.println("After the redundancy filter " +bps.getBlueprint("jon", "firstPrint").toString()  );
        for (Point p : bps.getBlueprint("jon", "firstPrint").getPoints()){
            System.out.print("(" + p.getX() + "," + p.getY() + ")" + ",");
        }
    }
}


