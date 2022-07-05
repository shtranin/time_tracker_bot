package com.example.util;

import com.example.models.Track;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BufferForCreatingTrackInFewSteps {
    private static final Map<Long, Track> bufferForTracks = new HashMap<>();

    public static void setBufferedTrackDescription(Long userId, String description){
        Track track = new Track();
        track.setUserId(userId);
        track.setDescription(description);

        bufferForTracks.put(userId,track);
    }
    public static void setBufferForTrackSpentHours(Long userid,int hours){
        Track track = bufferForTracks.get(userid);
        track.setSpentHours(hours);
        bufferForTracks.put(userid,track);
    }
    public static Track getTrackAndDeleteFromBuffed(Long userId){
        Track track = bufferForTracks.get(userId);
        bufferForTracks.remove(userId);
        return track;
    }
}
