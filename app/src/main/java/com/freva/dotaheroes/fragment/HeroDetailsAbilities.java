package com.freva.dotaheroes.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.freva.dotaheroes.R;
import com.freva.dotaheroes.adapter.HeroAbilitiesAdapter;
import com.freva.dotaheroes.container.Ability;
import com.freva.dotaheroes.container.Hero;
import com.freva.dotaheroes.data.Heroes;

import java.util.List;

public class HeroDetailsAbilities extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_hero_details_abilties, container, false);

        Hero selectedHero = Heroes.getHero(getArguments().getString("HERO_ID"));

        List<Ability> abilities = selectedHero.getAbilities();
        HeroAbilitiesAdapter heroAbiltiesAdapter = new HeroAbilitiesAdapter(getActivity(), abilities);
        ListView listview = (ListView) rootView.findViewById(R.id.fragment_hero_details_abilities_list_view);
        listview.setAdapter(heroAbiltiesAdapter);

        return rootView;
    }
}
