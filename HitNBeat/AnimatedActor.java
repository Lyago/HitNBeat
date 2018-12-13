import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AnimatedActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AnimatedActor extends Actor
{   
    private GreenfootImage[] sprites_idle;
    private GreenfootImage[] sprites_quick_attack;    
    private GreenfootImage[] sprites_strong_attack;
    private GreenfootImage[] sprites_ripose;
    private GreenfootImage[] sprites_flinch;
    
    private GreenfootImage[] sprites;
    private int spriteCount = 0;
    
    private int frameCounter = 0;
    private int animationFrameCounter = 0;
    
    public AnimatedActor(int frames)
    {
        for(PlayerStates state : PlayerStates.values())
        {
            loadWalkingSprites(state,getClass().getSimpleName(),"png",frames);
        }
        
        this.setImage(sprites_idle[0]);
        sprites = sprites_idle;
    }
    
    public AnimatedActor()
    {
        for(PlayerStates state : PlayerStates.values())
        {
            loadWalkingSprites(state,getClass().getSimpleName(),"png",4);
        }
        
        this.setImage(sprites_idle[0]);
        sprites = sprites_idle;
    }
    protected void loadWalkingSprites(PlayerStates state, String className, String fileType, int count)
    {
        GreenfootImage[] spritesAux = new GreenfootImage[count];
        
        for (int i=0 ; i<count ; i++)
        {
            String file = className+"-"+state.toString()+"-0"+(i)+"."+fileType;
            spritesAux[i] = new GreenfootImage(file);
            
            spritesAux[i].scale(spritesAux[i].getWidth() + 150, spritesAux[i].getHeight() + 150);
        }
        
        switch (state)
        {
            case IDLE:
                sprites_idle = spritesAux;
                break;
            case QUICKATTACKING:
                sprites_quick_attack = spritesAux;
                break;
            case STRONGATTACKING:
                sprites_strong_attack = spritesAux;
                break;
            case RIPOSTING:
                sprites_ripose = spritesAux;
                break;
            case FLINCHING:
                sprites_flinch = spritesAux;
                break;
            
        }
    }
    /**
     * Act - do whatever the AnimatedActor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    boolean moveNow;
    
    public void act() 
    {   
        MyWorld world = (MyWorld)this.getWorld();
        int frameDelay = 4;
        
        
            if(animationFrameCounter++ > 8){           
            
            if (Greenfoot.getKey() == null)
            {
                sprites = sprites_idle;
                moveNow = true;
                frameDelay = 9;
            }
            
            if (Greenfoot.isKeyDown("right"))
            {
                sprites = sprites_quick_attack;
                moveNow = true;
                animationFrameCounter = 0;
            }
            else if (Greenfoot.isKeyDown("up"))
            {
                sprites = sprites_strong_attack;
                moveNow = true;
                animationFrameCounter = 0;
            }
            else if (Greenfoot.isKeyDown("left"))
            {
                sprites = sprites_ripose;
                moveNow = true;
                animationFrameCounter = 0;
            }
            else if (false)
            {
                sprites = sprites_flinch;    
                moveNow = true;
                animationFrameCounter = 0;
            }
        }

        if (moveNow)
        {
            if (spriteCount >= sprites.length)
            {
                spriteCount = 0;
            }
            if(frameCounter++ < frameDelay){
                this.setImage(sprites[spriteCount]);
            }else{
                this.setImage(sprites[spriteCount++]);
                frameCounter = 0;
            }        
        }
    }
    
}
