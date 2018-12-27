import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;

/**
 * TODO (78): Copy all code below public class Charmander and paste it below.
 *          You will make a few changes to the code to make it appropriate for
 *          Golem. These are listed in order from top to bottom:
 *              - Change the constructor to say Golem instead of Charmander
 *              - Golem has 950 points of health
 *              - Golem's type is Rock
 *              - In the constructor, add a line at the end to set the transparency
 *                of the image of the health bar to 0
 *              - Show text that Golem has fainted when its health bar's value is 
 *                less than or equal to 0
 *                  - When Golem faints, you should be checking if getNewOneCreature at 0
 *                    still has health first
 *                      - You should be switching to Creature 0 if this is the case
 *              - Golem's first attack should do 30 points of damage
 *              - Golem's second attack...
 *                  - if used against an electric type...
 *                      - Should do two times 75 points of damage (DON'T DO THE MATH! Write the math expression)
 *                      - Should display that the attack is super effective at a location of
 *                        half the width of the world and half the height of the world plus 26
 *                  - otherwise, if used against a flying type...
 *                      - Should do 0 damage
 *                      - Should display that the attack has no effect at a location of
 *                        half the width of the world and half the height of the world plus 26
 *                      - Should delay the scenario by 30 act cycles
 *                  - otherwise...
 *                      - Should do 75 points of damage
 *              - In switchCreature...
 *                      - If idx is equal to 0...
 *                          - Change player one to Charmander
 *              
 */
public class Golem extends Creature
{
    /**
     * Constructor for objects of class Golem
     * 
     * @param w is a reference to the world that Charmander gets added to
     * @return An object of type Golem
     */
    public Golem(World w)
    {
        super(950, 1, "Rock");
        getImage().scale(150, 100);
        w.addObject( getHealthBar() , 300, w.getHeight() - 50 );
        getImage().setTransparency(0);
        getHealthBar().getImage().setTransparency(0);
    }
    
    /**
     * Act - do whatever the creature wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        CreatureWorld playerWorld = (CreatureWorld)getWorld();
        if( getHealthBar().getCurrent() <= 0 )
        {
            getWorld().showText("Golem has fainted...", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
            Greenfoot.delay(30);
            if(playerWorld.getNewOneCreature(0).getHealthBar().getCurrent() > 0)
            {
                switchCreature(0);
                playerWorld.setTurnNumber(1);
                getWorld().showText("", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
                getWorld().removeObject(this);
            }
            else if(playerWorld.getNewTwoCreature(2).getHealthBar().getCurrent() > 0)
            {
                switchCreature(2);
                playerWorld.setTurnNumber(1);
                getWorld().showText("", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26); 
                getWorld().removeObject(this);
            }
        }
    }

    /**
     * attack - takes away health from the enemy Creature using one of
     * two predefined attacks.
     * 
     * @param idx is the index of the attack option selected
     * @return Nothing is returned
     */
    public void attack(int idx)
    {
        CreatureWorld playerWorld = (CreatureWorld)getWorld();
        Creature enemy = playerWorld.getPlayerTwo();
        String enemyType = enemy.getType();
        attackAnimation();

        if( idx == 0 )
        {
            enemy.getHealthBar().add( -30 );
        }
        else
        {
            if(enemyType.equalsIgnoreCase("Electric"))
            {
                enemy.getHealthBar().add( -70 * 2 );
                getWorld().showText("The attack was super effective", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
                Greenfoot.delay(30);
            }
            
            else if(enemyType.equalsIgnoreCase("Flying"))
            {
                enemy.getHealthBar().add( -0 );
                getWorld().showText("The attack has no effect", getWorld().getWidth()/2, getWorld().getHeight()/2+26);
                Greenfoot.delay(30);
            }
            else
            {
                enemy.getHealthBar().add( -75);
            }
        }

        playerWorld.setTurnNumber(2);
    }

    /**
     * attackAnimation -  Helps with the animation and movement of the 
     * creatures. It gives them a realistic attacking animation.
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    private void attackAnimation()
    {
        int originalX = getX();
        int originalY = getY();
        for(int i = 0; i < 15; i++)
        {
            setLocation(getX() + 1, getY() - 2);
            Greenfoot.delay(1);
        }
        setLocation(originalX, originalY);
    }

    /**
     * switchCreature - switches the creature depending on the players turn 
     * and the creature they have initialized.
     * 
     * @param idx is the index of the attack option selected
     * @return Nothing is returned
     */
    public void switchCreature(int idx)
    {
        CreatureWorld playerWorld = (CreatureWorld)getWorld();
        Creature switchCreature = playerWorld.getNewOneCreature(idx);
        if(switchCreature.getHealthBar().getCurrent() <= 0)
        {
            JOptionPane.showMessageDialog( null , " This creature has fainted, please choose another one ");
        }
        else
        {
            while(getX() > 0)
            {
                setLocation(getX() - 5, getY());
                Greenfoot.delay(2);
            }
            getImage().setTransparency(0);
            getHealthBar().getImage().setTransparency(0);
            if(idx == 0)
            {
                playerWorld.changePlayerOne("Charmander");
            }
            else
            {
                playerWorld.changePlayerOne("Ivysaur");
            }
            switchCreature.switchedIn();
            playerWorld.setTurnNumber(2);
        }
    }
    
    /**
     * switchedIn - sets the Transparency of the picture to blank and
     * sets the location of the creature to the right adding an animation look
     * to the creature switching in.
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void switchedIn()
    {
        getImage().setTransparency(255);
        getHealthBar().getImage().setTransparency(255);
        while(getX() < getImage().getWidth()/2)
        {
            setLocation(getX() + 5, getY());
            Greenfoot.delay(2);
        }
    }
}
