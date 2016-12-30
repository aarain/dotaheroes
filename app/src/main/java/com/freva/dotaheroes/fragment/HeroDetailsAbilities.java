package com.freva.dotaheroes.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freva.dotaheroes.R;
import com.freva.dotaheroes.container.Hero;
import com.freva.dotaheroes.data.Heroes;

public class HeroDetailsAbilities extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_hero_details_abilities, container, false);

        Hero selectedHero = Heroes.getHero(getArguments().getString("HERO_ID"));



        return rootView;
    }
}
