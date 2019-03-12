package org.mlxxiv.hashcodeonline;

import java.util.*;
import java.util.concurrent.Phaser;

public class SlideShow {
    List<Photo> horizontal = new ArrayList<>();
    private List<Photo> vertical = new ArrayList<>();
    List<Slide> slides = new ArrayList<>();


    public static void main(String[] args) {
        String[] dataFiles = new String[]{
                "input/hashcodeonline/a_example.txt",
                "input/hashcodeonline/b_lovely_landscapes.txt",
                "input/hashcodeonline/c_memorable_moments.txt",
                "input/hashcodeonline/d_pet_pictures.txt",
                "input/hashcodeonline/e_shiny_selfies.txt"
        };


        String datafile = "input/hashcodeonline/b_lovely_landscapes.txt";
        Io input = new Io(datafile);
        int chunkSize = 1000;

        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("CPU cores: " + processors);
        System.out.println("\n");

        SlideShow.solverSequential(input.photos, chunkSize);
        System.out.println("=======================\n");
        SlideShow.solverParallel(input.photos, chunkSize);
    }

    public static void solverParallel(List<Photo> photos, int chunkSize) {

        long startTime = System.currentTimeMillis();

        List<Slide> results = new ArrayList<>();
        int score ;
        int chunks = (photos.size() + chunkSize + 1) / chunkSize;

        Phaser ph = new Phaser(0);
        ph.bulkRegister(chunks);

        Thread[] threads = new Thread[chunks];
        int[] threadScores = new int[chunks];

        for (int j = 0; j < chunks ; j++) {
            int cycle = j;
            threads[j] = new Thread(() -> {
            SlideShow slideShow = new SlideShow();
            slideShow.processPhotos(photos, cycle*chunkSize, chunkSize);

            int threadBestScore = 0;

            // combine vertical photos into slides
            slideShow.buildVerticalSlideSet();

            if (slideShow.slides.isEmpty()) {
                    return;
            }
            // sort reverse - the more tags 2 slides have, the higher is possible score
            slideShow.slides.sort(Collections.reverseOrder());

            HashSet<Slide> used = new HashSet<>();
            Slide slide1 = slideShow.slides.get(0);

            if(!results.isEmpty()) {
                threadScores[cycle] = getScore(results.get(results.size()-1).tags, slide1.tags);
            }

            results.add(slide1);
            used.add(slide1);

            while(used.size() < slideShow.slides.size()-1) {
                Slide slide2 = null;
                int bestScore = 0;
                for (Slide slide : slideShow.slides) {
                    if (slide1.equals(slide) || used.contains(slide)) {
                        continue;
                    }
                    int tmpScore = getScore(slide1.tags, slide.tags);
                    if (slide2 == null || tmpScore > bestScore) {
                        slide2 = slide;
                        bestScore = tmpScore;
                    }
                }


                if (slide2 != null) {
                    used.add(slide2);
                    results.add(slide2);
                    threadBestScore += bestScore;
                    slide1 = slide2;
                } else {
                    break;
                }

            }

            for (Slide slide: slideShow.slides) {
                if (!results.contains(slide)) {
                    results.add(slide);
                }
            }

            threadScores[cycle] = threadBestScore;
            });

            threads[j].start();
        }

        for (int j = 0; j < chunks; j++) {
            try {
                threads[j].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        score = Arrays.stream(threadScores).sum();

        System.out.printf("Parallel Total Score: %d\n", score);
        System.out.printf("Parallel Execution Time: %d ms\n\n", (System.currentTimeMillis() - startTime));
    }

    public static void solverSequential(List<Photo> photos, int chunkSize) {
        long startTime = System.currentTimeMillis();

        List<Slide> results = new ArrayList<>();
        int score;


        int chunks = (photos.size() + chunkSize + 1) / chunkSize;

        Phaser ph = new Phaser(0);
        ph.bulkRegister(chunks);

        int[] scores = new int[chunks];

        for (int j = 0; j < chunks ; j++) {
            SlideShow slideShow = new SlideShow();
            slideShow.processPhotos(photos, j*chunkSize, chunkSize);

            int threadBestScore = 0;

            // combine vertical photos into slides
            slideShow.buildVerticalSlideSet();

            if (slideShow.slides.isEmpty()) {
                continue;
            }
            // sort reverse - the more tags 2 slides have, the higher is possible score
            slideShow.slides.sort(Collections.reverseOrder());

            HashSet<Slide> used = new HashSet<>();
            Slide slide1 = slideShow.slides.get(0);

            if(!results.isEmpty()) {
                scores[j] = getScore(results.get(results.size()-1).tags, slide1.tags);
            }

            results.add(slide1);
            used.add(slide1);

            while(used.size() < slideShow.slides.size()-1) {
                Slide slide2 = null;
                int bestScore = 0;
                for (Slide slide : slideShow.slides) {
                    if (slide1.equals(slide) || used.contains(slide)) {
                        continue;
                    }
                    int tmpScore = getScore(slide1.tags, slide.tags);
                    if (slide2 == null || tmpScore > bestScore) {
                        slide2 = slide;
                        bestScore = tmpScore;
                    }
                }


                if (slide2 != null) {
                    used.add(slide2);
                    results.add(slide2);
                    threadBestScore += bestScore;
                    slide1 = slide2;
                } else {
                    break;
                }

            }

            for (Slide slide: slideShow.slides) {
                if (!results.contains(slide)) {
                    results.add(slide);
                }
            }

            scores[j] = threadBestScore;

        }

        score = Arrays.stream(scores).sum();

        System.out.printf("Sequential Total Score: %d\n", score);
        System.out.printf("Sequential Execution Time: %d ms\n\n", (System.currentTimeMillis() - startTime));
    }

    /**
     * Get the score between 2 slides - no sense to continue once of the parameters is 0
     *
     * @param list1 tags from the first slide
     * @param list2 tags from the second slide
     * @return
     */
    public static int getScore(HashSet<String> list1, HashSet<String> list2) {
        int intersect = intersection(list1, list2).size();
        if (intersect == 0 || intersect == list1.size() || intersect == list2.size()) return 0;

        int diff1 = diff(list1, list2).size();
        if (diff1 == 0) return 0;

        int diff2 = diff(list2, list1).size();
        if (diff2 == 0) return 0;

        int score;
        score = Math.min(intersect, diff1);
        score = Math.min(score, diff2);
        return score;
    }

    /**
     * Sort photos by orientation (vertical/horizontal)
     * convert horizontal into slides.
     *
     * @param photos list of the all photos
     * @param start start position
     * @param size number of the photos to process
     */
    public void processPhotos(List<Photo> photos, int start, int size) {
        for (int i = start; i < start + size; i++) {
            if (i>= photos.size()) break;

            Photo photo = photos.get(i);

            if (photo.orientation.equals(Photo.VERTICAL)) {
                vertical.add(photo);
            } else {
                horizontal.add(photo);
                Slide slide = new Slide(photo);
                slides.add(slide);
            }
        }

    }

    /**
     * Try to combine very different vertical in order to get
     * as much as possible tags in the slide
     *
     * @return
     */
    public void buildVerticalSlideSet() {
        HashSet<Integer> used = new HashSet<>();
        for (Photo photo1: vertical) {
            Photo best = null;
            int bestNum = 0;
            for (Photo photo2: vertical) {
                if (photo1.id == photo2.id || used.contains(photo1.id) || used.contains(photo2.id)) {
                    continue;
                }
                int tagsAggregated = SlideShow.tagsAggregated(photo1.tags, photo2.tags).size();
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
    }    /**
     * Try to combine very different vertical in order to get
     * as much as possible tags in the slide
     *
     * @return
     */
    public void buildVerticalSlideSet(boolean random) {
        HashSet<Integer> used = new HashSet<>();
        List<Photo> unUsed  = new ArrayList<>(vertical);

        long seed = System.nanoTime();
        Collections.shuffle(unUsed, new Random(seed));
        for (int i = 0; i < unUsed.size(); i += 2) {
            if (i+1 < unUsed.size()) {
                Slide slide = new Slide(unUsed.get(i), unUsed.get(i+1));
                slides.add(slide);
            }
        }
    }



    /**
     * Find aggregated tags set
     *
     * @param tags1
     * @param tags2
     * @return
     */
    public static HashSet<String> tagsAggregated(HashSet<String> tags1, HashSet<String> tags2) {
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

    /**
     * Find common tags
     * @param tags1
     * @param tags2
     * @return
     */
    public static HashSet<String> intersection(HashSet<String> tags1, HashSet<String> tags2) {
        HashSet<String> intersection = new HashSet<>();
        for (String s1 : tags1) {
            if(tags2.contains(s1)) {
                intersection.add(s1);
            }
        }
        return intersection;
    }

    /**
     * Find tags that present in first set but not in the second
     * @param tags1
     * @param tags2
     * @return
     */
    public static HashSet<String> diff(HashSet<String> tags1, HashSet<String> tags2) {
        HashSet<String> diff = new HashSet<>(tags1);
        diff.removeAll(tags2);
        return diff;

    }
}
