package agh.ics.oop;

import java.util.ArrayList;

abstract public class AbstractEntity {
    private Vector2d position;
    private float health;
    private float mana;
    private final float maxHealth;
    private final float maxMana;
    protected AbstractWeapon weapon;
    private ArrayList<AbstractSpell> spellList;
    private int weaponAttackStat;
    private int spellAttackStat;
    private int speedStat;
    private int damageStat;

    public AbstractEntity(float health, float mana, float maxHealth, float maxMana, AbstractWeapon weapon,
                         ArrayList<AbstractSpell> spellList, int weaponAttack, int spellAttack, int speed) {
        this.health = health;
        this.mana = mana;
        this.maxHealth = maxHealth;
        this.maxMana = maxMana;
        this.weapon = weapon;
        this.spellList = spellList;
        this.weaponAttackStat = weaponAttack;
        this.spellAttackStat = spellAttack;
        this.speedStat = speed;
    }

    public int getSpeed() {
        return speedStat;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void setMana(float mana) {
        this.mana = mana;
    }

    public void setWeaponAttack(int weaponAttack) {
        this.weaponAttackStat = weaponAttack;
    }

    public void setSpellAttack(int spellAttack) {
        this.spellAttackStat = spellAttack;
    }

    public void setSpeed(int speed) {
        this.speedStat = speed;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void receiveDamage(int healthDamage, int manaDamage){
        this.health -= healthDamage*(100/(float)(100+this.damageStat));
        this.mana -= manaDamage*(100/(float)(100+this.damageStat));
    }

    abstract public void chooseAttack(AbstractEntity target);

    public void generateAttack(AbstractEntity target){
        chooseAttack(target);
    }
}
