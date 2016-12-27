package com.freva.dotaheroes.data;


import android.content.res.Resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freva.dotaheroes.MainActivity;
import com.freva.dotaheroes.R;
import com.freva.dotaheroes.container.Hero;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Heroes {
    private static final Map<String, Hero> heroes = new HashMap<>();


    static {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Map<String, Map<String, Object>> heroesRaw = mapper.readValue(
                    MainActivity.getMainActivity().getResources().openRawResource(R.raw.heroes), Map.class);

            for (Map.Entry<String, Map<String, Object>> heroRaw: heroesRaw.entrySet()) {
                int iconResourceId = MainActivity.getMainActivity().getResources().getIdentifier(
                        "icon_hero_" + heroRaw.getKey(), "drawable", MainActivity.getMainActivity().getPackageName());
                heroes.put(heroRaw.getKey(),
                        new Hero((String) heroRaw.getValue().get("name"), iconResourceId));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Hero> getHeroes() {
        return new ArrayList<>(heroes.values());
    }
}
