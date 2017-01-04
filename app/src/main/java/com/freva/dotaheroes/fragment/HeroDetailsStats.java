package com.freva.dotaheroes.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.freva.dotaheroes.R;
import com.freva.dotaheroes.container.Hero;
import com.freva.dotaheroes.data.Heroes;

import java.util.ArrayList;
import java.util.List;

public class HeroDetailsStats extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_hero_details_stats, container, false);

        Hero selectedHero = Heroes.getHero(getArguments().getString("HERO_ID"));

        // Display hero image
        ImageView imageView = (ImageView) rootView.findViewById(R.id.fragment_hero_details_stats_image);
        imageView.setImageResource(selectedHero.getIconResourceIdentifier());


        // Set hero roles
        List<String> roles = new ArrayList<>();
        for (Hero.Role role : selectedHero.getRoles().keySet()) {
            roles.add(role.toString());
        }

        TextView textViewRoles = (TextView) rootView.findViewById(R.id.fragment_hero_details_stats_roles);
        textViewRoles.setText(TextUtils.join(" - ", roles));

        // Set hero attack type
        TextView attackTypeText = (TextView) rootView.findViewById(R.id.fragment_hero_details_stats_type);
        attackTypeText.setText(selectedHero.getAttackType().toString());

        // Set hero strength start and gain
        TextView strengthText = (TextView) rootView.findViewById(R.id.fragment_hero_details_stats_str);
        strengthText.setText(selectedHero.getStrength());
        // Set hero agility start and gain
        TextView agilityText = (TextView) rootView.findViewById(R.id.fragment_hero_details_stats_agi);
        agilityText.setText(selectedHero.getAgility());
        // Set hero intelligence start and gain
        TextView intelligenceText = (TextView) rootView.findViewById(R.id.fragment_hero_details_stats_int);
        intelligenceText.setText(selectedHero.getIntelligence());

        // Set hero attack damage
        TextView attackText = (TextView) rootView.findViewById(R.id.fragment_hero_details_stats_att);
        attackText.setText(selectedHero.getDamage());
        // Set hero armour
        TextView armorText = (TextView) rootView.findViewById(R.id.fragment_hero_details_stats_def);
        armorText.setText(selectedHero.getArmor());
        // Set hero move speed
        TextView speedText = (TextView) rootView.findViewById(R.id.fragment_hero_details_stats_spd);
        speedText.setText(selectedHero.getSpeed());

        return rootView;
    }
}
