
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */ 

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        return myMovies.size();
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
        
        for(Movie movie : myMovies) {
            String movieID = movie.getID();
            double value = getAverageByID(movieID, minimalRaters);
            if(value > 0.0) {
                Rating rating = new Rating(movieID, value);
                result.add(rating);
            }
        }
        return result;
    }   
    
    public String getTitle(String id) {
        String result = " was not found.";
        for(Movie movie : myMovies) {
            if(movie.getID().equals(id)) {
                return ("Movie with ID: " + id + " has title: " + movie.getTitle());
            }
        }
        return (id + result);
    }
    
    public String getID(String title) { 
        String result = " was not found.";
        for(Movie movie : myMovies) {
            if(movie.getTitle().equals(title)) {
                //return ("Movie with title: " + title + " has ID: " + movie.getID());
                return movie.getID();
            }
        }
        return (title + result);
    }
}