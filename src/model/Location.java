package model;

public class Location {
    char name;
    char dependency;
    boolean visited;

    Location(char name, char dependency) {
        this.name = name;
        this.dependency = dependency;
    }

    @Override
    public String toString() {
        return String.valueOf(name);
    }
}
