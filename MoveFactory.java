public abstract class MoveFactory {

    public static Move createMove(String name) {
        Move move;

        switch (name) {
            case "AGILITY": {
                SecondaryEffect secondaryEffect = new SecondaryStatModifier(100, true, "SPD", 2);
                move = new Move(name, Type.PSYCHIC, 0, 100, 30, 0, secondaryEffect);
                break;
            }
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
            case "DRILL PECK": {
                move = new Move(name, Type.FLYING, 80, 100, 20, 0, null);
                break;
            }
            case "EARTHQUAKE": {
                move = new Move(name, Type.GROUND, 100, 100, 10, 0, null);
                break;
            }
            case "EXPLOSION": {
                SecondaryEffect secondaryEffect = new SecondaryOHKO(100, true);
                move = new Move(name, Type.NORMAL, 170, 100, 5, 0, secondaryEffect);
                break;
            }
            case "HYPNOSIS": {
                SecondaryEffect secondaryEffect = new SecondaryStatus(100, false, Status.SLEEP);
                move = new Move(name, Type.PSYCHIC, 0, 60, 20, 0, secondaryEffect);
                break;
            }
            case "FIRE BLAST": {
                SecondaryEffect secondaryEffect = new SecondaryStatus(10, false, Status.BURN);
                move = new Move(name, Type.FIRE, 120, 85, 5, 0, secondaryEffect);
                break;
            }
            case "NIGHT SHADE": {
                SecondaryEffect secondaryEffect = new SecondaryDamageByLevel(100, false);
                move = new Move(name, Type.GHOST, 1, 100, 15, 0, secondaryEffect);
                break;
            }
            case "PSYCHIC": {
                SecondaryEffect secondaryEffect = new SecondaryStatModifier(33, false, "SPC", -1);
                move = new Move(name, Type.PSYCHIC, 90, 100, 10, 0, secondaryEffect);
                break;
            }
            case "RECOVER": {
                SecondaryEffect secondaryEffect = new SecondaryRecovery(100, true);
                move = new Move(name, Type.NORMAL, 0, 100, 20, 0, secondaryEffect);
                break;
            }
            case "ROCK SLIDE": {
                // add flinch
                move = new Move(name, Type.ROCK, 75, 90, 10, 0, null);
                break;
            }
            case "SEISMIC TOSS": {
                SecondaryEffect secondaryEffect = new SecondaryDamageByLevel(100, false);
                move = new Move(name, Type.NORMAL, 1, 100, 20, 0, secondaryEffect);
                break;
            }
            case "SOFT-BOILED": {
                SecondaryEffect secondaryEffect = new SecondaryRecovery(100, true);
                move = new Move(name, Type.NORMAL, 0, 100, 10, 0, secondaryEffect);
                break;
            }
            case "SURF": {
                move = new Move(name, Type.WATER, 95, 100, 15, 0, null);
                break;
            }
            case "SWORDS DANCE": {
                SecondaryEffect secondaryEffect = new SecondaryStatModifier(100, true, "ATK", 2);
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
            case "THUNDER WAVE": {
                SecondaryEffect secondaryEffect = new SecondaryStatus(100, false, Status.PARALYSIS);
                move = new Move(name, Type.ELECTRIC, 0, 100, 20, 0, secondaryEffect);
                break;
            }
            default: {
                return null;
            }
        }

        return move;
    }
}