import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class adventurer extends AnimatedActor
{
    private PlayerStates state = PlayerStates.IDLE;
    private int penaltyFrames = 30;
    private int frameCounter = 0;
    private String quickAttack;
    private String strongAttack;
    private String riposte;
    
    public adventurer(String quickAttack, String strongAttack, String riposte, int animationFrames){
        super(animationFrames);
        
        this.quickAttack = quickAttack;
        this.strongAttack = strongAttack;
        this.riposte = riposte;
        

    }
    public int getState(){
        switch(state)
        { 
            case IDLE:
            {                  
                return 0;   
            }  
            case FLINCHING:
            {                  
                return 1;
            }            
            case STRONGATTACKING:
            {                  
                return 2;
            }            
            case QUICKATTACKING:
            {                  
                return 3;
            }            

        }
        return 4; //RIPOSTING
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
        super.act();
        switch(state)
        { 
            case IDLE:
            {                  
                actIdle();   
                break;
            }  
            case QUICKATTACKING:
            {                  
                actQuickAttack();   
                break;
            }            
            case STRONGATTACKING:
            {                  
                actStrongAttack();   
                break;
            }            
            case RIPOSTING:
            {                  
                actRiposte();   
                break;
            }            
            case FLINCHING:
            {
                actPenalized();
                break;
            }
        }
    }
    public void actIdle(){
        MyWorld world = (MyWorld)this.getWorld();
     
        
        if (world.isActionTime()){
            if (Greenfoot.isKeyDown(this.quickAttack)){
                this.state = PlayerStates.QUICKATTACKING;
                
            }    
            if (Greenfoot.isKeyDown(this.strongAttack)){
                this.state = PlayerStates.STRONGATTACKING;
            }  
            if (Greenfoot.isKeyDown(this.riposte)){
                this.state = PlayerStates.RIPOSTING;
            }  
        }else{ 
           if(world.checkOffBeatInput()){
               this.state = PlayerStates.FLINCHING;
           }
        }
        
      
    }
    public void actPenalized(){
        // 15 frames no-input penalty for offbeat input
        this.penaltyFrames--;
        if(penaltyFrames == 0){
            this.state = PlayerStates.IDLE;
            this.resetPenaltyFrames();
        }
    }
    public void actQuickAttack(){
        MyWorld world = (MyWorld)this.getWorld();
        if(this.isTouching(TempoUnit.class)){
            TempoUnit unit = (TempoUnit)this.getOneIntersectingObject(TempoUnit.class);
            world.removeObject(unit);
        }
        
        
    }
    public void actStrongAttack(){
      
        
        
    }
    public void actRiposte(){
        
        
        
    }
}
