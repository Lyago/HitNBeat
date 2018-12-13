/**
 * Write a description of class PlayerStates here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum PlayerStates
{
    IDLE("idle"),QUICKATTACKING("attack2"),STRONGATTACKING("attack3"),RIPOSTING("attack1"),FLINCHING("hurt");   

    String symbol;
    
    private PlayerStates(String symbol)
    {
        this.symbol = symbol;
    }
    
    @Override
    public String toString()
    {
        return symbol;
    }
};
