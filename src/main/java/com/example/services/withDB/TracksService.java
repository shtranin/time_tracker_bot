package com.example.services.withDB;

import com.example.commands.base.Redirector;
import com.example.models.Track;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TracksService {

    private HttpURLConnection connection;

    public void sendTrack(Track track) {
        try {
            URL url = new URL("http://localhost:8085/test/tracks");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(900000000);
            connection.setDoOutput(true);


                ObjectMapper mapper = new ObjectMapper();
                try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
                    writer.write(mapper.writeValueAsString(track));
                    writer.flush();
                }
            connection.getResponseCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }

    }

    public List<Track> getTodayTracks(Long userId) {
        List<Track> tracks;
        try {
            URL url = new URL("http://localhost:8085/test//tracks?userId=" + userId);
            connection = (HttpURLConnection) url.openConnection();
            tracks = new ArrayList<>();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.connect();
           // if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((inputLine = reader.readLine()) != null) {
                        stringBuilder.append(inputLine);
                    }
                    ObjectMapper objectMapper = new ObjectMapper();
                    tracks = objectMapper.readValue(stringBuilder.toString(), new TypeReference<List<Track>>() {
                    });
               }
//            } else {
//                InputStream errorStream = connection.getErrorStream();
//                toConsole(errorStream);
//            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
        return tracks;
    }

    public void removeTrackById(int trackId) {
        try {
            URL url = new URL("http://localhost:8085/test//tracks?trackId=" + trackId);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setConnectTimeout(5000);
            connection.connect();
            if (HttpURLConnection.HTTP_OK != connection.getResponseCode()) {
                InputStream errorStream = connection.getErrorStream();
                toConsole(errorStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }


    private void toConsole(InputStream errorStream) {
        String result = new BufferedReader(new InputStreamReader(errorStream))
                .lines().collect(Collectors.joining("\n"));
        System.out.println(result);
    }

}
