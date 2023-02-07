package agh.ics.oop.entities;

import agh.ics.oop.Direction;
import agh.ics.oop.Vector2d;
import agh.ics.oop.entities.AbstractEntity;
import agh.ics.oop.items.AbstractWeapon;
import agh.ics.oop.spells.AbstractSpell;

import java.util.ArrayList;

public class Player extends AbstractEntity {

    private Vector2d position;
    private Direction dir = Direction.NORTH;
    public Player(float health, float mana, float maxHealth, float maxMana, AbstractWeapon weapon,
                  ArrayList<AbstractSpell> spellList, int weaponAttack, int spellAttack, int speed) {

        super(health, mana, maxHealth, maxMana, weapon, spellList, weaponAttack, spellAttack, speed);
    }
    public Player(float health) {
        super(health);
    }


    @Override
    public void chooseAttack(AbstractEntity target) {
    }
    public void setPosition(Vector2d position){
        this.position = position;
    }
    public void setDirection(Direction dir){
        this.dir = dir;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    public Direction getDir() {
        return dir;
    }
}
