package edu.eci.arep;

import edu.eci.arep.back.controllerMovie;
import edu.eci.arep.back.cache;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BackTest {

    controllerMovie memory;
    cache Cache;

    @Before
    public void init() throws Exception{
        memory = new controllerMovie();
        memory.begin();
        Cache = memory.getMovieService();

    }

    /*@Test
    public void getMovie_WithoutCache() throws Exception {
        // ARRANGE
        String info = "{\"Title\":\"It\",\"Year\":\"2017\",\"Rated\":\"R\",\"Released\":\"08 Sep 2017\",\"Runtime\":\"135 min\",\"Genre\":\"Horror\",\"Director\":\"Andy Muschietti\",\"Writer\":\"Chase Palmer, Cary Joji Fukunaga, Gary Dauberman\",\"Actors\":\"Bill Skarsgård, Jaeden Martell, Finn Wolfhard\",\"Plot\":\"In the summer of 1989, a group of bullied kids band together to destroy a shape-shifting monster, which disguises itself as a clown and preys on the children of Derry, their small Maine town.\",\"Language\":\"English, Hebrew\",\"Country\":\"United States, Canada\",\"Awards\":\"10 wins & 46 nominations\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BZDVkZmI0YzAtNzdjYi00ZjhhLWE1ODEtMWMzMWMzNDA0NmQ4XkEyXkFqcGdeQXVyNzYzODM3Mzg@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"7.3/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"86%\"},{\"Source\":\"Metacritic\",\"Value\":\"69/100\"}],\"Metascore\":\"69\",\"imdbRating\":\"7.3\",\"imdbVotes\":\"576,995\",\"imdbID\":\"tt1396484\",\"Type\":\"movie\",\"DVD\":\"19 Dec 2017\",\"BoxOffice\":\"$328,874,981\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}";
        // ACT
        String searchedInfo = controllerMovie.getMovie("It");
        // ASSERT
        assertEquals(info, searchedInfo);
    }*/

    @Test
    public void getMovie_WithCache() throws Exception {
        // ARRANGE
        Cache.addMovie("It", "{\"Title\":\"It\",\"Year\":\"2017\",\"Rated\":\"R\",\"Released\":\"08 Sep 2017\",\"Runtime\":\"135 min\",\"Genre\":\"Horror\",\"Director\":\"Andy Muschietti\",\"Writer\":\"Chase Palmer, Cary Joji Fukunaga, Gary Dauberman\",\"Actors\":\"Bill Skarsgård, Jaeden Martell, Finn Wolfhard\",\"Plot\":\"In the summer of 1989, a group of bullied kids band together to destroy a shape-shifting monster, which disguises itself as a clown and preys on the children of Derry, their small Maine town.\",\"Language\":\"English, Hebrew\",\"Country\":\"United States, Canada\",\"Awards\":\"10 wins & 46 nominations\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BZDVkZmI0YzAtNzdjYi00ZjhhLWE1ODEtMWMzMWMzNDA0NmQ4XkEyXkFqcGdeQXVyNzYzODM3Mzg@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"7.3/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"86%\"},{\"Source\":\"Metacritic\",\"Value\":\"69/100\"}],\"Metascore\":\"69\",\"imdbRating\":\"7.3\",\"imdbVotes\":\"576,995\",\"imdbID\":\"tt1396484\",\"Type\":\"movie\",\"DVD\":\"19 Dec 2017\",\"BoxOffice\":\"$328,874,981\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}");
        String info = "{\"Title\":\"It\",\"Year\":\"2017\",\"Rated\":\"R\",\"Released\":\"08 Sep 2017\",\"Runtime\":\"135 min\",\"Genre\":\"Horror\",\"Director\":\"Andy Muschietti\",\"Writer\":\"Chase Palmer, Cary Joji Fukunaga, Gary Dauberman\",\"Actors\":\"Bill Skarsgård, Jaeden Martell, Finn Wolfhard\",\"Plot\":\"In the summer of 1989, a group of bullied kids band together to destroy a shape-shifting monster, which disguises itself as a clown and preys on the children of Derry, their small Maine town.\",\"Language\":\"English, Hebrew\",\"Country\":\"United States, Canada\",\"Awards\":\"10 wins & 46 nominations\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BZDVkZmI0YzAtNzdjYi00ZjhhLWE1ODEtMWMzMWMzNDA0NmQ4XkEyXkFqcGdeQXVyNzYzODM3Mzg@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"7.3/10\"},{\"Source\":\"Rotten Tomatoes\",\"Value\":\"86%\"},{\"Source\":\"Metacritic\",\"Value\":\"69/100\"}],\"Metascore\":\"69\",\"imdbRating\":\"7.3\",\"imdbVotes\":\"576,995\",\"imdbID\":\"tt1396484\",\"Type\":\"movie\",\"DVD\":\"19 Dec 2017\",\"BoxOffice\":\"$328,874,981\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}";
        // ACT
        String searchedInfo = controllerMovie.getMovie("It");
        // ASSERT
        assertEquals(info, searchedInfo);
    }



    @Test
    public void when_MovieDosntExits() throws Exception {
        // ARRANGE
        String info = "{\"Response\":\"False\",\"Error\":\"Movie not found!\"}";
        // ACT
        String searchedInfo = controllerMovie.getMovie("aavengers");
        // ASSERT
        assertEquals(info, searchedInfo);
    }


}
