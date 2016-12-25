package com.freva.dotaheroes.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.freva.dotaheroes.R;
import com.freva.dotaheroes.container.Hero;

import java.util.List;

public class HeroListingAdapter extends ArrayAdapter<Hero> {
    private List<Hero> originalHeroList;

    public HeroListingAdapter(Context context, List<Hero> heroList) {
        super(context, 0, heroList);

        originalHeroList = heroList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Hero hero = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.dota_hero_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.hero_list_item_name_text);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.hero_list_item_icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(hero.getName());
        viewHolder.icon.setImageResource(hero.getIconResourceIdentifier());

        return convertView;
    }


    @Override
    public int getCount() {
        return originalHeroList.size();
    }

    @Override
    public Hero getItem(int position) {
        return originalHeroList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private static class ViewHolder {
        private TextView name;
        private ImageView icon;
    }
}