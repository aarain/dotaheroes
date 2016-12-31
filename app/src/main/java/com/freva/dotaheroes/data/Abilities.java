package com.freva.dotaheroes.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freva.dotaheroes.MainActivity;
import com.freva.dotaheroes.fragment.HeroListing;
import com.freva.dotaheroes.R;
import com.freva.dotaheroes.container.Ability;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Abilities {
    private static final Map<String, Ability> abilities = new HashMap<>();

    static {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Map<String, Map<String, Object>> abilitiesRaw = mapper.readValue(
                    MainActivity.getMainActivity().getResources().openRawResource(R.raw.abilities), Map.class);

            for (Map.Entry<String, Map<String, Object>> abilityRaw: abilitiesRaw.entrySet()) {
                int iconResourceId = MainActivity.getMainActivity().getResources().getIdentifier(
                        "icon_ability_" + abilityRaw.getKey(), "drawable", MainActivity.getMainActivity().getPackageName());

                Ability ability = new Ability(abilityRaw.getKey(), (String) abilityRaw.getValue().get("name"),
                        iconResourceId,
                        (String) abilityRaw.getValue().get("mana"),
                        (String) abilityRaw.getValue().get("cooldown"),
                        (Map<String, String>) abilityRaw.getValue().get("affects"),
                        (Map<String, String>) abilityRaw.getValue().get("details"));

                abilities.put(abilityRaw.getKey(), ability);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Ability> getAbilities() {
        return new ArrayList<>(abilities.values());
    }

    public static Ability getAbility(String abilityId) {
        return abilities.get(abilityId);
    }
}
