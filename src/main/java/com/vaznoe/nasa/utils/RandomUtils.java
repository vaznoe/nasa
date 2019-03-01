package com.vaznoe.nasa.utils;

/**
 * @author vaznoe
 * Date: 2/16/19
 */
public class RandomUtils {

    public static String generateSearch() {
        String[] units = {"apollo", "moon", "nasa", "mars", "Lunar", "Washington", "DC", "astronauts", "flight",
                "1961", "orbiter", "news", "STS-131", "mission", "space", "station", "schedule", "spaceX"};
        return units[(int) (Math.random() * units.length)];
    }
}
