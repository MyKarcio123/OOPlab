package agh.ics.oop;

import java.util.ArrayList;

public class AbstractEnemy extends AbstractEntity {
    private boolean firstAttack = true;

    public AbstractEnemy (float health, float mana, float maxHealth, float maxMana, AbstractWeapon weapon,
                          ArrayList<AbstractSpell> spellList, int weaponAttack, int spellAttack, int speed){

        super(health, mana, maxHealth, maxMana, weapon, spellList,  weaponAttack, spellAttack, speed);
    }

    public void chooseAttack(AbstractEntity target){
        int healthDamage = 0;
        int manaDamage = 0;
        if (firstAttack){
            firstAttack = false;
            //TODO wybieranie ataku tego trwałego
        }
        else{
            //TODO wybieranie najlepszego
        }
        target.receiveDamage(healthDamage, manaDamage);
    }


}
