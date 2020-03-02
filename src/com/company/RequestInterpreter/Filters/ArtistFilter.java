// package com.company.RequestInterpreter.Filters;

// import java.util.Collection;
// import java.util.LinkedList;

// import com.company.Database.Artist;
// import com.company.Database.Release;
// import com.company.Database.Song;

// /**
//  * ArtistFilter
//  */
// public class ArtistFilter implements Filter {

//     @Override
//     public LinkedList<Release> filterReleases(Collection<Release> values, String searchValue) {

//         if()
        
//         return null;
//     }

//     @Override
//     public LinkedList<Song> filterSongs(Collection<Song> values, String searchValue) {


//         LinkedList<Song> filteredSongs = new LinkedList<>();

//         for (Song song : values) {
//             if (song.getArtistGUID().contains(searchValue)) {
//                 filteredSongs.add(song);
//             }
//         }
//         if(filteredSongs.size() != 0) {
//             return filteredSongs;
//         }

//         System.out.println("No songs by that artist were found");

//         return null;
//     }

//     @Override
//     public LinkedList<Artist> filterArtists(Collection<Artist> someSongs, String searchValue) {



//         return null;
//     }

    
// }