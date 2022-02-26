package edu.eci.arsw.blueprints.filters.impl;

import edu.eci.arsw.blueprints.filters.BlueprintsFilters;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SubsamplingFilter implements BlueprintsFilters {
    /**
     * Filters a point list by eliminating 1 out of every 2 points.
     * @param blueprint - Plane with the points to be filtered.
     * @return Plane with filtered points.
     */
    @Override
    public Blueprint filterBlueprint(Blueprint blueprint) {
        List<Point> points = blueprint.getPoints();
        List<Point> filteredPoints = new ArrayList<>();
        for( int i = 0; i < points.size(); i = i+2 ){
            filteredPoints.add(points.get(i));
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
    public Set<Blueprint> filterBlueprints(Set<Blueprint> blueprints){
            for (Blueprint  bp : blueprints) {
                filterBlueprint(bp);
            }
            return blueprints;
    }

}
