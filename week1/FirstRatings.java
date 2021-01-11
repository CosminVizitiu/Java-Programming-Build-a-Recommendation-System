
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    public ArrayList<Movie> loadMovies (String filename) {
        ArrayList<Movie> result = new ArrayList<Movie>();
        FileResource fr = new FileResource("data/" + filename + ".csv" );
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record:parser) {
            String currentID = record.get(0);
            System.out.println(currentID);
            String currentTitle = record.get(1);
            String currentYear = record.get(2);
            String currentCountry = record.get(3);
            String currentGenre = record.get(4);
            String currentDirector = record.get(5);
            int currentMinutes = Integer.parseInt(record.get(6));
            String currentPoster = record.get(7);
            Movie currentMovie = new Movie(currentID, currentTitle, currentYear, currentGenre, currentDirector, 
            currentCountry, currentPoster, currentMinutes);
            
            result.add(currentMovie);
        }
        
        return result;
    }
    
    public void testLoadMovies() {
        String filename = "ratedmoviesfull";
        ArrayList<Movie> movies = loadMovies(filename);
        System.out.println("Number of movies: " + movies.size());
        //System.out.println(movies.toString());
        
        String genre = "Comedy";
        int genreNum = 0;
        for(Movie movie : movies) {
            if(movie.getGenres().indexOf(genre) != -1)
            genreNum++;
        }
        System.out.println(genre + " movies: " + genreNum);
        
        int minutes = 150;
        int minutesNum = 0;
        for(Movie movie : movies) {
            if(movie.getMinutes() > minutes)
            minutesNum++;
        }
        System.out.println(" movies with minutes over " + minutes + ": " + minutesNum);
        
        HashMap<String, Integer> resultDirector = new HashMap<String, Integer>();
        for(Movie movie: movies) {
            String[] directors = movie.getDirector().split(",");
            
            for(String director : directors) {
                director = director.trim();
                if(! resultDirector.containsKey(director)) {
                    resultDirector.put(director, 1);
                } else {
                    resultDirector.put(director, resultDirector.get(director) + 1);
                }
            }
        }
        
        System.out.println(resultDirector);
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> result = new ArrayList<Rater>();
        ArrayList<String> listOfIDs = new ArrayList<String>();
        
        FileResource fr = new FileResource("data/" + filename + ".csv" );
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record:parser) {
            String currentRaterID = record.get(0);
            String imbdID = record.get(1);
            double currentMovieRating = Double.parseDouble(record.get(2));
            if(! listOfIDs.contains(currentRaterID)) {
                Rater currentRater = new Rater(currentRaterID);
                result.add(currentRater);
                currentRater.addRating(imbdID, currentMovieRating);
            } else {
                for(int k=0; k < result.size(); k++) {
                    if(result.get(k).getID().equals(currentRaterID)) {
                        result.get(k).addRating(imbdID, currentMovieRating);
                    }
                }
            }
            listOfIDs.add(currentRaterID);
        }
        
        return result;
    }
    
    void testLoadRaters() {
        String filename = "ratings";
        ArrayList<Rater> result = loadRaters(filename);
        //System.out.println("Total number of raters: " + result.size());
        
        HashMap<String, HashMap<String, Double>> hashmap = new HashMap<String, HashMap<String, Double>>();
        for(Rater rater:result) {
            HashMap<String, Double> ratings = new HashMap<String, Double>();
            ArrayList<String> itemsRated = rater.getItemsRated();
            for(int i=0; i < itemsRated.size(); i++) {
                String movieID = itemsRated.get(i);
                double movieRating = rater.getRating(movieID);
                ratings.put(movieID, movieRating);
            }               
            hashmap.put(rater.getID(), ratings);
        }
        
        //Print all Raters + rater info
        
        /*for(String i : hashmap.keySet()) {
           HashMap<String, Double> functionResult = hashmap.get(i);
           System.out.println(i + " - " + functionResult.size());
           for(String j : functionResult.keySet()) {
               System.out.println(j + " - " + functionResult.get(j));
            }
        }*/  

        // number of Ratings
        String rater_id = "193";
        System.out.println(hashmap.get(rater_id).size());
        
        // maximum number of Ratings
        
        int max = Integer.MIN_VALUE;
        for(String i : hashmap.keySet()) {
            HashMap<String, Double> functionResult = hashmap.get(i);
            if(functionResult.size() > max) {
                max = functionResult.size();
            }
        }
            String resultt = "";
        for(String i : hashmap.keySet()) {
            HashMap<String, Double> functionResult = hashmap.get(i);
            if(functionResult.size() == max) {
                System.out.println(i + " - " + functionResult.size());
                for(String j : functionResult.keySet()) {
                    System.out.println(j + " - " + functionResult.get(j));
                }
                resultt = i;
            }
        }
        
       System.out.println("Max number of ratings " + max);
       System.out.println("Rater with max number of ratings: " + resultt);
       
       // number of ratings for a particular movie
       
       int contor = 0;
       String ratingNumb = "1798709";
       for(String i : hashmap.keySet()) {
           HashMap<String, Double> functionResult = hashmap.get(i);
           if(functionResult.containsKey(ratingNumb)) {
               contor++;
            }
        }
       System.out.println("Number of raters for " + ratingNumb + " is " + contor);
       
       
      
      ArrayList<String> movies = new ArrayList<String>();
      for(String i : hashmap.keySet()) {
          HashMap<String, Double> functionResult = hashmap.get(i);
          for(String j : functionResult.keySet()) {
              if(!movies.contains(j)) {
                  movies.add(j);
                }
            }
        }
      System.out.println("Number of unique movies: " + movies.size());
    }
    }
    