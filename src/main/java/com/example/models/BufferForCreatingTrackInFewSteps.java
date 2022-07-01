package com.example.models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BufferForCreatingTrackInFewSteps {
    private static Map<Long,Track> bufferForTracks = new HashMap<>();

    public static void setBufferedTrackDescription(Long userId, String description){
        Track track = new Track();
        track.setUserId(userId);
        track.setDescription(description);
        track.setDate(new Date());
        bufferForTracks.put(userId,track);
    }
    public static void setBufferForTrackSpentHours(Long userid,int hours){
        Track track = bufferForTracks.get(userid);
        track.setSpendHours(hours);
        bufferForTracks.put(userid,track);
    }
    public static Track getTrackAndDeleteFromBuffed(Long userId){
        Track track = bufferForTracks.get(userId);
        bufferForTracks.remove(userId);
        return track;
    }
}
