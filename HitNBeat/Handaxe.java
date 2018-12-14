import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Handaxe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Handaxe extends TempoUnit
{
    
    public Handaxe(int startLocation){
        this.startLocation = startLocation;
    }
    /**
     * Act - do whatever the Handaxe wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(-speed);
    }    
}
