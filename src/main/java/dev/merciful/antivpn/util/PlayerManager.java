package dev.merciful.antivpn.util;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class stores the hashmap and an arraylist that has the addresses for the players and ignored players
 */
public class PlayerManager {

    private static final HashMap<String, Boolean> playerHashMap = new HashMap<>();
    private static final ArrayList<String> ignoredPlayers = new ArrayList<>();

    public static HashMap<String, Boolean> getHashMap(){
        return playerHashMap;
    }
    public static ArrayList<String> getIgnoredPlayers(){
        return ignoredPlayers;
    }


}