package org.mlxxiv.hashcodeonline;

import java.util.ArrayList;
import java.util.List;
public class Slide implements Comparable<Slide> {
    public List<String> tags = new ArrayList<>();
    public List<Photo> photos = new ArrayList<>();
    public int num;

    public Slide(Photo photo) {
        this.photos.add(photo);
        this.tags = photo.tags;
    }

    public Slide(Photo photo1, Photo photo2) {
        List<String> tags1 = new ArrayList();
        if (photo1.orientation.equals(Photo.VERTICAL)) {
            photos.add(photo1);
            tags1.addAll(photo1.tags);
        }

        List<String> tags2 = new ArrayList();
        if (photo2.orientation.equals(Photo.VERTICAL)) {
            photos.add(photo2);
            tags2.addAll(photo2.tags);
        }

        this.tags = SlideShow.tagsAggregated(tags1, tags2);
    }

    public String toString() {
        return (
                photos.size() == 2  ?
                        photos.get(0).id + " " + photos.get(1).id :
                        photos.get(0).id + ""
        );
    }


    public int compareTo(Slide that) {
        return this.tags.size() > that.tags.size() ? 1 : -1;
    }
}