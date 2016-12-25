package com.freva.dotaheroes.container;

public class Hero {
    private final String name;
    private final int iconResourceIdentifier;

    public Hero(String name, int iconResourceIdentifier) {
        this.name = name;
        this.iconResourceIdentifier = iconResourceIdentifier;
    }

    public String getName() {
        return name;
    }

    public int getIconResourceIdentifier() {
        return iconResourceIdentifier;
    }
}