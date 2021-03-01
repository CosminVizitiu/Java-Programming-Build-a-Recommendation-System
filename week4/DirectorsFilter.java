
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    String directors;
    
    public DirectorsFilter(String director) {
        directors = director;
    }
    
    @Override
    public boolean satisfies(String id) {
        String directorsSplit[] = directors.split(",");
        
        for(int i = 0; i < directorsSplit.length; i++) {
            if(MovieDatabase.getDirector(id).indexOf(directorsSplit[i]) != -1) {
                return true;
            }
        }
        return false;
    }
    
    
}
