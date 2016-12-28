package com.freva.dotaheroes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.freva.dotaheroes.R;
import com.freva.dotaheroes.adapter.HeroListingAdapter;
import com.freva.dotaheroes.container.Hero;

import com.freva.dotaheroes.data.Heroes;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static MainActivity mainActivity;
    private HeroListingAdapter heroListingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Hero> heroes = Heroes.getHeroes();
        Collections.sort(heroes, (o1, o2) -> o1.getName().compareTo(o2.getName()));

        heroListingAdapter = new HeroListingAdapter(getApplicationContext(), heroes);
        ListView listview = (ListView) findViewById(R.id.heroes_list_view);
        listview.setAdapter(heroListingAdapter);
        listview.setOnItemClickListener((parent, view, position, id) -> {
            Hero selectedHero = ((Hero) parent.getItemAtPosition(position));
            Intent myIntent = new Intent(view.getContext(), HeroDetailsActivity.class);
            myIntent.putExtra("HERO_ID", selectedHero.getId());
            startActivityForResult(myIntent, 0);
        });

        EditText heroSearch = (EditText) findViewById(R.id.activity_main_hero_search_input);
        heroSearch.addTextChangedListener(new FilterHeroListener());
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

    public static MainActivity getMainActivity() {
        return mainActivity;
    }
}
