package com.freva.dotaheroes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
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
    private HeroListingAdapter heroListingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heroListingAdapter = new HeroListingAdapter(getApplicationContext(), getHeroes());
        ListView listview = (ListView) findViewById(R.id.heroes_list_view);
        listview.setAdapter(heroListingAdapter);

        EditText heroSearch = (EditText) findViewById(R.id.activity_main_hero_search_input);
        heroSearch.addTextChangedListener(new FilterHeroListener());
    }

    private List<Hero> getHeroes() {
        ObjectMapper mapper = new ObjectMapper();
        List<Hero> heroes = new ArrayList<>();

        try {
            List heroesRaw = mapper.readValue(getResources().openRawResource(R.raw.heroes), List.class);
            for (Map<String, String> heroRaw: (List<Map<String, String>>) heroesRaw) {
                int iconResourceId = getApplicationContext().getResources().getIdentifier(
                        heroRaw.get("icon"), "drawable", getApplicationContext().getPackageName());
                heroes.add(new Hero(heroRaw.get("name"), iconResourceId));
            }

            Collections.sort(heroes, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return heroes;
    }

    private class FilterHeroListener implements TextWatcher {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            heroListingAdapter.getFilter().filter(s);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void afterTextChanged(Editable s) { }
    }
}
