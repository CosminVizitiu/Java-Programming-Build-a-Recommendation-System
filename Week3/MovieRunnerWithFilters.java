
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        ThirdRatings tr = new ThirdRatings("ratings_short");
        
        MovieDatabase.initialize("ratedmovies_short");
        
        System.out.println("Number of ratings: " + tr.getRaterSize());
        System.out.println("number of movies: " + MovieDatabase.size());
        
        // Test print AverageRatings - showing movies with rating and total number of movies
        int minNumbOfRaters = 1;
        ArrayList<Rating> result = tr.getAverageRatings(minNumbOfRaters);
        
        System.out.println("Total number of movies with " + minNumbOfRaters + " raters or above: " + result.size());
        
        Collections.sort(result);
        
        for(Rating rating : result) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));   // rating - rating.getValue(), titlu - rating.getItem()  
        }
        
    }
    
    public void printAverageRatingsByYear() {
         
         ThirdRatings tr = new ThirdRatings("ratings_short");
         MovieDatabase.initialize("ratedmovie_short");
         
        System.out.println("Number of ratings: " + tr.getRaterSize());
        System.out.println("number of movies: " + MovieDatabase.size());
         
        int year = 2000;
        int minNumbOfRaters = 1;
        YearAfterFilter yaf = new YearAfterFilter(year);
        
        ArrayList<Rating> result = tr.getAverageRatingsByFilter(minNumbOfRaters, yaf);
        System.out.println("Found: " + result.size() + " movies. ");
        
        Collections.sort(result);
        
        for(Rating rating : result) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " " +MovieDatabase.getTitle(rating.getItem()));   // rating - rating.getValue(), titlu - rating.getItem())
        }
    }
    
    public void printAverageRatingsByGenre() {
        ThirdRatings tr = new ThirdRatings("ratings_short");
        MovieDatabase.initialize("ratedmovies_short");
        String genre = "Crime";
        int minNumbOfRaters = 1;
        
        System.out.println("Number of ratings: " + tr.getRaterSize());
        System.out.println("number of movies: " + MovieDatabase.size());
        
        GenreFilter gf = new GenreFilter(genre);
        ArrayList<Rating> result = tr.getAverageRatingsByFilter(minNumbOfRaters, gf);
        
        System.out.println("Found: " + result.size() + " movies. ");
                
        Collections.sort(result);
        
        for(Rating rating : result) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println(MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes() {
        ThirdRatings tr  = new ThirdRatings("ratings_short");
        MovieDatabase.initialize("ratedmovies_short");
        
        int minMinutes = 110;
        int maxMinutes = 170;
        int minNumbOfRaters = 1;
        
        System.out.println("Number of ratings: " + tr.getRaterSize());
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        MinutesFilter mf = new MinutesFilter(minMinutes, maxMinutes);
        ArrayList<Rating> result = tr.getAverageRatingsByFilter(minNumbOfRaters, mf);
        
        System.out.println("Found: " + result.size() + " movies. ");
        
        Collections.sort(result);
        
        for(Rating rating : result) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getMinutes(rating.getItem()) + " "
            + MovieDatabase.getTitle(rating.getItem()));
        }
        
    }
    
    public void printAverageRatingsByDirectors() {
        ThirdRatings tr  = new ThirdRatings("ratings_short");
        MovieDatabase.initialize("ratedmovies_short");
        
        String directors = "Charles Chaplin,Michael Mann,Spike Jonze";
        
        int minNumbOfRaters = 1;
        
        System.out.println("Number of ratings: " + tr.getRaterSize());
        System.out.println("Number of movies: " + MovieDatabase.size());
        
        DirectorsFilter df = new DirectorsFilter(directors);
        ArrayList<Rating> result = tr.getAverageRatingsByFilter(minNumbOfRaters, df);
                
        System.out.println("Found: " + result.size() + " movies. ");
        
        Collections.sort(result);
        
        for(Rating rating : result) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println(MovieDatabase.getDirector(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings tr = new ThirdRatings("ratings_short");
        MovieDatabase.initialize("ratedmovies_short");
        int minNumbOfRaters = 1;
        
        System.out.println("Number of ratings: " + tr.getRaterSize());
        System.out.println("number of movies: " + MovieDatabase.size());
        
        int year = 1980;
        YearAfterFilter yaf = new YearAfterFilter(year);
        
        String genre = "Romance";
        GenreFilter gf = new GenreFilter(genre);
        
        AllFilters af = new AllFilters();
        af.addFilter(yaf);
        af.addFilter(gf);
        
        ArrayList<Rating> result = tr.getAverageRatingsByFilter(minNumbOfRaters, af);
        System.out.println("Found: " + result.size() + " movies. ");
        
        Collections.sort(result);
        
        for (Rating rating : result) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem())
            + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println(MovieDatabase.getGenres(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings tr = new ThirdRatings("ratings_short");
        MovieDatabase.initialize("ratedmovies_short");
        int minNumbOfRaters = 1;
        
        System.out.println("Number of ratings: " + tr.getRaterSize());
        System.out.println("number of movies: " + MovieDatabase.size());
        
        int minMinutes = 30;
        int maxMinutes = 170;
        MinutesFilter mf = new MinutesFilter(minMinutes, maxMinutes);
        
        String directors = "Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola";
        DirectorsFilter df = new DirectorsFilter(directors);
        
        AllFilters af = new AllFilters();
        af.addFilter(mf);
        af.addFilter(df);
        
        ArrayList<Rating> result = tr.getAverageRatingsByFilter(minNumbOfRaters, af);
        System.out.println("Found: " + result.size() + " movies. ");
        
        Collections.sort(result);
        
        for (Rating rating : result) {
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem())
            + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println(MovieDatabase.getDirector(rating.getItem()));
        }
    }
}
