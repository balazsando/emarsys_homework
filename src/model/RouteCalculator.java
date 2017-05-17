package model;

import exception.CircleException;

import java.util.*;
import java.util.stream.Collectors;

public class RouteCalculator {
    private List<Location> locations;

    public RouteCalculator() {
        locations = new ArrayList<>();
    }

    public void addLocation(String location) {
        String[] input = location.split(" ");
        char dependency = input.length > 2 ? input[2].charAt(0) : '\u0000';
        char name = location.charAt(0);
        Optional<Location> match = locations.stream().filter(l -> l.name == name).findFirst();
        if (match.isPresent()) {
            match.get().dependency = dependency;
        } else {
            locations.add(new Location(name, dependency));
        }
    }

    public String calculate() {
        List<Location> route = new LinkedList<>();
        locations.forEach(l -> l.visited = false);
        while (firstWithDependency() != null) {
            try {
                route.addAll(followDependency(firstWithDependency()));
            } catch (CircleException e) {
                return e.getMessage();
            }
        }
        route.addAll(locations.stream().filter(l -> !l.visited).collect(Collectors.toList()));
        return "Route: " + route.stream().map(Object::toString).collect(Collectors.joining(", "));
    }

    private Location firstWithDependency() {
        return locations.stream().filter(l -> l.dependency != '\u0000' && !l.visited).findFirst().orElse(null);
    }

    private Location findDependency(Location location) {
        return locations.stream().filter(l -> l.name == location.dependency && !l.visited).findFirst().orElse(null);
    }

    private List<Location> followDependency(Location location) throws CircleException {
        List<Location> subRoute = new LinkedList<>();
        Location destination = location;
        while(destination != null){
            subRoute.add(0, destination);
            destination = findDependency(destination);
            if (destination != null && subRoute.contains(destination)) throw new CircleException("Invalid route");
        }
        subRoute.forEach(l -> l.visited = true);
        return subRoute;
    }
}
