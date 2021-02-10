import java.util.List;
import java.util.ArrayList;

public class Pokemon {
    // instance variables
    private String SPECIES;
    private final Type TYPE1;
    private final Type TYPE2;

    private final int HP;
    private int currentHp;
    private final int ATK;
    private int currentAtk;
    private final int DEF;
    private int currentDef;
    private final int SPC;
    private int currentSpc;
    private final int SPD;
    private int currentSpd;

    private int baseSpd;

    private int atkStageMultiplier;
    private int defStageMultiplier;
    private int spcStageMultiplier;
    private int spdStageMultiplier;

    private Status status1;
    private int status1Counter;

    private List<Move> moves;

//    /**
//     * Constructor for objects of class Pokemon
//     */
    public Pokemon(String species, Type type1, Type type2, int hp, int atk, int def, int spc, int spd, int baseSpd, Move move1, Move move2, Move move3, Move move4) {
        this.SPECIES = species;
        this.TYPE1 = type1;
        this.TYPE2 = type2;

        this.HP = hp;
        this.currentHp = hp;
        this.ATK = atk;
        this.currentAtk = atk;
        this.DEF = def;
        this.currentDef = def;
        this.SPC = spc;
        this.currentSpc = spc;
        this.SPD = spd;
        this.currentSpd = spd;

        this.baseSpd = baseSpd;

        this.atkStageMultiplier = 0;
        this.defStageMultiplier = 0;
        this.spcStageMultiplier = 0;
        this.spdStageMultiplier = 0;

        this.moves = new ArrayList<Move>();
        moves.add(move1);
        moves.add(move2);
        moves.add(move3);
        moves.add(move4);
    }

    public String getSpecies() {
        return this.SPECIES;
    }
    public Type getType1() {
        return this.TYPE1;
    }
    public Type getType2() {
        return this.TYPE2;
    }
    public int getHp() {
        return this.HP;
    }
    public int getCurrentHp() {
        return this.currentHp;
    }
    public int getAtk() {
        return this.ATK;
    }
    public int getCurrentAtk() {
        return this.currentAtk;
    }
    public int getDef() {
        return this.DEF;
    }
    public int getCurrentDef() {
        return this.currentDef;
    }
    public int getSpc() {
        return this.SPC;
    }
    public int getCurrentSpc() {
        return this.currentSpc;
    }
    public int getSpd() {
        return this.SPD;
    }
    public int getCurrentSpd() {
        return this.currentSpd;
    }
    public int getBaseSpd() { return this.baseSpd; }

    // implement Pokemon helper class?
    protected int getAtkStageMultiplier() {
        return atkStageMultiplier;
    }
    protected void incrementAtkStageMultiplier(int num) {
        atkStageMultiplier += num;
        currentAtk = getStatAlteration(ATK, atkStageMultiplier);
    }
    protected void decrementAtkStageMultiplier(int num) {
        atkStageMultiplier -= num;
        currentAtk = getStatAlteration(ATK, atkStageMultiplier);
    }

    protected int getDefStageMultiplier() {
        return defStageMultiplier;
    }
    protected void incrementDefStageMultiplier(int num) {
        defStageMultiplier += num;
        currentDef = getStatAlteration(DEF, defStageMultiplier);
    }
    protected void decrementDefStageMultiplier(int num) {
        defStageMultiplier -= num;
        currentDef = getStatAlteration(DEF, defStageMultiplier);
    }
    protected int getSpcStageMultiplier() {
        return spcStageMultiplier;
    }
    protected void incrementSpcStageMultiplier(int num) {
        spcStageMultiplier += num;
        currentSpc = getStatAlteration(SPC, spcStageMultiplier);
    }
    protected void decrementSpcStageMultiplier(int num) {
        spcStageMultiplier -= num;
        currentSpc = getStatAlteration(SPC, spcStageMultiplier);
    }
    protected int getSpdStageMultiplier() {
        return spdStageMultiplier;
    }
    protected void incrementSpdStageMultiplier(int num) {
        spdStageMultiplier += num;
        currentSpd = getStatAlteration(SPD, spdStageMultiplier);
    }
    protected void decrementSpdStageMultiplier(int num) {
        spdStageMultiplier -= num;
        currentSpd = getStatAlteration(SPD, spdStageMultiplier);
    }
    private int getStatAlteration(int maxStat, int stageMultiplier) {
        if (stageMultiplier == -6) {
            return (int)(maxStat * 0.25);
        }
        else if (stageMultiplier == -5) {
            return (int)(maxStat * 0.28);
        }
        else if (stageMultiplier == -4) {
            return (int)(maxStat * 0.33);
        }
        else if (stageMultiplier == -3) {
            return (int)(maxStat * 0.4);
        }
        else if (stageMultiplier == -2) {
            return (int)(maxStat * 0.5);
        }
        else if (stageMultiplier == -1) {
            return (int)(maxStat * 0.66);
        }
        else if (stageMultiplier == 0) {
            return maxStat;
        }
        else if (stageMultiplier == 1) {
            return (int)(maxStat * 1.5);
        }
        else if (stageMultiplier == 2) {
            return maxStat * 2;
        }
        else if (stageMultiplier == 3) {
            return (int)(maxStat * 2.5);
        }
        else if (stageMultiplier == 4) {
            return maxStat * 3;
        }
        else if (stageMultiplier == 5) {
            return (int)(maxStat * 3.5);
        }
        else if (stageMultiplier == 6) {
            return maxStat * 4;
        }
        else {
            return maxStat;
        }
    }

    protected int heal(int value) {
        currentHp = currentHp + value;
        return currentHp;
    }
    protected int damage(int value) {
        if (currentHp - value < 0) {
            currentHp = 0;
        }
        else {
            currentHp = currentHp - value;
        }
        return currentHp;
    }
    protected int resetHp() {
        currentHp = HP;
        return currentHp;
    }
    protected void resetStats(){
        currentAtk = ATK;
        currentDef = DEF;
        currentSpc = SPC;
        currentSpd = SPD;
    }

    public Status getStatus1() {
        return status1;
    }
    protected void setStatus1(Status status) {
        status1 = status;
    }
    protected int getStatus1Counter() {
        return status1Counter;
    }
    protected void incrementStatus1Counter() {
        status1Counter ++;
    }
    protected void resetStatus1Counter() {
        status1Counter = 0;
    }

    protected Move getMove1() {
        return moves.get(0);
    }
    protected Move getMove2() {
        return moves.get(1);
    }
    protected Move getMove3() {
        return moves.get(2);
    }
    protected Move getMove4() {
        return moves.get(3);
    }
}
