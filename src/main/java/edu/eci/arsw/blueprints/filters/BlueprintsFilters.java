package edu.eci.arsw.blueprints.filters;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;

import java.util.Set;

public interface BlueprintsFilters {

    public Blueprint filterBlueprint(Blueprint blueprint);

    public Set<Blueprint> filterBlueprints(Set<Blueprint> blueprints);

}
