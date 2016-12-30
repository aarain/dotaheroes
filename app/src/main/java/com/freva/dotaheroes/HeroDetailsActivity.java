package com.freva.dotaheroes;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.freva.dotaheroes.adapter.HeroDetailsTabAdapter;
import com.freva.dotaheroes.data.Heroes;

public class HeroDetailsActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_details);

        String selectedHeroId = getIntent().getStringExtra("HERO_ID");
        setTitle(Heroes.getHero(selectedHeroId).getName());

        TabLayout tabLayout = (TabLayout) findViewById(R.id.activity_hero_details_tab_layout);
        tabLayout.addOnTabSelectedListener(this);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        HeroDetailsTabAdapter heroDetailsTabAdapter = new HeroDetailsTabAdapter(
                getSupportFragmentManager(), selectedHeroId);
        viewPager = (ViewPager) findViewById(R.id.activity_hero_details_pager);
        viewPager.setAdapter(heroDetailsTabAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        for(int i = 0; i < heroDetailsTabAdapter.getCount(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(heroDetailsTabAdapter.getPageTitle(i)));
        }
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
