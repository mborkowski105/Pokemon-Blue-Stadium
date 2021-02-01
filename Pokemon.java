
/**
 * Write a description of class Pokemon here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pokemon {
    // instance variables
    private final String SPECIES;
    private final int HP;
    private final int ATK;
    private final int DEF;
    private final int SPC;
    private final int SPD;

    /**
     * Constructor for objects of class Pokemon
     */
    public Pokemon(String species, int hp, int atk, int def, int spc, int spd) {
        // initialise instance variables
        this.SPECIES = species;
        this.HP = hp;
        this.ATK = atk;
        this.DEF = def;
        this.SPC = spc;
        this.SPD = spd;
    }

    public String getSpecies() {
        return this.SPECIES;
    }
    
    public int getHp() {
        return this.HP;
    }
    
    public int getAtk() {
        return this.ATK;
    }
    
    public int getDef() {
        return this.DEF;
    }
    public int getSpc() {
        return this.SPC;
    }
    public int getSpd() {
        return this.SPD;
    }
}
