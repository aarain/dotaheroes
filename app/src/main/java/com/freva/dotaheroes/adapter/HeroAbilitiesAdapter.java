package com.freva.dotaheroes.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.freva.dotaheroes.R;
import com.freva.dotaheroes.container.Ability;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class HeroAbilitiesAdapter extends ArrayAdapter<Ability> {
    private static final String mapFormat = "<b>%s:</b> %s<br/>";
    private List<Ability> originalAbilityList;

    public HeroAbilitiesAdapter(Context context, List<Ability> abilityList) {
        super(context, 0, abilityList);

        originalAbilityList = abilityList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Ability ability = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.fragment_hero_details_abilites_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.fragment_hero_details_ability_name);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.fragment_hero_details_ability_icon);
            viewHolder.affects = (TextView) convertView.findViewById(R.id.fragment_hero_details_ability_affects);
            viewHolder.desc = (TextView) convertView.findViewById(R.id.fragment_hero_details_ability_description);
            viewHolder.mana = (TextView) convertView.findViewById(R.id.fragment_hero_details_ability_mana);
            viewHolder.cooldown = (TextView) convertView.findViewById(R.id.fragment_hero_details_ability_cooldown);
            viewHolder.details = (TextView) convertView.findViewById(R.id.fragment_hero_details_ability_details);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(ability.getName());
        viewHolder.icon.setImageResource(ability.getIconResourceIdentifier());

        List<String> keys = new ArrayList<>(ability.getAffects().keySet());
        Collections.sort(keys);
        StringBuilder sbAffects = new StringBuilder();
        for (String key: keys) {
            String htmlDetails = String.format(mapFormat, key, ability.getAffects().get(key));
            sbAffects.append(htmlDetails);
        }
        viewHolder.affects.setText(Html.fromHtml(sbAffects.toString()));
        viewHolder.desc.setText(ability.getDescription());

        String manaText = ability.getMana() != null ? ability.getMana() : "N/A";
        viewHolder.mana.setText(Html.fromHtml("<b>MANA COST:</b> " + manaText));

        String cooldownText = ability.getCooldown() != null ? ability.getCooldown() : "N/A";
        viewHolder.cooldown.setText(Html.fromHtml("<b>COOLDOWN:</b> " + cooldownText));

        StringBuilder sbDetails = new StringBuilder();
        for (Map.Entry<String, String> entry : ability.getDetails().entrySet()) {
            String htmlDetails = String.format(mapFormat, entry.getKey(), entry.getValue());
            sbDetails.append(htmlDetails);
        }
        viewHolder.details.setText(Html.fromHtml(sbDetails.toString()));

        return convertView;
    }

    @Override
    public int getCount() {
        return originalAbilityList.size();
    }

    @Override
    public Ability getItem(int position) {
        return originalAbilityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        // First row
        private TextView name;
        // Second row
        private ImageView icon;
        private TextView affects;
        // Third row
        private TextView desc;
        // Fourth row
        private TextView mana;
        private TextView cooldown;
        // Fifth row
        private TextView details;
    }
}