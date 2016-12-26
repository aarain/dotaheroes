package com.freva.dotaheroes.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.freva.dotaheroes.R;
import com.freva.dotaheroes.container.Hero;

import java.util.ArrayList;
import java.util.List;

public class HeroListingAdapter extends ArrayAdapter<Hero> {
    private List<Hero> originalHeroList, filteredHeroList;
    private Filter heroFilter;

    public HeroListingAdapter(Context context, List<Hero> heroList) {
        super(context, 0, heroList);

        originalHeroList = filteredHeroList = heroList;
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
        return filteredHeroList.size();
    }

    @Override
    public Hero getItem(int position) {
        return filteredHeroList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        if (heroFilter == null)
            heroFilter = new HeroFilter();

        return heroFilter;
    }

    private class HeroFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Hero> filteredHeroList = new ArrayList<>();
            for (Hero hero : originalHeroList) {
                if (hero.getName().contains(constraint.toString())) {
                    filteredHeroList.add(hero);
                }
            }

            results.values = filteredHeroList;
            results.count = filteredHeroList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredHeroList = (List<Hero>) results.values;
            notifyDataSetChanged();
        }
    }

    private static class ViewHolder {
        private TextView name;
        private ImageView icon;
    }
}