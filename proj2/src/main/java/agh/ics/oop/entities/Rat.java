package agh.ics.oop.entities;

import agh.ics.oop.items.AbstractWeapon;
import agh.ics.oop.spells.AbstractSpell;

import java.util.ArrayList;

public class Rat extends AbstractEnemy{
    //Szczur
    //TODO szczur values trzeba wymyślić
    //zdrowie - mało
    //mana - mało
    //broń- no nie ma raczej
    //spelllist - coś z tym truciem przy gryxieniu
    //staty:
    //     wapon attack - niskie jakieś
    //      spell attack - niskie jakieś
    //     speed niskie może, albo może wysokie właśnie
    public Rat (float health, float mana, float maxHealth, float maxMana, AbstractWeapon weapon,
                          ArrayList<AbstractSpell> spellList, int weaponAttack, int spellAttack, int speed){

        super(health, mana, maxHealth, maxMana, weapon, spellList,  weaponAttack, spellAttack, speed);
    }
}
