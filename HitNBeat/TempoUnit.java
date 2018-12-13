import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TempoUnit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TempoUnit extends Actor
{
    //travel speed of TempoUnits, 5 sets the unit to take 60 frames to get in center
    protected int speed = 7;
    private int startLocation;
    public TempoUnit(int startLocation)
    {
        startLocation = startLocation;
        
    }
    /**
     * Act - do whatever the TempoUnit wants to do. This method is called whenever
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
    public boolean isActionTime()
    {
        if(this.isTouching(Metronome.class)){
            return true;

        }
        return false;
    }
}
