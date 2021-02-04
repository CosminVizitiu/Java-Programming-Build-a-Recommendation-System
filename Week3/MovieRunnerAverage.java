import java.util.*;

/**
 * Write a description of dsadsa here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {
    
    public void printAverageRatings() {
        SecondRatings sr = new SecondRatings("ratedmoviesfull", "ratings");
        System.out.println("Number of movies: " + sr.getMovieSize());
        System.out.println("Number of ratings: " + sr.getRaterSize());
        // result
        ArrayList<Rating>result = new ArrayList<Rating>();
        result = sr.getAverageRatings(12);
        Collections.sort(result);
        for(Rating rating : result) {
            System.out.println(rating.getValue() + " " + sr.getTitle(rating.getItem()));
        }
        System.out.println("Total number of results: " + result.size());
    }
    
    public void printGetAverageRatings() {
        SecondRatings sr = new SecondRatings("ratedmovies_short", "ratings_short");
        ArrayList<Rating>result = new ArrayList<Rating>();
        result = sr.getAverageRatings(3);
        for(int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
    
    public void printGetTitle() {
        SecondRatings sr = new SecondRatings("ratedmovies_short", "ratings");
        System.out.println(sr.getTitle("113277"));
    }
    
    public void printGetID() {
        SecondRatings sr = new SecondRatings("ratedmovies_short", "ratings");
        System.out.println(sr.getID("Heat"));
    }
    
    public void getAverageRatingOneMovie() {
        SecondRatings sr = new SecondRatings("ratedmoviesfull", "ratings");
        String movieName = "Vacation";
        String movieID = sr.getID(movieName);
        System.out.println("Average rating for " + movieName + " is " + sr.getAverageByID(movieID, 0));
    }
}
