package com.freva.dotaheroes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.freva.dotaheroes.R;
import com.freva.dotaheroes.adapter.HeroListingAdapter;
import com.freva.dotaheroes.container.Hero;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listview = (ListView) findViewById(R.id.heroes_list_view);
        try {
            List<Hero> heroes = getHeroes();
            listview.setAdapter(new HeroListingAdapter(getApplicationContext(), heroes));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Hero> getHeroes() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List heroesRaw = mapper.readValue(getResources().openRawResource(R.raw.heroes), List.class);
        List<Hero> heroes = new ArrayList<>();

        for (Map<String, String> heroRaw: (List<Map<String, String>>) heroesRaw) {
            int iconResourceId = getApplicationContext().getResources().getIdentifier(
                    heroRaw.get("icon"), "drawable", getApplicationContext().getPackageName());
            heroes.add(new Hero(heroRaw.get("name"), iconResourceId));
        }

        Collections.sort(heroes, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        return heroes;
    }
}
