package org.mlxxiv.hashcodeonline;

import java.util.*;

public class Solver2 {

    HashMap<String, List<Photo>> tagsMap = new HashMap<>();
    List<Photo> hor  = new ArrayList<>();
    List<Photo> vert = new ArrayList<>();
    List<Slide> slides = new ArrayList<>();


    public static void main(String[] args) {
        // Path to data file
        String dataFile = "input/hashcodeonline/d_pet_pictures.txt";

        // utility class, that will read and write data/results
        Io input = new Io(dataFile);

        Solver2 slideShow = new Solver2();
        slideShow.processPhotos(input.photos);

        // Resulting slideshow
        List<Slide> results = new ArrayList<>();
        int score = 0;

        HashSet<Slide> used = new HashSet<>();
        results.add(slideShow.slides.get(0));

        int i = 0;

        ArrayList<Slide> slideShowCopy = new ArrayList<>(slideShow.slides);
        for(Slide slide1: slideShow.slides) {
            slideShowCopy.remove(slide1);
            Slide bestMatch = null;
            int bestScore = 0;
            for (Slide slide2 : slideShowCopy) {
                if (slide1.equals(slide2) || used.contains(slide2)) {
                    continue;
                }
                int tmpScore = getScore(slide1.tags, slide2.tags);
                if (bestMatch == null || tmpScore > bestScore) {
                    bestMatch = slide2;
                    bestScore = tmpScore;
                }
            }
            used.add(slide1);

            slideShowCopy.remove(bestMatch);
            if (bestMatch != null) {
                used.add(bestMatch);
                results.add(bestMatch);
                score += bestScore;
            }
            System.out.println(i);
            i++;
            if (i%1000 == 0) {
                input.writeOutput(results);
                results = new ArrayList<>();
            }
        }

        for (Slide slide: slideShow.slides) {
            if (!results.contains(slide)) {
                results.add(slide);
            }
        }

        // start time to measure performance
        long startTime = System.currentTimeMillis();

        for (Slide slide: results) {
            System.out.println(slide);
        }
        input.writeOutput(results);

        System.out.println("total: score: " + score);
        System.out.println("Execution Time: " + (System.currentTimeMillis() - startTime) + " ms");
    }

    public static int getScore(List<String> list1, List<String> list2) {
        int intersect = intersection(list1, list2).size();
        int diff1 = diff(list1, list2).size();
        int diff2 = diff(list2, list1).size();

        int score;
        score = Math.min(intersect, diff1);
        score = Math.min(score, diff2);
        return score;
    }

    public void processPhotos(List<Photo> photos) {
        for(Photo photo: photos) {
            if (photo.orientation.equals(Photo.VERTICAL)) {
                vert.add(photo);
            } else {
                hor.add(photo);
                Slide slide = new Slide(photo);
                slides.add(slide);
            }
        }
        buildVerticalSlideSet();
//        this.slides.sort(Collections.reverseOrder());
    }

    /**
     * Try to combine very different vertical in order to get
     * as much as possible tags in the slide
     *
     * @return
     */
    public void buildVerticalSlideSet() {
        HashSet<Integer> used = new HashSet<>();
        for (Photo photo1: vert) {
            Photo best = null;
            int bestNum = 0;
            for (Photo photo2: vert) {
                if (photo1.id == photo2.id || used.contains(photo1.id) || used.contains(photo2.id)) {
                    continue;
                }
                int tagsAggregated = Solver2.tagsAggregated(photo1.tags, photo2.tags).size();
                if (best == null || tagsAggregated > bestNum) {
                    best = photo2;
                    bestNum = tagsAggregated;
                }

            }

            used.add(photo1.id);
            if (best != null) {
                used.add(best.id);
                Slide slide = new Slide(photo1, best);
                slides.add(slide);
            }
        }
    }

    public static List<String> tagsAggregated(List<String> tags1, List<String> tags2) {
        if (tags1.size() > tags2.size()) {
            for (String tag: tags2) {
                if (!tags1.contains(tag)) {
                    tags1.add(tag);
                }
            }
            return  tags1;
        } else {
            for (String tag: tags1) {
                if (!tags2.contains(tag)) {
                    tags2.add(tag);
                }
            }
            return  tags2;
        }
    }

    public static List<String> intersection(List<String> list1, List<String> list2) {
        List<String> intersection = new ArrayList<>();
        for (String s1 : list1) {
            if(list2.contains(s1)) {
                intersection.add(s1);
            }
        }
        return intersection;
    }

    public static List<String> diff(List<String> list1, List<String> list2) {
        List<String> diff = new ArrayList<>(list1);
        diff.removeAll(list2);
        return diff;

    }


}

