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
            convertView = inflater.inflate(R.layout.fragment_hero_abilites_item, parent, false);

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

        // If the keys exist, sort in the following order:
        // ABILITY, DAMAGE TYPE, PIERCES SPELL IMMUNITY
        List<String> keys = new ArrayList(ability.getAffects().keySet());
        Collections.sort(keys);
        StringBuilder sbAffects = new StringBuilder();
        for (String key: keys) {
            if (key != null) {
                sbAffects.append(key).append(": ").append(ability.getAffects().get(key)).append("\n");
            }
        }
        viewHolder.affects.setText(sbAffects.toString());

        viewHolder.desc.setText(ability.getDescription());

        StringBuilder sbMana = new StringBuilder();
        sbMana.append("<b>MANA COST:</b> ");
        if (ability.getMana() == null) {
            viewHolder.mana.setText(Html.fromHtml(sbMana.append("N/A").toString()));
        }
        else {
            viewHolder.mana.setText(Html.fromHtml(sbMana.append(ability.getMana()).toString()));
        }

        StringBuilder sbCooldown = new StringBuilder();
        sbCooldown.append("<b>COOLDOWN:</b> ");
        if (ability.getCooldown() == null) {
            viewHolder.cooldown.setText(Html.fromHtml(sbCooldown.append("N/A").toString()));
        }
        else {
            viewHolder.cooldown.setText(Html.fromHtml(sbCooldown.append(ability.getCooldown()).toString()));
        }

        //String strDetails = "<b>%s:</b> %s\n";
        StringBuilder sbDetails = new StringBuilder();
        for (Map.Entry<String, String> entry : ability.getDetails().entrySet()) {
            String htmlDetails = String.format("<b>%s:</b> %s<br/>", entry.getKey(), entry.getValue());
            sbDetails.append(htmlDetails);

            //sbDetails.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
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