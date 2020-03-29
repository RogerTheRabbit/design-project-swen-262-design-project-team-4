package com.company.Database;

import com.company.FileIO.FileParser;
import com.company.RequestInterpreter.Requests.Response;
import com.company.SearchableFactory.ArtistFactory;
import com.company.SearchableFactory.ReleaseFactory;
import com.company.SearchableFactory.SongFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author mjh9131
 *
 *         contains all the songs, releases, and artists on record
 */
public class OfflineDatabase implements Database{

    /**
     * Attributes
     */
    private FileParser FILEREADER;
    private HashMap<String, Song> songs;
    private HashMap<String, Release> releases;
    private HashMap<String, Artist> artists;

    /**
     * Constructor
     */
    public OfflineDatabase() {
        this.FILEREADER = new FileParser();
        this.songs = new HashMap<>();
        this.releases = new HashMap<>();
        this.artists = new HashMap<>();
        initializeDatabase();
    }


    /*
     * ===================================================================================
     *                                  initializers
     * ===================================================================================
     */

    /**
     * initializes the database by:
     *
     * reading the csv files constructing searchables from the data in the files
     * adding those searchables to the database then it constructs the user's
     * library
     */
    private void initializeDatabase() {
        initializeArtists(new ArtistFactory());
        initializeSongs(new SongFactory());
        initializeAlbums(new ReleaseFactory());
    }

    /**
     *  adds all the songs to the database from the files
     * @param maker the factory
     */
    private void initializeSongs(SongFactory maker) {
        FILEREADER.setFileName("songs.csv");
        FILEREADER.setFilePath("src/data/global/");
        try {
            ArrayList<String[]> splitData = FILEREADER.readFile();
            for (String[] fields : splitData) {
                Song entry = maker.makeSongFromCsv(fields);
                songs.put(entry.getGUID(), entry);
                addToArtistDiscography(entry);
            }
        } catch (java.io.IOException e) {
            System.err.println(e);
        }
    }

    /**
     * adds all the artists to the database from the files
     * @param maker the factory
     */
    private void initializeArtists(ArtistFactory maker) {
        FILEREADER.setFileName("artists.csv");
        FILEREADER.setFilePath("src/data/global/");
        try {
            ArrayList<String[]> splitData = FILEREADER.readFile();
            for (String[] fields : splitData) {
                Artist entry = maker.makeArtistFromCsv(fields);
                artists.put(entry.getGUID(), entry);
            }
        } catch (java.io.IOException e) {
            System.err.println(e);
        }
    }

    /**
     * adds all the releases to the database from the files
     * @param maker the factory
     */
    private void initializeAlbums(ReleaseFactory maker) {
        FILEREADER.setFileName("releases.csv");
        FILEREADER.setFilePath("src/data/global/");
        try {
            ArrayList<String[]> splitData = FILEREADER.readFile();
            for (String[] fields : splitData) {
                Release entry = maker.makeReleaseFromCsv(fields, this);
                releases.put(entry.getGUID(), entry);
                addToArtistDiscography(entry);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }







    /**
     * adds a searchable to the artist's discography
     * @param entry the searchable to be added to the discography
     */
    private void addToArtistDiscography(Searchable entry) {
        String artistGUID = entry.getArtistGUID();
        Artist artist = artists.get(artistGUID);
        artist.addSearchable(entry);
    }




    /*
     * ===================================================================================
     * getters for items in the database
     * ===================================================================================
     */

    /**
     * given a guid will return the Song associated with the guid
     * @param GUID the guid of the desired Song
     * @return the Song searched for
     */
    public Song getSong(String GUID) {
        return songs.get(GUID);
    }

    /**
     * given a guid will return the Artist associated with the guid
     * @param GUID the guid of the desired Artist
     * @return the Artist searched for
     */
    public Artist getArtist(String GUID) {
        return artists.get(GUID);
    }

    /**
     * given a guid will return the Release associated with the guid
     * @param GUID the guid of the desired Release
     * @return the Release searched for
     */
    public Release getRelease(String GUID) {
        return releases.get(GUID);
    }

    /**
     * Given a string of a guid, will search the database and retrieve a searchable
     * of that guid if it exists
     *
     * @param GUID the guid of the searchable to be added to the database
     * @return the searchable of
     */
    public Searchable getSearchable(String GUID) {
        if (getSong(GUID) != null) {
            return getSong(GUID);
        } else if (getArtist(GUID) != null) {
            return getArtist(GUID);
        } else if (getRelease(GUID) != null) {
            return getRelease(GUID);
        }
        return null;
    }


    /*
     * ===================================================================================
     * For future releases, most of the functionality below this line should be moved to
     * a display class.
     * ===================================================================================
     */

    /**
     * given a search value, will search the database for content matching that search value
     * and returns all the songs that satisfy that search
     * @return the songs that match that search
     */
    @Override
    public Response getSongs() {
        List<Searchable> searchables = new ArrayList<>(songs.values());
        return new Response( searchables, true, "List of every Song");
    }

    /**
     * given a search value, will search the database for content matching that search value
     * and returns all the releases that satisfy that search
     * @return the releases that match that search
     */
    @Override
    public Response getReleases() {
        List<Searchable> searchables = new ArrayList<>(releases.values());
        return new Response(searchables, true, "List of every Release");
    }

    /**
     * given a search value, will search the database for content matching that search value
     * and returns all the artists that satisfy that search
     * @return the artists that match that search
     */
    @Override
    public Response getArtists() {
        List<Searchable> searchables = new ArrayList<>(artists.values());

        return new Response(searchables, true, "List of all Releases");
    }

}
