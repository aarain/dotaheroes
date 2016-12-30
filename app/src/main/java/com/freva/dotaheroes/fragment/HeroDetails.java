package com.freva.dotaheroes.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.freva.dotaheroes.R;
import com.freva.dotaheroes.adapter.HeroDetailsTabAdapter;
import com.freva.dotaheroes.data.Heroes;

public class HeroDetails extends Fragment implements TabLayout.OnTabSelectedListener {
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_hero_details, container, false);

        String selectedHeroId = getArguments().getString("HERO_ID");
        getActivity().setTitle(Heroes.getHero(selectedHeroId).getName());

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.fragment_hero_details_tab_layout);
        tabLayout.addOnTabSelectedListener(this);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        HeroDetailsTabAdapter heroDetailsTabAdapter = new HeroDetailsTabAdapter(
                getActivity().getSupportFragmentManager(), selectedHeroId);
        viewPager = (ViewPager) rootView.findViewById(R.id.fragment_hero_details_pager);
        viewPager.setAdapter(heroDetailsTabAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        for(int i = 0; i < heroDetailsTabAdapter.getCount(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(heroDetailsTabAdapter.getPageTitle(i)));
        }

        return rootView;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) { }

    @Override
    public void onTabReselected(TabLayout.Tab tab) { }
}
