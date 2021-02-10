public abstract class MoveFactory {

    public static Move createMove(String name) {
        Move move;

        switch (name) {
            case "BLIZZARD": {
                move = new Move(name, Type.ICE, 120, 90, 5, 0);
                break;
            }
            case "BODY SLAM": {
                move = new Move(name, Type.NORMAL, 85, 100, 15, 0);
                break;
            }
            case "EARTHQUAKE": {
                move = new Move(name, Type.GROUND, 100, 100, 10, 0);
                break;
            }
            case "EXPLOSION": {
                move = new Move(name, Type.NORMAL, 170, 100, 5, 0);
                break;
            }
            case "FIRE BLAST": {
                move = new Move(name, Type.FIRE, 120, 85, 5, 0);
                break;
            }
            case "ROCK SLIDE": {
                move = new Move(name, Type.ROCK, 75, 90, 10, 0);
                break;
            }
            case "THUNDERBOLT": {
                move = new Move(name, Type.ELECTRIC, 95, 100, 15, 0);
                break;
            }
            case "THUNDER": {
                move = new Move(name, Type.ELECTRIC, 120, 70, 10, 0);
                break;
            }
            default: {
                return null;
            }
        }

        return move;
    }
}