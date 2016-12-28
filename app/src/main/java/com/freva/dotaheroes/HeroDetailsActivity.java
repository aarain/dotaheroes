package com.freva.dotaheroes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.freva.dotaheroes.container.Hero;
import com.freva.dotaheroes.data.Heroes;

public class HeroDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_details);

        Hero selectedHero = Heroes.getHero(getIntent().getStringExtra("HERO_ID"));
        TextView textView = (TextView) findViewById(R.id.test);
        textView.setText("Selected: " + selectedHero.getName());
    }
}
