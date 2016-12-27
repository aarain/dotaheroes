package com.freva.dotaheroes.container;

import com.freva.dotaheroes.data.Abilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Hero {
    private final String id, name;
    private final int iconResourceIdentifier;
    private final AttackType attackType;
    private final Set<Role> roles = new HashSet<>();
    private final List<Ability> abilities = new ArrayList<>();
    private final String agility, intelligence, strength;
    private final String damage, armor, speed;

    public Hero(String id, String name, int iconResourceIdentifier, String attackType,
                List<String> roles, List<String> abilities, Map<String, String> stats) {
        this.id = id;
        this.name = name;
        this.iconResourceIdentifier = iconResourceIdentifier;
        this.attackType = AttackType.valueOf(attackType.toUpperCase());
        this.agility = stats.get("agi");
        this.intelligence = stats.get("int");
        this.strength = stats.get("str");
        this.damage = stats.get("attack");
        this.armor = stats.get("defence");
        this.speed = stats.get("speed");

        for (String role : roles) this.roles.add(Role.valueOf(role.toUpperCase()));
        for (String ability : abilities) this.abilities.add(Abilities.getAbility(ability));
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

    public AttackType getAttackType() {
        return attackType;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public String getAgility() {
        return agility;
    }

    public String getIntelligence() {
        return intelligence;
    }

    public String getStrength() {
        return strength;
    }

    public String getDamage() {
        return damage;
    }

    public String getArmor() {
        return armor;
    }

    public String getSpeed() {
        return speed;
    }


    public enum AttackType { MELEE, RANGED }

    public enum Role { CARRY, DURABLE, SUPPORT, DISABLER, INITIATOR, NUKER, ESCAPE, JUNGLER, PUSHER }
}