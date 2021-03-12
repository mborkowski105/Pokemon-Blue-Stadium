public abstract class MoveFactory {

    public static Move createMove(String name) {
        Move move;

        switch (name) {
            case "ACID ARMOR": {
                SecondaryEffect secondaryEffect = new SecondaryStatModifier(100, true, "DEF", 2);
                move = new Move(name, Type.POISON, 0, 100, 40, 0, secondaryEffect);
                break;
            }
            case "AMNESIA": {
                SecondaryEffect secondaryEffect = new SecondaryStatModifier(100, true, "SPC", 2);
                move = new Move(name, Type.PSYCHIC, 0, 100, 20, 0, secondaryEffect);
                break;
            }
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
            case "CONFUSE RAY": {
                SecondaryEffect secondaryEffect = new SecondaryStatus(100, false, Status.CONFUSED);
                move = new Move(name, Type.GHOST, 0, 100, 10, 0, secondaryEffect);
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
            case "FIRE BLAST": {
                SecondaryEffect secondaryEffect = new SecondaryStatus(10, false, Status.BURN);
                move = new Move(name, Type.FIRE, 120, 85, 5, 0, secondaryEffect);
                break;
            }
            case "HYDRO PUMP": {
                move = new Move(name, Type.WATER, 120, 80, 5, 0, null);
                break;
            }
            case "HYPNOSIS": {
                SecondaryEffect secondaryEffect = new SecondaryStatus(100, false, Status.SLEEP);
                move = new Move(name, Type.PSYCHIC, 0, 60, 20, 0, secondaryEffect);
                break;
            }
            case "ICE BEAM": {
                SecondaryEffect secondaryEffect = new SecondaryStatus(10, false, Status.FREEZE);
                move = new Move(name, Type.ICE, 95, 90, 10, 0, secondaryEffect);
                break;
            }
            case "LOVELY KISS": {
                SecondaryEffect secondaryEffect = new SecondaryStatus(100, false, Status.SLEEP);
                move = new Move(name, Type.NORMAL, 0, 75, 10, 0, secondaryEffect);
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
            case "RAZOR LEAF": {
                SecondaryEffect secondaryEffect = new SecondaryCriticalHit(100, false);
                move = new Move(name, Type.GRASS, 55, 95, 25, 0, secondaryEffect);
                break;
            }
            case "RECOVER": {
                SecondaryEffect secondaryEffect = new SecondaryRecovery(100, true);
                move = new Move(name, Type.NORMAL, 0, 100, 20, 0, secondaryEffect);
                break;
            }
            case "REST": {
                SecondaryEffect secondaryEffect = new SecondaryStatus(100, true, Status.REST);
                move = new Move(name, Type.PSYCHIC, 0, 100, 10, 0, secondaryEffect);
                break;
            }
            case "ROCK SLIDE": {
                // add flinch
                move = new Move(name, Type.ROCK, 75, 90, 10, 0, null);
                break;
            }
            case "SCREECH": {
                SecondaryEffect secondaryEffect = new SecondaryStatModifier(100, false, "DEF", -2);
                move = new Move(name, Type.NORMAL, 0, 85, 40, 0, secondaryEffect);
                break;
            }
            case "SEISMIC TOSS": {
                SecondaryEffect secondaryEffect = new SecondaryDamageByLevel(100, false);
                move = new Move(name, Type.NORMAL, 1, 100, 20, 0, secondaryEffect);
                break;
            }
            case "SLASH": {
                SecondaryEffect secondaryEffect = new SecondaryCriticalHit(100, false);
                move = new Move(name, Type.NORMAL, 70, 100, 20, 0, secondaryEffect);
                break;
            }
            case "SLEEP POWDER": {
                SecondaryEffect secondaryEffect = new SecondaryStatus(100, false, Status.SLEEP);
                move = new Move(name, Type.GRASS, 0, 75, 15, 0, secondaryEffect);
                break;
            }
            case "SLUDGE": {
                SecondaryEffect secondaryEffect = new SecondaryStatus(40, false, Status.POISON);
                move = new Move(name, Type.POISON, 65, 100, 20, 0, secondaryEffect);
                break;
            }
            case "SOFT-BOILED": {
                SecondaryEffect secondaryEffect = new SecondaryRecovery(100, true);
                move = new Move(name, Type.NORMAL, 0, 100, 10, 0, secondaryEffect);
                break;
            }
            case "SUBMISSION": {
                SecondaryEffect secondaryEffect = new SecondaryRecoil(100, true);
                move = new Move(name, Type.FIGHTING, 80, 80, 25, 0, secondaryEffect);
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
            case "TOXIC": {
                SecondaryEffect secondaryEffect = new SecondaryStatus(100, false, Status.BAD_POISON);
                move = new Move(name, Type.POISON, 0, 85, 10, 0, secondaryEffect);
                break;
            }
            default: {
                return null;
            }
        }

        return move;
    }
}