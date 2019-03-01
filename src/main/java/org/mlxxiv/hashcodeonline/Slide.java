package org.mlxxiv.hashcodeonline;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

/**
 * Slide - sortable class, which will allow to compare first slides with more tags first
 */
public class Slide implements Comparable<Slide> {
    public final HashSet<String> tags;
    public final List<Photo> photos = new ArrayList<>();
    public final int size;

    public Slide(Photo photo) {
        this.photos.add(photo);
        this.tags = photo.tags;
        this.size = this.tags.size();
    }

    public Slide(Photo photo1, Photo photo2) {
        HashSet<String> tags1 = new HashSet();
        if (photo1.orientation.equals(Photo.VERTICAL)) {
            photos.add(photo1);
            tags1.addAll(photo1.tags);
        }

        HashSet<String> tags2 = new HashSet();
        if (photo2.orientation.equals(Photo.VERTICAL)) {
            photos.add(photo2);
            tags2.addAll(photo2.tags);
        }

        this.tags = SlideShow.tagsAggregated(tags1, tags2);
        this.size = this.tags.size();
    }

    public String toString() {
        return (
                photos.size() == 2  ?
                        photos.get(0).id + " " + photos.get(1).id :
                        photos.get(0).id + ""
        );
    }

    public boolean equals(Slide that) {
        return this.toString().equals(that.toString());
    }


    public int compareTo(Slide that) {
        if (this.size == that.size) {
            return 0;
        }
        return this.size > that.size ? 1 : -1;
    }
}