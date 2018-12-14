import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AnimatedActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AnimatedActor extends Actor
{   
    protected PlayerStates state = PlayerStates.IDLE;
    private GreenfootImage[] sprites_idle;
    private GreenfootImage[] sprites_quick_attack;    
    private GreenfootImage[] sprites_strong_attack;
    private GreenfootImage[] sprites_ripose;
    private GreenfootImage[] sprites_flinch;

    private GreenfootImage[] sprites;
    private int spriteCount = 0;

    private int frameCounter = 0;
    private int animationFrameCounter = 0;
    //number of frames that a single sprite will be on screen on animations
    private int frameDelay = 1;

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
    public void act() 
    {  
        if(animationFrameCounter++ <= 6){
            if (spriteCount >= sprites.length)
            {
                spriteCount = 0;
            }
            //delay for repeating sprites in multiple frames, slowing down the animation
            if(frameCounter++ < frameDelay){
                this.setImage(sprites[spriteCount]);
            }else{
                this.setImage(sprites[spriteCount++]);
                frameCounter = 0;
            }  
        }  
    }

    public void actIdle(){
        sprites = sprites_idle;
        animationFrameCounter = 0;
        frameDelay = 4;

    }

    public void actPenalized(){
        sprites = sprites_flinch;    
        animationFrameCounter = 0;   
        frameDelay = 4;
    }

    public void actQuickAttack(){
        sprites = sprites_quick_attack;
        animationFrameCounter = 0;
        frameDelay = 1;

    }

    public void actStrongAttack(){
        sprites = sprites_strong_attack;
        animationFrameCounter = 0;
        frameDelay = 1;
    }

    public void actRiposte(){
        sprites = sprites_ripose;
        animationFrameCounter = 0;
        frameDelay = 1;
    }
}
