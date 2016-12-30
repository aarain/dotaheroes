package com.freva.dotaheroes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.freva.dotaheroes.fragment.HeroListing;

public class MainActivity extends AppCompatActivity {
    private static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new HeroListing();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_main_frame_container, fragment)
                .commit();
    }

    public static MainActivity getMainActivity() {
        return mainActivity;
    }
}
