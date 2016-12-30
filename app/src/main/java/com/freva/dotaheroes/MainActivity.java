package com.freva.dotaheroes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.freva.dotaheroes.fragment.HeroListing;

public class MainActivity extends AppCompatActivity {
    private static MainActivity mainActivity;
    private static Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment fragment = new HeroListing();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_main_frame_container, fragment)
                .commit();
    }

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    public static void setIconTitle(int iconResourceId, String title) {
        Bitmap sourceBitmap = BitmapFactory.decodeResource(getMainActivity().getResources(), iconResourceId);
        Drawable drawable = createCircleBitmap(sourceBitmap);
        toolbar.setNavigationIcon(drawable);
        toolbar.setTitle(title);
    }

    public static Drawable createCircleBitmap(Bitmap bmp) {
        bmp = ThumbnailUtils.extractThumbnail(bmp, bmp.getWidth() / 4, bmp.getHeight() / 4, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        RoundedBitmapDrawable roundedBitmap = RoundedBitmapDrawableFactory.create(getMainActivity().getResources(), bmp);
        roundedBitmap.setCornerRadius(Math.max(bmp.getWidth(), bmp.getHeight()) / 2.0f);
        return roundedBitmap.getCurrent();
    }
}
