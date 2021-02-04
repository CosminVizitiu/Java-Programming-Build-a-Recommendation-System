
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {

    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters) {
        double average = 0.0;
        int contor=0;
        
        for(Rater rater : myRaters) {
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
    
        public boolean testAlreadyExistItem(ArrayList<Rating> rating, Rating testRating) {
        for(int i = 0; i < rating.size(); i++) {
                if(rating.get(i).getItem().equals(testRating.getItem())) {
                    return true;
                }
        }
        return false;
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
}
