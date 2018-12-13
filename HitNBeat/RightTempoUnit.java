import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RigthTempoUnit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RightTempoUnit extends TempoUnit
{
    public RightTempoUnit(int startLocation){
        super(startLocation);
    }
    /**
     * Act - do whatever the RigthTempoUnit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       createTempoUnit();
    } 
    public void createTempoUnit(){
        adventurer adventurer = getObjectsInRange(500, adventurer.class).get(0);
        if(this.getX() <= adventurer.getX()){
            World world = this.getWorld();
            world.removeObject(this);
        }else{
            move(-speed);
        }
    }
}
