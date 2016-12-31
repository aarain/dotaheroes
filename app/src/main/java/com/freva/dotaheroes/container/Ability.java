package com.freva.dotaheroes.container;

import java.util.HashMap;
import java.util.Map;

public class Ability {
    private final String id, name, description;
    private final int iconResourceIdentifier;
    private final String mana, cooldown;
    private Map<String, String> affects, details;

    public Ability(String id, String name, int iconResourceIdentifier, String mana, String cooldown,
                   String description, Map<String, String> affects, Map<String, String> details) {
        this.id = id;
        this.name = name;
        this.iconResourceIdentifier = iconResourceIdentifier;
        this.mana = mana;
        this.cooldown = cooldown;
        this.description = description;
        this.affects = affects;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getIconResourceIdentifier() {
        return iconResourceIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public String getMana() {

        return mana;
    }

    public String getCooldown() {

        return cooldown;
    }

    public Map<String, String> getAffects() {

        return new HashMap<>(affects);
    }

    public Map<String, String> getDetails() {

        return new HashMap<>(details);
    }
}
