package com.company.Database;

import com.company.FileIO.FileParser;
import com.company.RequestInterpreter.Filters.*;
import com.company.RequestInterpreter.Sorts.*;

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

    /**
     * ===================================================================================
     * initializers
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
        SearchableMaker maker = new SearchableMaker(this);
        initializeArtists(maker);
        initializeSongs(maker);
        initializeAlbums(maker);
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
    private void initializeSongs(SearchableMaker maker) {
        FILEREADER.setFileName("songs.csv");
        FILEREADER.setFilePath("src/data/global/");
        try {
            ArrayList<String[]> splitData = FILEREADER.readFile();
            for (String[] fields : splitData) {
                Searchable entry = maker.makeSearchable("Song", fields);
                songs.put(entry.getGUID(), (Song) entry);
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
    private void initializeArtists(SearchableMaker maker) {
        FILEREADER.setFileName("artists.csv");
        FILEREADER.setFilePath("src/data/global/");
        try {
            ArrayList<String[]> splitData = FILEREADER.readFile();
            for (String[] fields : splitData) {
                Searchable entry = maker.makeSearchable("Artist", fields);
                artists.put(entry.getGUID(), (Artist) entry);
            }
        } catch (java.io.IOException e) {
            System.err.println(e);
        }
    }

    /**
     * adds all the releases to the database from the files
     * @param maker the factory
     */
    private void initializeAlbums(SearchableMaker maker) {
        FILEREADER.setFileName("releases.csv");
        FILEREADER.setFilePath("src/data/global/");
        try {
            ArrayList<String[]> splitData = FILEREADER.readFile();
            for (String[] fields : splitData) {
                Searchable entry = maker.makeSearchable("Release", fields);
                releases.put(entry.getGUID(), (Release) entry);
                addToArtistDiscography(entry);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * ===================================================================================
     * getters for items in the database
     * ===================================================================================
     */

    /**
     *
     * @param signedInUser
     * @param searchableType
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

    private void addAcquisitionDateFromFile(String signedInUser) {
        FILEREADER.setFileName(signedInUser + "Dates" + ".csv");
        FILEREADER.setFilePath("src/data/user/");
        try {
            ArrayList<String[]> splitData = FILEREADER.readFile();
            for (String[] fields : splitData) {
                Searchable searchableToAddDate = songs.get(fields[0]);
                searchableToAddDate.setAcquisitionDate(SearchableMaker.makeDate(fields[1]));
            }
        } catch (Exception e) {
            // System.err.println(e);
        }
    }

    private void addToArtistDiscography(Searchable entry) {
        String artistGUID = entry.getArtistGUID();
        Artist artist = artists.get(artistGUID);
        artist.addSearchable(entry);
    }

    /**
     * ===================================================================================
     * getters for items in the database
     * ===================================================================================
     */

    public Song getSong(String GUID) {
        return songs.get(GUID);
    }

    public Artist getArtist(String GUID) {
        return artists.get(GUID);
    }

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

    public void addSearchableToLibrary(String searchableGUID, Date aquDate) {
        library.addAcquisitionDate(searchableGUID, aquDate);
        library.addSearchable(searchableGUID);
    }

    public void removeSearchableFromLibrary(String searchableGUID) {
        library.removeSearchable(searchableGUID);
    }

    public void rateSearchableInLibrary(String searchableGUID, int rating) {
        library.addRating(searchableGUID, rating);
    }

    public HashMap<String, Collection<Searchable>> getArtistMap() {
        return library.getArtistMap();
    }

    /**
     * ===================================================================================
     * For future releases, most of the functionality below this line should be moved to
     * a display class.
     * ===================================================================================
     */    

    private static final HashMap<String, Filter> FILTERS;
    static {
        FILTERS = new HashMap<>();
        FILTERS.put("name", new DateRangeFilter());
        FILTERS.put("artist", new DateRangeFilter());
        FILTERS.put("duration", new DateRangeFilter());
        FILTERS.put("GUID", new GUIDFilter());
        FILTERS.put("date-range", new DateRangeFilter());
    }

    private static final HashMap<String, Sort> SORTS;
    static {
        SORTS = new HashMap<>();
        // Add Commands here
        // Note: Keys should always be lowercase
        SORTS.put("acquisitiondate", new AcquisitionDate());
        SORTS.put("alphasong", new Alphabetical());
        SORTS.put("alphaartist", new Alphabetical());
        SORTS.put("rating", new Rating());
    }

    public void setFilter(String filter) {
        if (FILTERS.containsKey(filter)) {
            this.filter = FILTERS.get(filter);
        } else {
            System.err.printf("Invalid search filter '%s' for songs\n", filter);
        }
    }

    public void setSort(String sort) {
        if (SORTS.containsKey(sort)) {
            this.sort = SORTS.get(sort);
        } else {
            System.err.printf("Invalid sort '%s' for songs\n", filter);
        }
    }

    public Set<String> getAvailableSortTypes() {
        return SORTS.keySet();
    }

    public Set<String> getAvailableFilterTypes() {
        return FILTERS.keySet();
    }

	public Collection<Song> getSongs(String searchValue) {

        if (filter == null) {
            System.err.println("Filter not set, please use 'setfilter' to set the filter.");
            return new LinkedList<>();
        }

        LinkedList<Song> output = filter.filterSongs(songs.values(), searchValue);

        output.sort(sort);

        return output;
	}

	public Collection<Release> getReleases(String searchValue) {

        if (filter == null) {
            System.err.println("Filter not set, please use 'setfilter' to set the filter.");
            return new LinkedList<>();
        }

        LinkedList<Release> output = filter.filterReleases(releases.values(), searchValue);

        output.sort(sort);

        return output;
    }
    
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

    public Collection<Release> getReleasesFromLibrary(String searchValue) {
		
        if (filter == null) {
            System.err.println("Filter not set, please use 'setfilter' to set the filter.");
            return new LinkedList<>();
        }

        Collection<Release> someSongs = new ArrayList<>();
        for(Searchable song: library.seperateSearchables("artist")){
            someSongs.add((Release)song);
        }

        LinkedList<Release> output = filter.filterReleases(someSongs, searchValue);

        output.sort(sort);

        return output;
    }

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

	public Collection<Searchable> getArtistFromLibrary(String args) {
		return library.getArtistMap().get(args);
	}

	public Searchable getReleaseFromLibrary(String args) {
		return library.getRelease(args);
	}

	public Searchable getSongFromLibrary(String args) {
		return library.getSong(args);
	}
}
