// consider making this class into an interface

public enum Status {
    POISON ("PSN"),
    BAD_POISON ("PSN"),
    PARALYSIS ("PAR"),
    BURN ("BRN"),
    FREEZE ("FRZ"),
    SLEEP ("SLP"),
    REST ("SLP"),
    FAINT ("FNT");

    private final String ABBREVIATED;

    Status(String abbreviated) {
        this.ABBREVIATED = abbreviated;
    }

    public String getAbbreviated(){
        if (this.ABBREVIATED == null){
            return "";
        }
        return this.ABBREVIATED;
    }
}
