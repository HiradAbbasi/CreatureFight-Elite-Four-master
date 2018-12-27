import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;

/**
 * TODO (152): Copy all code below public class Pikachu and paste it below.
 *          You will make a few changes to the code to make it appropriate for
 *          Lapras. These are listed in order from top to bottom:
 *              - Change the constructor to say Lapras instead of Pikachu
 *              - Lapras has 900 points of health
 *              - Lapras's type is Water
 *              - In the constructor, add a line at the end to set the transparency
 *                of the image of the health bar to 0
 *              - Show text that Lapras has fainted when its health bar's value is 
 *                less than or equal to 0
 *                  - When Lapras faints, you should be checking if getNewTwoCreature at 0
 *                    still has health first
 *                      - You should be switching to Creature 0 if this is the case
 *              - Lapras's second attack...
 *                  - if used against an fire type...
 *                      - Should do two times 100 points of damage (DON'T DO THE MATH! Write the math expression)
 *                      - Should display that the attack is super effective at a location of
 *                        half the width of the world and half the height of the world plus 26
 *                  - otherwise, if used against a rock type...
 *                      - Should do two times 100 points of damage (DON'T DO THE MATH! Write the math expression)
 *                      - Should display that the attack is super effective at a location of
 *                        half the width of the world and half the height of the world plus 26
 *                  - otherwise, if used against a grass type...
 *                      - Should do half of 100 points of damage (DON'T DO THE MATH! Write the math expression)
 *                      - Should display that the attack is not very effective at a location of
 *                        half the width of the world and half the height of the world plus 26
 *                      - Should delay the scenario by 30 act cycles
 *                  - otherwise...
 *                      - Should do 100 points of damage
 *              - In switchCreature...
 *                      - If idx is equal to 0...
 *                          - Change player two to Pikachu
 *              
 */
public class Lapras extends Creature
{
    /**
     * Constructor for objects of class Lapras
     * 
     * @param w is a reference to the world that Charmander gets added to
     * @return An object of type Lapras
     */
    public Lapras(World w)
    {
        super(900, 2, "Water");
        getImage().scale(150, 100);
        w.addObject( getHealthBar() , 100, 25 );
        getHealthBar().getImage().setTransparency(0);
    }
    
    /**
     * Act - do whatever the Lapras wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        CreatureWorld playerWorld = (CreatureWorld)getWorld();

        if( getHealthBar().getCurrent() <= 0 )
        {
            getWorld().showText("Lapras has fainted...", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
            Greenfoot.delay(30);
            if(playerWorld.getNewTwoCreature(0).getHealthBar().getCurrent() > 0)
            {
                switchCreature(0);
                playerWorld.setTurnNumber(2);
                getWorld().showText("", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
                getWorld().removeObject(this);
            }
            else if( playerWorld.getNewTwoCreature(2).getHealthBar().getCurrent() > 0)
            {
                switchCreature(2);
                playerWorld.setTurnNumber(2);
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
        Creature enemy = playerWorld.getPlayerOne();
        String enemyType = enemy.getType();
        attackAnimation();

        if( idx == 0 )
        {
            enemy.getHealthBar().add( -30 );
        }
        else
        {
            if(enemyType.equalsIgnoreCase("Fire"))
            {
                enemy.getHealthBar().add( -100 * 2 );
                getWorld().showText("The attack was super effective", getWorld().getWidth()/2, getWorld().getHeight()/2 + 26);
                Greenfoot.delay(30);
            }
            if(enemyType.equalsIgnoreCase("Rock"))
            {
                enemy.getHealthBar().add( -100*2 );
                getWorld().showText("The attack was super effective", getWorld().getWidth()/2, getWorld().getHeight()/2+26);
                Greenfoot.delay(30);
            }
            else if(enemyType.equalsIgnoreCase("Grass"))
            {
                enemy.getHealthBar().add( -100/2 );
                getWorld().showText("The attack was not very effective", getWorld().getWidth()/2, getWorld().getHeight()/2+26);
                Greenfoot.delay(30);
            }
            else
            {
                enemy.getHealthBar().add( -100 );
            }
        }
        playerWorld.setTurnNumber(1);
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
            setLocation(getX() - 1,  getY() +2);
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
        Creature switchCreature = playerWorld.getNewTwoCreature(idx);
        if(switchCreature.getHealthBar().getCurrent() <= 0)
        {
            JOptionPane.showMessageDialog( null , " This creature has fainted, please choose another one ");
        }
        else
        {
            while(getX() < getWorld().getWidth()-1)
            {
                setLocation(getX() + 5, getY());
                Greenfoot.delay(2);
            }
            getImage().setTransparency(0);
            getHealthBar().getImage().setTransparency(0);
            if(idx == 0)
            {
                playerWorld.changePlayerTwo("Pikachu");
            }
            else
            {
                playerWorld.changePlayerTwo("Pidgeot");
            }
            switchCreature.switchedIn();
            playerWorld.setTurnNumber(1);
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
        while(getX() > getWorld().getWidth() - getImage().getWidth()/2)
        {
            setLocation(getX() - 5, getY());
            Greenfoot.delay(2);
        }
    }
}
