package agh.ics.oop;

import java.util.ArrayList;

public class Player extends AbstractEntity {

    public Player(float health, float mana, float maxHealth, float maxMana, AbstractWeapon weapon,
                  ArrayList<AbstractSpell> spellList, int weaponAttack, int spellAttack, int speed) {

        super(health, mana, maxHealth, maxMana, weapon, spellList, weaponAttack, spellAttack, speed);
    }


    @Override
    public void chooseAttack(AbstractEntity target) {
    }
}
