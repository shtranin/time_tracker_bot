package com.example.clearing_old_messages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeletingBlocker {
    private static List<Long> blockedId = new ArrayList<>();

    public static void setBlock(Long userId){
        blockedId.add(userId);
    }
    public static boolean isBlocked(Long userId){
        if(blockedId.contains(userId)){
            blockedId.remove(userId);
            return true;
        }else{
            return false;
        }
    }
}
