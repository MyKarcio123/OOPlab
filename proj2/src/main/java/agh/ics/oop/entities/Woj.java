package agh.ics.oop.entities;

import agh.ics.oop.items.AbstractWeapon;
import agh.ics.oop.spells.AbstractSpell;

import java.util.ArrayList;

public class Woj extends Player {
    //TODO walues
    public Woj(float health, float mana, float maxHealth, float maxMana, AbstractWeapon weapon, ArrayList<AbstractSpell> spellList, int weaponAttack, int spellAttack, int speed) {
        super(health, mana, maxHealth, maxMana, weapon, spellList, weaponAttack, spellAttack, speed);
    }
}
