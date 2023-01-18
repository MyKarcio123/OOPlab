package agh.ics.oop.spells;

abstract public class AbstractSpell {
    private int healthDamage;
    private int manaDamage;

    public AbstractSpell(int healthDamage, int manaDamage){
        this.healthDamage = healthDamage;
        this.manaDamage = manaDamage;
    }
}
