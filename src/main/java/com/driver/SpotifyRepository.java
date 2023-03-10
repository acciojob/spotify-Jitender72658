package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class SpotifyRepository {
    public HashMap<Artist, List<Album>> artistAlbumMap;
    public HashMap<Album, List<Song>> albumSongMap;
    public HashMap<Playlist, List<Song>> playlistSongMap;
    public HashMap<Playlist, List<User>> playlistListenerMap;
    public HashMap<User, Playlist> creatorPlaylistMap;
    public HashMap<User, List<Playlist>> userPlaylistMap;
    public HashMap<Song, List<User>> songLikeMap;

    public List<User> users;
    public List<Song> songs;
    public List<Playlist> playlists;
    public List<Album> albums;
    public List<Artist> artists;

    public HashMap<User,List<Song>> userLikedSongsMap;
    public HashMap<Artist,List<Song>> artistLikedSongsMap;

    public SpotifyRepository(){
        //To avoid hitting apis multiple times, initialize all the hashmaps here with some dummy data
        artistAlbumMap = new HashMap<>();
        albumSongMap = new HashMap<>();
        playlistSongMap = new HashMap<>();
        playlistListenerMap = new HashMap<>();
        creatorPlaylistMap = new HashMap<>();
        userPlaylistMap = new HashMap<>();
        songLikeMap = new HashMap<>();

        users = new ArrayList<>();
        songs = new ArrayList<>();
        playlists = new ArrayList<>();
        albums = new ArrayList<>();
        artists = new ArrayList<>();
        userLikedSongsMap = new HashMap<>();
    }

    public User createUser(String name, String mobile) {
        User newUser = new User(name,mobile);
        users.add(newUser);
        return newUser;
    }

    public Artist createArtist(String name) {
        Artist newArtist = new Artist(name);
        artists.add(newArtist);
        return newArtist;
    }

    public Album createAlbum(String title, String artistName) {
        // checking that the artist is already present or not
        Artist currentArtist = new Artist(artistName);
       boolean isArtistPresent = false;
        for(int i =0;i<artists.size();i++){
            Artist artist = artists.get(i);
            if(artist.getName().equals(artistName)){
                currentArtist = artist;
                isArtistPresent = true;
                break;
            }
        }
        if(!isArtistPresent){
            artists.add(currentArtist);
        }

        // adding new album in aristAlbumMap
        Album newAlbum = new Album(title);
        List<Album> albumList = new ArrayList<>();
        if(artistAlbumMap.containsKey(currentArtist)){
            albumList = artistAlbumMap.get(currentArtist);
        }
        albumList.add(newAlbum);
        artistAlbumMap.put(currentArtist,albumList);
        albums.add(newAlbum);
        return newAlbum;
    }

    public Song createSong(String title, String albumName, int length) throws Exception{

        Album currentAlbum = new Album();
        boolean isAlbumExist = false;
        for(int i =0;i<albums.size();i++){
           Album album = albums.get(i);
            if(album.getTitle().equals(albumName)){
                isAlbumExist = true;
                currentAlbum = album;
                break;
            }
        }

        if(!isAlbumExist){
            throw new Exception("Album does not exist");
        }

        Song newSong = new Song(title,length);

         List<Song> songList = new ArrayList<>();
         if(albumSongMap.containsKey(albumName)){
             songList = albumSongMap.get(albumName);
         }
         songList.add(newSong);
         albumSongMap.put(currentAlbum,songList);
         songs.add(newSong);
        return newSong;
    }

    public Playlist createPlaylistOnLength(String mobile, String title, int length) throws Exception {
       boolean isUserExist = false;
       User newuser = new User();
       for(int i =0;i<users.size();i++){
           User currentUser = users.get(i);
           if(currentUser.getMobile().equals(mobile)){
               isUserExist = true;
               newuser = currentUser;
               break;
           }
       }
       if(!isUserExist){
           throw new Exception("User does not exist");
       }
       List<Song> songList = new ArrayList<>();
       for(int i =0;i<songs.size();i++){
           Song currentSong = songs.get(i);
           if(currentSong.getLength()==length){
               songList.add(currentSong);
           }
       }
       Playlist newPlayList = new Playlist(title);
       playlistSongMap.put(newPlayList,songList);
       List<User> userList = new ArrayList<>();
       userList.add(newuser);
       playlistListenerMap.put(newPlayList,userList);
       creatorPlaylistMap.put(newuser,newPlayList);
       List<Playlist> userPlayLists= new ArrayList<>();
       if(userPlayLists.contains(newuser)){
          userPlayLists =  userPlaylistMap.get(newuser);
       }
       userPlayLists.add(newPlayList);
       userPlaylistMap.put(newuser,userPlayLists);
       playlists.add(newPlayList);
       return newPlayList;

    }

    public Playlist createPlaylistOnName(String mobile, String title, List<String> songTitles) throws Exception {
        boolean isUserExist = false;
        User newuser = new User();
        for(int i =0;i<users.size();i++){
            User currentUser = users.get(i);
            if(currentUser.getMobile().equals(mobile)){
                isUserExist = true;
                newuser = currentUser;
                break;
            }
        }
        if(!isUserExist){
            throw new Exception("User does not exist");
        }
        List<Song> songList = new ArrayList<>();
        for(int i =0;i<songTitles.size();i++){
            String givenSongTitle = songTitles.get(i);
            for(int j =0;j<songs.size();j++){
                Song currentSong = songs.get(j);
                if(currentSong.getTitle().equals(givenSongTitle)){
                    songList.add(currentSong);
                    break;
                }
            }
        }
        Playlist newPlayList = new Playlist(title);
        playlistSongMap.put(newPlayList,songList);
        List<User> userList = new ArrayList<>();
        userList.add(newuser);
        playlistListenerMap.put(newPlayList,userList);
        creatorPlaylistMap.put(newuser,newPlayList);
        List<Playlist> userPlayLists= new ArrayList<>();
        if(userPlayLists.contains(newuser)){
            userPlayLists =  userPlaylistMap.get(newuser);
        }
        userPlayLists.add(newPlayList);
        userPlaylistMap.put(newuser,userPlayLists);
        playlists.add(newPlayList);
        return newPlayList;
    }

    public Playlist findPlaylist(String mobile, String playlistTitle) throws Exception {
        boolean isUserExist = false;
        User newuser = new User();
        for(int i =0;i<users.size();i++){
            User currentUser = users.get(i);
            if(currentUser.getMobile().equals(mobile)){
                isUserExist = true;
                newuser = currentUser;
                break;
            }
        }
        if(!isUserExist){
            throw new Exception("User does not exist");
        }

        boolean isPlayListExist = false;
        Playlist newPlayList = new Playlist();
        for(int i =0;i<playlists.size();i++){
            Playlist currentPlayList = playlists.get(i);
            if(currentPlayList.getTitle().equals(playlistTitle)){
                isPlayListExist = true;
                newPlayList = currentPlayList;
                break;
            }
        }
        if(!isPlayListExist){
            throw new Exception("Playlist does not exist");
        }

        List<User> listeners = new ArrayList<>();
        if(playlistListenerMap.containsKey(newPlayList)) {
            listeners =  playlistListenerMap.get(newPlayList);
        }
       boolean isUserAlreadyPresent = false;
        for(User user: listeners){
            if(user.getMobile().equals(newuser.getMobile())){
                isUserAlreadyPresent = true;
                break;
            }
        }
        if(isUserAlreadyPresent){
            return newPlayList;
        }
        listeners.add(newuser);
        playlistListenerMap.put(newPlayList,listeners);
        return newPlayList;
    }

    public Song likeSong(String mobile, String songTitle) throws Exception {
        //However, an artist can indirectly have multiple likes from a user, if the user has liked multiple songs of that artist.
        boolean isUserExist = false;
        User newuser = new User();
        for(int i =0;i<users.size();i++){
            User currentUser = users.get(i);
            if(currentUser.getMobile().equals(mobile)){
                isUserExist = true;
                newuser = currentUser;
                break;
            }
        }
        if(!isUserExist){
            throw new Exception("User does not exist");
        }

        boolean isSongExist = false;
        Song newSong = new Song();
        for(int i =0;i<songs.size();i++){
            Song currentSong = songs.get(i);
            if(currentSong.getTitle().equals(songTitle)){
                isSongExist = true;
               newSong = currentSong;
                break;
            }
        }
        if(!isSongExist){
            throw new Exception("Song does not exist");
        }

        // adding likes to the song and make sure it is not liked by the same user again, so putting in userLikedSongMap;
        List<Song> userLikedSongs = new ArrayList<>();
        if(userLikedSongsMap.containsKey(newuser)){
            userLikedSongs = userLikedSongsMap.get(newuser);
        }

        boolean isListContainSong = false;
        for(Song song: userLikedSongs){
            if(newSong.getTitle().equals(song.getTitle())){
                isListContainSong = true;
                break;
            }
        }

        if(!isListContainSong){
            newSong.setLikes(newSong.getLikes()+1);
            userLikedSongs.add(newSong);
            userLikedSongsMap.put(newuser,userLikedSongs);
        }
        Album albumHavingCurrSong = new Album();
        boolean isSongPresentInAlbum = false;
        for(Album album: albumSongMap.keySet()){
           if(albumSongMap.get(album)!=null) {
               List<Song> songList = albumSongMap.get(album);
               if (songList.contains(newSong)) {
                   albumHavingCurrSong = album;
                   isSongPresentInAlbum = true;
                   break;
               }
           }
        }
        if(!isSongPresentInAlbum){
            return newSong;
        }


        Artist artistOfCurrSong = new Artist();
        for(Artist artist: artistAlbumMap.keySet()){
            if(artistAlbumMap.get(artist)!=null) {
                List<Album> albumList = artistAlbumMap.get(artist);
                if (albumList.contains(albumHavingCurrSong)) {
                    artist.setLikes(artist.getLikes() + 1);
                    break;
                }
            }
        }
        return newSong;
    }

    public String mostPopularArtist() {
        int maxLikes = 0;
        String mostPopularArtistName = "";
        for(Artist artist: artistAlbumMap.keySet()){
            if(artist.getLikes()>maxLikes){
                maxLikes = artist.getLikes();
                mostPopularArtistName = artist.getName();
            }
        }
        return mostPopularArtistName;
    }

    public String mostPopularSong() {
        String mostPopularSongTitle = "";
        int mostLikes = 0;
        for(int i = 0;i<songs.size();i++){
            Song currentSong = songs.get(i);
            if(currentSong.getLikes()>mostLikes){
                mostPopularSongTitle = currentSong.getTitle();
                mostLikes = currentSong.getLikes();
            }
        }
        return mostPopularSongTitle;
    }
}
