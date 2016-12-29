package com.freva.dotaheroes.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.freva.dotaheroes.fragment.HeroDetailsAbilities;
import com.freva.dotaheroes.fragment.HeroDetailsStats;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class HeroDetailsTabAdapter extends FragmentStatePagerAdapter {
    private final List<Map.Entry<String, Fragment>> tabs = Arrays.asList(
            new AbstractMap.SimpleEntry<>("Stats", new HeroDetailsStats()),
            new AbstractMap.SimpleEntry<>("Abilities", new HeroDetailsAbilities()));

    public HeroDetailsTabAdapter(FragmentManager fm, String heroId) {
        super(fm);

        Bundle bundle = new Bundle();
        bundle.putString("HERO_ID", heroId);

        for (Map.Entry<String, Fragment> tab : tabs) {
            tab.getValue().setArguments(bundle);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return tabs.get(position).getValue();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs.get(position).getKey();
    }

    @Override
    public int getCount() {
        return tabs.size();
    }
}