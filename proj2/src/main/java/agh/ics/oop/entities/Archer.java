package agh.ics.oop.entities;

import agh.ics.oop.items.AbstractWeapon;
import agh.ics.oop.items.Cane;
import agh.ics.oop.spells.AbstractSpell;

import java.util.ArrayList;

public class Archer extends Player{
    //TODO walues
    public Archer() {
        super(1, 1, 2, 2, new Cane(), new ArrayList<>(), 3, 3, 3);
    }
}
