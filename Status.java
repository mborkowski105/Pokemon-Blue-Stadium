public enum Status {
    POISON ("PSN"),
    BAD_POISON ("PSN"),
    PARALYSIS ("PAR"),
    BURN ("BRN"),
    FREEZE ("FRZ"),
    SLEEP ("SLP");

    private final String abbreviated;

    Status(String abbreviated) {
        this.abbreviated = abbreviated;
    }
}
