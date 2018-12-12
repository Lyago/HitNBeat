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
        Actor metronome;
        metronome = getObjectsInRange(500, Metronome.class).get(0);
        if(this.getX() <= metronome.getX()){
            World world = this.getWorld();
            world.removeObject(this);
        }else{
            move(-speed);
        }
    }
}
