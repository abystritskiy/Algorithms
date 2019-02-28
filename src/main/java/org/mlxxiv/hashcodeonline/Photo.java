package org.mlxxiv.hashcodeonline;

import java.util.HashSet;
import java.util.List;

public class Photo {
    public static final String VERTICAL = "V";
    public static final String HORIZONTAL = "C";

    public final int id;
    public final String orientation;
    public final List<String> tags;


    public Photo(int id, String orientation, List<String> tags) {
        this.id = id;
        this.orientation = orientation;
        this.tags = tags;

    }

    public String toString() {
        return  id + " " + orientation + " " + tags.toString() ;
    }

}
