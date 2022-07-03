package com.example.services.withDB;

import com.example.commands.base.Redirector;
import com.example.models.Track;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TracksService {

    private static HttpURLConnection getConnection(){
        HttpURLConnection connection;
        try {
            URL url = new URL("http://localhost:8080/test//tracks?userId=1");
            connection = (HttpURLConnection) url.openConnection();
            return connection;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void sendTrack(Track track){
        //TODO
        HttpURLConnection connection = getConnection();
        BufferedReader reader;
        String line;
        StringBuilder sb;
        try {
            connection.setRequestMethod("POST");
            ObjectMapper mapper = new ObjectMapper();
            String trackAsString = mapper.writeValueAsString(track);
            connection.setDoOutput(true);
            System.out.println(connection.getResponseCode());

            try(OutputStream os = connection.getOutputStream()) {
                byte[] input = trackAsString.getBytes("utf-8");
                os.write(input, 0, input.length);
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (ProtocolException | JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("exception");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            connection.disconnect();
        }
        System.out.println(track);
    }
    public List<Track> getTodayTracks(Long userId){
        HttpURLConnection connection;
        //BufferedReader reader;
        String line;
        StringBuilder sb;
        try{
            connection = getConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);

            int status = connection.getResponseCode();

            if(status < 299){

                ObjectMapper objectMapper = new ObjectMapper();
            }
            connection.disconnect();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO get list of today tracks by userId (Long userId)
        List<Track> list = new ArrayList<>();
        Track track = new Track("track1");
        Track track2 = new Track("track2");
        list.add(track);
        list.add(track2);
        return list;
    }
    public void removeTrackById(int trackId){
        //TODO remove track by id (int trackId)
        System.out.println("track removed");


    }
}
