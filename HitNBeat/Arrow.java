import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RigthTempoUnit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arrow extends TempoUnit
{
    public Arrow(int startLocation){
        this.startLocation = startLocation;
    }
    /**
     * Act - do whatever the RigthTempoUnit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(-speed);
    } 

}
