package com.driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
//		SpotifyController controller = new SpotifyController();
//		controller.createUser("Rahul", "11223344");
//		controller.createUser("Rohit", "12341234");
//		controller.createArtist("Arijit Singh");
//		controller.createArtist("Lata Ji");
//		controller.createAlbum("Tere Bin", "Arijit Singh");
//		controller.createAlbum("Old Songs", "Lata Ji");
//		List<String> songsList = new ArrayList<>();
//		try {
//			controller.createSong("Mai Jo Hu", "Tere Bin", 12);
//		} catch (Exception e) {
//			System.out.println("Exception found in create song"+e);
//		}
//		try {
//
//		controller.createSong("Tumne Jo Kha", "Old Songs", 13);
//	    }
//
//		catch (Exception e) {
//			System.out.println("Exception found in create song"+e);
//		}
//		songsList.add("Mai Jo Hu");
//		try {
//			controller.createPlaylistOnName("11223344", "My Songs", songsList);
//		}
//		catch (Exception e){
//			System.out.println("Exception found in create playlist on name"+e);
//		}
//		try{	controller.likeSong("11223344", "Mai Jo Hu");
//		}
//		catch (Exception e){
//			System.out.println("Exception found in like song"+e);
//		}
//		try{
//			controller.findPlaylist("11223344","My Songs");
//		}
//		catch (Exception e){
//
//		}
//		System.out.println(controller.mostPopularArtist());
//		System.out.println(controller.mostPopularSong());



	}
}
