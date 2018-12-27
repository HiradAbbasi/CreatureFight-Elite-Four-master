import greenfoot.*;

/**
 * Write a description of class PlayerCommands here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SwitchCommands implements MenuCommands
{
    /**
     * execute executes the appropriate action based on which kind 
     * of menu is being used, which option is selected, and for which creature
     * the option is selected
     * 
     * @param idx is the index of the option chosen
     * @param c is the creature that is currently in play
     * @return Nothing is returned
     */
    public void execute(int idx, Creature c)
    {
         //TODO (4): If c is an instance of Charmander or c is an instance of Pikachu (use the instanceof operator)...
         if(c instanceof Charmander || c instanceof Pikachu )
         {
             //TODO (4a): Call the switchCreature method of c using a parameter of idx+1
             c.switchCreature(idx+1);
         }
         //TODO (4b): Otherwise, if c is an instance of Golem or c is an instance of Lapra (use the instanceof operator)...
         else if(c instanceof Golem || c instanceof Lapras )
         {
             //TODO (4c): If idx is 1...
             if(idx == 1)
             {
                 //TODO (4d): Call the switchCreature method of c using a parameter of idx+1
                 c.switchCreature(idx+1);
             }   
         
             //TODO (4e): Otherwise...
             else
             {
                 //TODO (4f): Call the switchCreature method of c using a parameter of idx
                 c.switchCreature(idx);
             }
         }
         //TODO (4g): Otherwise...
         else
         {
              //TODO (4h): Call the switchCreature method of c using a parameter of idx 
              c.switchCreature(idx);
         }
    }
}
