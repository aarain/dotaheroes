package com.freva.dotaheroes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.freva.dotaheroes.container.Hero;
import com.freva.dotaheroes.data.Heroes;

import java.util.ArrayList;
import java.util.List;

public class HeroDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_details);

        // Set page title to the hero name
        Hero selectedHero = Heroes.getHero(getIntent().getStringExtra("HERO_ID"));
        setTitle(selectedHero.getName());

        // Display hero image
        ImageView imageView = (ImageView) findViewById(R.id.activity_hero_details_image);
        imageView.setImageResource(selectedHero.getIconResourceIdentifier());


        // Set hero roles
        List<String> roles = new ArrayList<>();
        for (Hero.Role role : selectedHero.getRoles()) {
            roles.add(role.toString());
        }

        TextView textViewRoles = (TextView) findViewById(R.id.activity_hero_details_roles);
        textViewRoles.setText(TextUtils.join(" - ", roles));

        // Set hero strength start and gain
        String attackType = selectedHero.getAttackType().toString();
        TextView attackTypeText = (TextView) findViewById(R.id.activity_hero_details_type);
        attackTypeText.setText(attackType);

        // Set hero strength start and gain
        TextView strengthText = (TextView) findViewById(R.id.activity_hero_details_str);
        strengthText.setText(selectedHero.getStrength());
        // Set hero agility start and gain
        TextView agilityText = (TextView) findViewById(R.id.activity_hero_details_agi);
        agilityText.setText(selectedHero.getAgility());
        // Set hero intelligence start and gain
        TextView intelligenceText = (TextView) findViewById(R.id.activity_hero_details_int);
        intelligenceText.setText(selectedHero.getIntelligence());
        // Set hero attack damage
        TextView attackText = (TextView) findViewById(R.id.activity_hero_details_att);
        attackText.setText(selectedHero.getDamage());
        // Set hero armour
        TextView armorText = (TextView) findViewById(R.id.activity_hero_details_def);
        armorText.setText(selectedHero.getArmor());
        // Set hero move speed
        TextView speedText = (TextView) findViewById(R.id.activity_hero_details_spd);
        speedText.setText(selectedHero.getSpeed());
    }
}
