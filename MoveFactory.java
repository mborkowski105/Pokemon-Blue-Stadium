public abstract class MoveFactory {

    public static Move createMove(String name) {
        Move move;

        switch (name) {
            case "BLIZZARD": {
                SecondaryEffect secondaryEffect = new SecondaryStatus(10, false, Status.FREEZE);
                move = new Move(name, Type.ICE, 120, 90, 5, 0, secondaryEffect);
                break;
            }
            case "BODY SLAM": {
                SecondaryEffect secondaryEffect = new SecondaryStatus(30, false, Status.PARALYSIS);
                move = new Move(name, Type.NORMAL, 85, 100, 15, 0, secondaryEffect);
                break;
            }
            case "EARTHQUAKE": {
                move = new Move(name, Type.GROUND, 100, 100, 10, 0, null);
                break;
            }
            case "EXPLOSION": {
                //add self-destruct
                move = new Move(name, Type.NORMAL, 170, 100, 5, 0, null);
                break;
            }
            case "FIRE BLAST": {
                SecondaryEffect secondaryEffect = new SecondaryStatus(10, false, Status.BURN);
                move = new Move(name, Type.FIRE, 120, 85, 5, 0, secondaryEffect);
                break;
            }
            case "ROCK SLIDE": {
                // add flinch
                move = new Move(name, Type.ROCK, 75, 90, 10, 0, null);
                break;
            }
            case "SWORDS DANCE": {
                SecondaryEffect secondaryEffect = new SecondaryStatModifier(100, false, "ATK", 2);
                move = new Move(name, Type.NORMAL, 0, 100, 30, 0, secondaryEffect);
                break;
            }
            case "THUNDERBOLT": {
                SecondaryEffect secondaryEffect = new SecondaryStatus(10, false, Status.PARALYSIS);
                move = new Move(name, Type.ELECTRIC, 95, 100, 15, 0, secondaryEffect);
                break;
            }
            case "THUNDER": {
                SecondaryEffect secondaryEffect = new SecondaryStatus(10, false, Status.PARALYSIS);
                move = new Move(name, Type.ELECTRIC, 120, 70, 10, 0, secondaryEffect);
                break;
            }
            default: {
                return null;
            }
        }

        return move;
    }
}