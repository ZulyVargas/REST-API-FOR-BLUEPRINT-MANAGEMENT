package edu.eci.arsw.blueprints.filters.impl;

import edu.eci.arsw.blueprints.filters.BlueprintsFilters;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//@Component
public class RedundancyFilter implements BlueprintsFilters {

    /**
     * Filters a point list by eliminating repeated consecutive items.
     * @param blueprint - Plane with the points to be filtered.
     * @return Plane with filtered points.
     */
    @Override
    public Blueprint filterBlueprint(Blueprint blueprint){
        List<Point> points = blueprint.getPoints();
        List<Point> filteredPoints = new ArrayList<>();
        for( int i = 0; i < points.size() - 1; i++ ){
            Point currentPoint = points.get(i);
            Point nextPoint = points.get(i+1);

            if( !(currentPoint.getX() == nextPoint.getX() && currentPoint.getY() == nextPoint.getY())){
                filteredPoints.add(currentPoint);
            }
            //Add the last
            if ( i ==  points.size() - 2) {
                filteredPoints.add(points.get(points.size() - 1));
            }
        }
        blueprint.setPoints(filteredPoints);
    return blueprint;
    }


    /**
     * Filter points from a list of plans .
     * @param blueprints - Planes with the points to be filtered.
     * @return Planes with filtered points.
     */
    @Override
    public Set<Blueprint> filterBlueprints(Set<Blueprint> blueprints) {
        for (Blueprint  bp : blueprints) {
            filterBlueprint(bp);
        }
        return blueprints;
    }
}
