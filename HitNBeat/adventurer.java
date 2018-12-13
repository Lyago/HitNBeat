import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class adventurer extends AnimatedActor
{
    private int penaltyFrames = 30;
    private int frameCounter = 0;
    private int bufferInputCoolDown = 24;
    private String quickAttack;
    private String strongAttack;
    private String riposte;
    
    public adventurer(String quickAttack, String strongAttack, String riposte, int animationFrames){
        super(animationFrames);
        
        this.quickAttack = quickAttack;
        this.strongAttack = strongAttack;
        this.riposte = riposte;
        

    }
    public boolean isActing(){
        if(this.state == PlayerStates.IDLE || 
        this.state == PlayerStates.FLINCHING){
            return false;
        }
        return true;
    }
    public void resetPenaltyFrames(){
        this.penaltyFrames = 30;
    }
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        MyWorld world = (MyWorld)this.getWorld();
        //calls animation, default: Idle
        super.act();
        //setup states, which will determine which animation will be run
        switch(state)
        { 
            case IDLE:
            {                  
                if (world.isActionTime()){
                    if (Greenfoot.isKeyDown(this.quickAttack)){
                        this.state = PlayerStates.QUICKATTACKING;
                        //System.out.println("qa");
                        
                    }    
                    else if (Greenfoot.isKeyDown(this.strongAttack)){
                        this.state = PlayerStates.STRONGATTACKING;
                    }  
                    else if (Greenfoot.isKeyDown(this.riposte)){
                        this.state = PlayerStates.RIPOSTING;
                    }  
                }else{ 
                   if(world.checkOffBeatInput()){
                       this.state = PlayerStates.FLINCHING;
                   }
                }
        
                actIdle();   
                break;
            }  
            case QUICKATTACKING:
            {                  
                
                if(this.isTouching(TempoUnit.class)){
                    TempoUnit unit = (TempoUnit)this.getOneIntersectingObject(TempoUnit.class);
                    world.removeObject(unit);
                }
                actQuickAttack();   
                break;
            }            
            case STRONGATTACKING:
            {                  
               
                if(this.isTouching(TempoUnit.class)){
                    TempoUnit unit = (TempoUnit)this.getOneIntersectingObject(TempoUnit.class);
                    world.removeObject(unit);
                }
                actStrongAttack();   
                break;
            }            
            case RIPOSTING:
            {                  
              
                if(this.isTouching(TempoUnit.class)){
                    TempoUnit unit = (TempoUnit)this.getOneIntersectingObject(TempoUnit.class);
                    world.removeObject(unit);
                }
                actRiposte();   
                break;
            }            
            case FLINCHING:
            {
                // 15 frames no-input penalty for offbeat input
                this.penaltyFrames--;
                if(penaltyFrames == 0){
                    this.state = PlayerStates.IDLE;
                    this.resetPenaltyFrames();
                }
                actPenalized();
                break;
            }
        }
        if(frameCounter++ < bufferInputCoolDown){
            this.state = PlayerStates.IDLE;
            frameCounter = 0;
        }
    }
}
