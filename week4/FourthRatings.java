
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings {

    public FourthRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public FourthRatings(String ratingsfile) {
        RaterDatabase.initialize(ratingsfile);
    }
    
    public double getAverageByID(String id, int minimalRaters) {
        double average = 0.0;
        int contor=0;
        
        for(Rater rater : RaterDatabase.getRaters()) {
            if(rater.hasRating(id)) {
                average+=rater.getRating(id);
                contor++;
            }
        }
        
        if(contor >= minimalRaters) {
            return average/contor;
        }
        else {
            return 0.0;
        }
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        for(String movieID : movies) {
            double value = getAverageByID(movieID, minimalRaters);
            if(value > 0.0) {
                Rating rating = new Rating(movieID, value);
                result.add(rating);
            }
        }
        
        return result;
    }   
    
    public ArrayList<Rating> getAverageRatingsByFilter (int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<String> moviesID = MovieDatabase.filterBy(filterCriteria);
        
        for(String movieID : moviesID) {
            double average = getAverageByID(movieID, minimalRaters);
            if(average > 0.0) {
                Rating rating = new Rating(movieID, average);
                result.add(rating);
            }
        }
        
        return result;
    }
    
    private double dotProduct (Rater me, Rater r) {
        double result = 0.0;
        ArrayList<String> itemsRatedMe = me.getItemsRated();
        
        for (String item : itemsRatedMe) {
            if (r.getItemsRated().contains(item)) {
                double ratingR = r.getRating(item);
                double ratingMe = me.getRating(item);
                
                result += (ratingR - 5.0) * (ratingMe - 5.0);
            }
        }
        return result;
    }
    
    private ArrayList<Rating> getSimilarities (String id) {
        ArrayList<Rating> similarities = new ArrayList<Rating> ();
        Rater me = RaterDatabase.getRater(id);
        
        for (Rater currRater : RaterDatabase.getRaters()) {
            if (! currRater.getID().equals(id)) {
               double dotProduct = dotProduct(me, currRater);
               if (dotProduct >= 0) {
                   similarities.add(new Rating(currRater.getID(), dotProduct));
               }
            }
        }
        
        Collections.sort(similarities, Collections.reverseOrder());
        return similarities;
    }
    
    public ArrayList<Rating> getSimilarRatings (String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> weightedRatings = new ArrayList<Rating> ();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        HashMap<String,Double> accumulatedRating = new HashMap<String,Double> ();
        HashMap<String,Integer> accumulatedCount = new HashMap<String,Integer> ();
        
        for (String movieID : movies) {
            double currRating = 0.0;
            int currCount = 0;
            
            for (int k=0; k < numSimilarRaters; k++) {
                Rating r = similarRaters.get(k);
                String raterID = r.getItem();
                double weight = r.getValue();
                
                Rater rater = RaterDatabase.getRater(raterID);
                
                if (rater.hasRating(movieID)) {
                    double rating = rater.getRating(movieID) * weight;
                    currRating += rating;
                    currCount += 1;
                }
            }
            
            if (currCount >= minimalRaters) {
                accumulatedRating.put(movieID, currRating);
                accumulatedCount.put(movieID, currCount);
            }
        }
        
        for (String movieID : accumulatedRating.keySet()) {
            double weightedRating = Math.round((accumulatedRating.get(movieID) / accumulatedCount.get(movieID)) * 100.0) / 100.0;
            Rating rating = new Rating (movieID, weightedRating);
            weightedRatings.add(rating);
        }
        
        Collections.sort(weightedRatings, Collections.reverseOrder());
        return weightedRatings;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter 
    (String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> weightedRatings = new ArrayList<Rating> ();
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<String> filteredMovies = MovieDatabase.filterBy(filterCriteria);
        
        HashMap<String,Double> accumulatedRating = new HashMap<String,Double> ();
        HashMap<String,Integer> accumulatedCount = new HashMap<String,Integer> ();
        
        for (String movieID : filteredMovies) {
            double currRating = 0.0;
            int currCount = 0;
            
            for (int k=0; k < numSimilarRaters; k++) {
                Rating r = similarRaters.get(k);
                String raterID = r.getItem();
                double weight = r.getValue();
                
                Rater rater = RaterDatabase.getRater(raterID);
                
                if (rater.hasRating(movieID)) {
                    double rating = rater.getRating(movieID) * weight;
                    currRating += rating;
                    currCount += 1;
                }
            }
            
            if (currCount >= minimalRaters) {
                accumulatedRating.put(movieID, currRating);
                accumulatedCount.put(movieID, currCount);
            }
        }
        
        for (String movieID : accumulatedRating.keySet()) {
            double weightedRating = Math.round((accumulatedRating.get(movieID) / accumulatedCount.get(movieID)) * 100.0) / 100.0;
            Rating rating = new Rating (movieID, weightedRating);
            weightedRatings.add(rating);
        }
        
        Collections.sort(weightedRatings, Collections.reverseOrder());
        return weightedRatings;
    }
}
