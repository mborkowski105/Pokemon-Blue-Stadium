public abstract class MoveFactory {

    public static Move createMove(String name) {
        Move move;

        switch (name) {
            case "THUNDERBOLT": {
                move = new Move(name, Type.ELECTRIC, 95, 100, 15, 0);
                break;
            }
            default: {
                return null;
            }
        }

        return move;
    }
}