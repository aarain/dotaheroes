package com.freva.dotaheroes.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.freva.dotaheroes.MainActivity;
import com.freva.dotaheroes.R;
import com.freva.dotaheroes.adapter.HeroListingAdapter;
import com.freva.dotaheroes.container.Hero;

import com.freva.dotaheroes.data.Heroes;

import java.util.Collections;
import java.util.List;

public class HeroListing extends Fragment {
    private HeroListingAdapter heroListingAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_hero_listing, container, false);
        MainActivity.setIconTitle(R.drawable.icon_dota_logo, "Dota Heroes");

        List<Hero> heroes = Heroes.getHeroes();
        Collections.sort(heroes, (o1, o2) -> o1.getName().compareTo(o2.getName()));

        heroListingAdapter = new HeroListingAdapter(getActivity().getApplicationContext(), heroes);
        ListView listview = (ListView) rootView.findViewById(R.id.fragment_hero_listing_list_view);
        listview.setAdapter(heroListingAdapter);
        listview.setOnItemClickListener((parent, view, position, id) -> {
            Hero selectedHero = ((Hero) parent.getItemAtPosition(position));

            Bundle bundle = new Bundle();
            bundle.putString("HERO_ID", selectedHero.getId());
            Fragment fragment = new HeroDetails();
            fragment.setArguments(bundle);

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main_frame_container, fragment)
                    .addToBackStack(selectedHero.getId())
                    .commit();
        });

        EditText heroSearch = (EditText) rootView.findViewById(R.id.fragment_hero_listing_search_input);
        heroSearch.addTextChangedListener(new FilterHeroListener());

        return rootView;
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
