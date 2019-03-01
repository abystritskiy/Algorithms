package org.mlxxiv.hashcodeonline;

import java.util.HashSet;

/**
 * Photo
 */
public class Photo {
    public static final String VERTICAL = "V";
    public static final String HORIZONTAL = "C";

    public final int id;
    public final String orientation;
    public final HashSet<String> tags;

    /**
     * Constructor
     *
     * @param id
     * @param orientation
     * @param tags
     */
    public Photo(int id, String orientation, HashSet<String> tags) {
        this.id = id;
        this.orientation = orientation;
        this.tags = tags;
    }

    /**
     * To string (for debug only)
     * @return
     */
    public String toString() {
        return  id + " " + orientation + " " + tags.toString() ;
    }

}
