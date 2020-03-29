package com.company.Database;

import com.company.FileIO.FileParser;
import com.company.RequestInterpreter.Filters.*;
import com.company.RequestInterpreter.Sorts.*;
import com.company.SearchableFactory.ArtistFactory;
import com.company.SearchableFactory.DateMaker;
import com.company.SearchableFactory.ReleaseFactory;
import com.company.SearchableFactory.SongFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author mjh9131
 *
 *         contains all the songs, releases, and artists on record
 */
public class Database {

    /**
     * Attributes
     */
    private FileParser FILEREADER;
    private static Library library;
    private HashMap<String, Song> songs;
    private HashMap<String, Release> releases;
    private HashMap<String, Artist> artists;
    private Filter filter;
    private Sort sort;

    /**
     * Constructor
     */
    public Database() {
        this.FILEREADER = new FileParser();
        library = new Library(this);
        this.songs = new HashMap<>();
        this.releases = new HashMap<>();
        this.artists = new HashMap<>();
        this.filter = new NameFilter();     // Set default filtering
        this.sort = new Alphabetical();     // Set default sorting
        initializeDatabase();
    }

    /**
     * saves the library's contents
     */
    public void saveLibrary() {
        library.saveLibrary();
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
        initializeLibrary(library.getUsername());
    }

    /**
     * builds the library by adding each type of searchable to it from their
     * coresponding files adds
     *
     * @param signedInUser
     */
    private void initializeLibrary(String signedInUser) {
        addSearchableToLibraryFromFile(signedInUser, "Artists");
        addSearchableToLibraryFromFile(signedInUser, "Songs");
        addSearchableToLibraryFromFile(signedInUser, "Releases");
        addRatingToSongFromFile(signedInUser);
        addAcquisitionDateFromFile(signedInUser);
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





    /*
     * ===================================================================================
     *                      getters for items in the database
     * ===================================================================================
     */

    /**
     * reads the user's associated searchable files and adds them to their library
     * @param signedInUser      the user's username
     * @param searchableType    the type of searchable file that needs to be read
     */
    private void addSearchableToLibraryFromFile(String signedInUser, String searchableType) {
        FILEREADER.setFileName(signedInUser + searchableType + ".csv");
        FILEREADER.setFilePath("src/data/user/");
        try {
            ArrayList<String[]> splitData = FILEREADER.readFile();
            for (String[] fields : splitData) {
                library.addSearchable(fields[0]);
            }
        } catch (java.io.IOException e) {
            // System.err.println(e);
        }
    }

    /**
     * reads the user's associated rating files and adds them to the corresponding searchable
     * @param signedInUser the user's username
     */
    private void addRatingToSongFromFile(String signedInUser){
        FILEREADER.setFileName(signedInUser + "Ratings" + ".csv");
        FILEREADER.setFilePath("src/data/user/");
        try {
            ArrayList<String[]> splitData = FILEREADER.readFile();
            for (String[] fields : splitData) {
                Song songToAddRating = songs.get(fields[0]);
                songToAddRating.setRating(Integer.parseInt(fields[1]));
            }
        } catch (java.io.IOException e) {
            // System.err.println(e);
        }
    }

    /**
     * reads the user's associated acquisition date files and adds them to the corresponding searchable
     * @param signedInUser the user's username
     */
    private void addAcquisitionDateFromFile(String signedInUser) {
        FILEREADER.setFileName(signedInUser + "Dates" + ".csv");
        FILEREADER.setFilePath("src/data/user/");
        try {
            ArrayList<String[]> splitData = FILEREADER.readFile();
            DateMaker maker = new DateMaker();
            for (String[] fields : splitData) {
                Searchable songToAddDate = songs.get(fields[0]);
                Searchable releaseToAddDate = releases.get(fields[0]);
                if(songToAddDate != null){
                    songToAddDate.setAcquisitionDate(maker.makeDate(fields[1]));
                }
                if(releaseToAddDate != null){
                    releaseToAddDate.setAcquisitionDate(maker.makeDate(fields[1]));

                }
            }
        } catch (Exception e) {
            // System.err.println(e);
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

    /**
     * given a searchable guid and acquisition date, will add the searchable to the user's library
     * @param searchableGUID the guid of the searchable to add
     * @param aquDate the acquisition date of the searchable to add
     */
    public void addSearchableToLibrary(String searchableGUID, Date aquDate) {
        if(getArtist(searchableGUID) != null) {
            System.out.println("Artist not added to library.  Only songs and releases can be added to your library.");
            return;
        }
        library.addAcquisitionDate(searchableGUID, aquDate);
        library.addSearchable(searchableGUID);
    }

    /**
     * given a searchable guid, remove it from a user's library
     * @param searchableGUID the guid of the searchable to be removed
     */
    public boolean removeSearchableFromLibrary(String searchableGUID) {
        return library.removeSearchable(searchableGUID);
    }

    /**
     * rates a searchable in a user's library
     * 
     * @param searchableGUID the guid of the searchable to be rated
     * @param rating         the rating to be set
     * @throws Exception
     */
    public void rateSearchableInLibrary(String searchableGUID, int rating) throws Exception {
        library.addRating(searchableGUID, rating);
    }

    /**
     * gets the hashmap of artists contained within the library
     * @return the artist hashmap
     */
    public HashMap<String, ArrayList<Searchable>> getArtistMap() {
        return library.getArtistMap();
    }




    /*
     * ===================================================================================
     * For future releases, most of the functionality below this line should be moved to
     * a display class.
     * ===================================================================================
     */

    /**
     * the hashmap of possible filters
     */
    private static final HashMap<String, Filter> FILTERS;
    static {
        FILTERS = new HashMap<>();
        FILTERS.put("name", new NameFilter());
        FILTERS.put("maxduration", new MaxDurationFilter());
        FILTERS.put("minduration", new MinDurationFilter());
        FILTERS.put("guid", new GUIDFilter());
        FILTERS.put("date-range", new DateRangeFilter());
        FILTERS.put("rating", new RatingFilter());
    }

    /**
     * the hashmap of possible sorts
     */
    private static final HashMap<String, Sort> SORTS;
    static {
        SORTS = new HashMap<>();
        // Add Commands here
        // Note: Keys should always be lowercase
        SORTS.put("acquisitiondate", new AcquisitionDate());
        SORTS.put("alphabetical", new Alphabetical());
        SORTS.put("rating", new Rating());
    }

    /**
     * sets the filter according to what was specified
     * @param filter the name of the filter to be set
     */
    public void setFilter(String filter) {
        if (FILTERS.containsKey(filter.toLowerCase())) {
            this.filter = FILTERS.get(filter.toLowerCase());
            System.out.printf("Filter successfully set to '%s'\n", filter);
        } else {
            System.err.printf("'%s' is an invalid filter.\n", filter);
        }
    }

    /**
     * sets the sort acording to what was specified
     * @param sort the name of the sort to be set
     */
    public void setSort(String sort) {
        if (SORTS.containsKey(sort.toLowerCase())) {
            this.sort = SORTS.get(sort.toLowerCase());
            System.out.printf("Sort successfully set to '%s'.\n", sort);
        } else {
            System.err.printf("'%s' is not a valid sort\n", sort);
        }
    }

    /**
     * gets the available sort types
     * @return the set of sort names(String)
     */
    public Set<String> getAvailableSortTypes() {
        return SORTS.keySet();
    }

    /**
     * gets the available filter types
     * @return the set of filter names
     */
    public Set<String> getAvailableFilterTypes() {
        return FILTERS.keySet();
    }

    /**
     * given a search value, will search the database for content matching that search value
     * and returns all the songs that satisfy that search
     * @param searchValue the value to be searched for
     * @return the songs that match that search
     */
	public Collection<Song> getSongs(String searchValue) {

        if (filter == null) {
            System.err.println("Filter not set, please use 'setfilter' to set the filter.");
            return new LinkedList<>();
        }

        LinkedList<Song> output = filter.filterSongs(songs.values(), searchValue);

        if(output != null) {
            output.sort(sort);
        }

        return output;
	}

    /**
     * given a search value, will search the database for content matching that search value
     * and returns all the releases that satisfy that search
     * @param searchValue the value to be searched for
     * @return the releases that match that search
     */
	public Collection<Release> getReleases(String searchValue) {

        if (filter == null) {
            System.err.println("Filter not set, please use 'setfilter' to set the filter.");
            return new LinkedList<>();
        }

        LinkedList<Release> output = filter.filterReleases(releases.values(), searchValue);

        if(output != null) {
            output.sort(sort);
        }

        return output;
    }

    /**
     * given a search value, will search the database for content matching that search value
     * and returns all the artists that satisfy that search
     * @param searchValue the value to be searched for
     * @return the artists that match that search
     */
	public Collection<Artist> getArtists(String searchValue) {

        if (filter == null) {
            System.err.println("Filter not set, please use 'setfilter' to set the filter.");
            return new LinkedList<>();
        }

        LinkedList<Artist> output = filter.filterArtists(artists.values(), searchValue);

        if(output != null) {
            output.sort(sort);
        }

        return output;
    }

    /**
     * given a search value, will search the library for content matching that search value
     * and returns all the songs that satisfy that search
     * @param searchValue the value to be searched for
     * @return the songs that match that search
     */
    public Collection<Song> getSongsFromLibrary(String searchValue) {
     
        if (filter == null) {
            System.err.println("Filter not set, please use 'setfilter' to set the filter.");
            return new LinkedList<>();
        }

        Collection<Song> someSongs = new ArrayList<>();
        for(Searchable song: library.seperateSearchables("song")){
            someSongs.add((Song)song);
        }

        LinkedList<Song> output = filter.filterSongs(someSongs, searchValue);

        output.sort(sort);

        return output;
    }

    /**
     * given a search value, will search the library for content matching that search value
     * and returns all the releases that satisfy that search
     * @param searchValue the value to be searched for
     * @return the releases that match that search
     */
    public Collection<Release> getReleasesFromLibrary(String searchValue) {
		
        if (filter == null) {
            System.err.println("Filter not set, please use 'setfilter' to set the filter.");
            return new LinkedList<>();
        }

        Collection<Release> someSongs = new ArrayList<>();
        for(Searchable song: library.seperateSearchables("release")){
            someSongs.add((Release)song);
        }

        LinkedList<Release> output = filter.filterReleases(someSongs, searchValue);

        output.sort(sort);

        return output;
    }

    /**
     * given a search value, will search the library for content matching that search value
     * and returns all the Artists that satisfy that search
     * @param searchValue the value to be searched for
     * @return the Artists that match that search
     */
	public Collection<Artist> getArtistsFromLibrary(String searchValue) {

        if (filter == null) {
            System.err.println("Filter not set, please use 'setfilter' to set the filter.");
            return new LinkedList<>();
        }

        Collection<Artist> someSongs = new ArrayList<>();
        for(Searchable song: library.seperateSearchables("artist")){
            someSongs.add((Artist)song);
        }

        LinkedList<Artist> output = filter.filterArtists(someSongs, searchValue);

        output.sort(sort);

        return output;
    }

    /**
     * gets the collection of searchables in the library based on a specified artist contained in the user's library
     * @param args the name of the artist
     * @return the searchables by the artist in the library
     */
	public ArrayList<Searchable> getArtistFromLibrary(String args) {

        ArrayList<Searchable> output = library.getArtistMap().get(args);

        if(output != null) {
            output.sort(sort);
            return output;
        }

		return output;
	}

    /**
     * gets a release from the user's library when given a guid
     * @param args the guid of the release
     * @return the release
     */
	public Searchable getReleaseFromLibrary(String args) {
		return library.getRelease(args);
	}

    /**
     * gets a song from the user's library when given a guid
     * @param args the guid of the song
     * @return the song
     */
	public Searchable getSongFromLibrary(String args) {
		return library.getSong(args);
	}
}
