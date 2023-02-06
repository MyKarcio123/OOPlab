package agh.ics.oop.entities;

import agh.ics.oop.items.AbstractWeapon;
import agh.ics.oop.spells.AbstractSpell;

import java.util.ArrayList;

public class Mag extends Player {
    //TODO walues
    public Mag(AbstractWeapon weapon, ArrayList<AbstractSpell> spellList,) {
        super(1, 1, 2, 2, weapon , spellList, 3, 3, 3);
    }
}
