package com.example.services.withDB;

import com.example.commands.base.Redirector;
import com.example.models.Track;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

public class TracksService {


    public void sendTrack(Track track){
        //TODO send track to db (Track)
        System.out.println(track);
    }
    public List<Track> getTodayTracks(Long userId){
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
