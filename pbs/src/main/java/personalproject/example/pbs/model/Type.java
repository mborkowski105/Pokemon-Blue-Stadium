package personalproject.example.pbs.model;

import java.util.*;

// TODO split class up, extract damage charts into utility class (can be versioned by Gen 1-8 types)

public enum Type {
    NORMAL (0, "physical"),
    FIRE (1, "special"),
    WATER (2, "special"),
    ELECTRIC (3, "special"),
    GRASS (4, "special"),
    ICE (5, "special"),
    FIGHTING (6, "physical"),
    POISON (7, "physical"),
    GROUND (8, "physical"),
    FLYING (9, "physical"),
    PSYCHIC (10, "special"),
    BUG (11, "physical"),
    ROCK (12, "physical"),
    GHOST (13, "physical"),
    DRAGON (14, "special");

    private final int id;
    private final String damageCategory;

    Type (int id, String damageCategory) {
        this.id = id;
        this.damageCategory = damageCategory;
    }

    public String getDamageCategory() {
        return damageCategory;
    }

    private static double getNormalAttackModifier(int id){
        List<Double> typeChart = List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 0.0, 1.0);

        return typeChart.get(id);
    }
    private static double getFireAttackModifier(int id){
        List<Double> typeChart = List.of(1.0, 0.5, 0.5, 1.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5);

        return typeChart.get(id);
    }
    private static double getWaterAttackModifier(int id){
        List<Double> typeChart = List.of(1.0, 2.0, 0.5, 1.0, 0.5, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5);

        return typeChart.get(id);
    }
    private static double getElectricAttackModifier(int id){
        List<Double> typeChart = List.of(1.0, 1.0, 2.0, 0.5, 0.5, 1.0, 1.0, 1.0, 0.0, 2.0, 1.0, 1.0, 1.0, 1.0, 0.5);

        return typeChart.get(id);
    }
    private static double getGrassAttackModifier(int id){
        List<Double> typeChart = List.of(1.0, 0.5, 2.0, 1.0, 0.5, 1.0, 1.0, 0.5, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 0.5);

        return typeChart.get(id);
    }
    private static double getIceAttackModifier(int id){
        List<Double> typeChart = List.of(1.0, 1.0, 0.5, 1.0, 2.0, 0.5, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0);

        return typeChart.get(id);
    }
    private static double getFightingAttackModifier(int id){
        List<Double> typeChart = List.of(2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 0.5, 1.0, 0.5, 0.5, 0.5, 2.0, 0.0, 1.0);

        return typeChart.get(id);
    }
    private static double getPoisonAttackModifier(int id){
        List<Double> typeChart = List.of(1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 0.5, 1.0, 1.0, 2.0, 0.5, 0.5, 1.0);

        return typeChart.get(id);
    }
    private static double getGroundAttackModifier(int id){
        List<Double> typeChart = List.of(1.0, 2.0, 1.0, 2.0, 0.5, 1.0, 1.0, 2.0, 1.0, 0.0, 1.0, 0.5, 2.0, 1.0, 1.0);

        return typeChart.get(id);
    }
    private static double getFlyingAttackModifier(int id){
        List<Double> typeChart = List.of(1.0, 1.0, 1.0, 0.5, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0);

        return typeChart.get(id);
    }
    private static double getPsychicAttackModifier(int id){
        List<Double> typeChart = List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0);

        return typeChart.get(id);
    }
    private static double getBugAttackModifier(int id){
        List<Double> typeChart = List.of(1.0, 0.5, 1.0, 1.0, 2.0, 1.0, 0.5, 0.5, 1.0, 0.5, 2.0, 0.5, 1.0, 0.5, 1.0);

        return typeChart.get(id);
    }
    private static double getRockAttackModifier(int id){
        List<Double> typeChart = List.of(1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 0.5, 2.0, 1.0, 2.0, 0.5, 1.0, 1.0);

        return typeChart.get(id);
    }
    private static double getGhostAttackModifier(int id){
        List<Double> typeChart = List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 2.0, 1.0);

        return typeChart.get(id);
    }
    private static double getDragonAttackModifier(int id){
        List<Double> typeChart = List.of(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0);

        return typeChart.get(id);
    }

    public static double getDamageModifier(Type attackType, Type defenseType1, Type defenseType2) {
        double modifier = 1;

        switch (attackType) {
            case NORMAL: {
                if (defenseType2 == null) {
                    modifier = modifier * getNormalAttackModifier(defenseType1.id);
                }
                else {
                    modifier = modifier * getNormalAttackModifier(defenseType1.id) * getNormalAttackModifier(defenseType2.id);
                }
                break;
            }
            case FIRE: {
                if (defenseType2 == null) {
                    modifier = modifier * getFireAttackModifier(defenseType1.id);
                }
                else {
                    modifier = modifier * getFireAttackModifier(defenseType1.id) * getFireAttackModifier(defenseType2.id);
                }
                break;
            }
            case WATER: {
                if (defenseType2 == null) {
                    modifier = modifier * getWaterAttackModifier(defenseType1.id);
                }
                else {
                    modifier = modifier * getWaterAttackModifier(defenseType1.id) * getWaterAttackModifier(defenseType2.id);
                }
                break;
            }
            case ELECTRIC: {
                if (defenseType2 == null) {
                    modifier = modifier * getElectricAttackModifier(defenseType1.id);
                }
                else {
                    modifier = modifier * getElectricAttackModifier(defenseType1.id) * getElectricAttackModifier(defenseType2.id);
                }
                break;
            }
            case GRASS: {
                if (defenseType2 == null) {
                    modifier = modifier * getGrassAttackModifier(defenseType1.id);
                }
                else {
                    modifier = modifier * getGrassAttackModifier(defenseType1.id) * getGrassAttackModifier(defenseType2.id);
                }
                break;
            }
            case ICE: {
                if (defenseType2 == null) {
                    modifier = modifier * getIceAttackModifier(defenseType1.id);
                }
                else {
                    modifier = modifier * getIceAttackModifier(defenseType1.id) * getIceAttackModifier(defenseType2.id);
                }
                break;
            }
            case FIGHTING: {
                if (defenseType2 == null) {
                    modifier = modifier * getFightingAttackModifier(defenseType1.id);
                }
                else {
                    modifier = modifier * getFightingAttackModifier(defenseType1.id) * getFightingAttackModifier(defenseType2.id);
                }
                break;
            }
            case POISON: {
                if (defenseType2 == null) {
                    modifier = modifier * getPoisonAttackModifier(defenseType1.id);
                }
                else {
                    modifier = modifier * getPoisonAttackModifier(defenseType1.id) * getPoisonAttackModifier(defenseType2.id);
                }
                break;
            }
            case GROUND: {
                if (defenseType2 == null) {
                    modifier = modifier * getGroundAttackModifier(defenseType1.id);
                }
                else {
                    modifier = modifier * getGroundAttackModifier(defenseType1.id) * getGroundAttackModifier(defenseType2.id);
                }
                break;
            }
            case FLYING: {
                if (defenseType2 == null) {
                    modifier = modifier * getFlyingAttackModifier(defenseType1.id);
                }
                else {
                    modifier = modifier * getFlyingAttackModifier(defenseType1.id) * getFlyingAttackModifier(defenseType2.id);
                }
                break;
            }
            case PSYCHIC: {
                if (defenseType2 == null) {
                    modifier = modifier * getPsychicAttackModifier(defenseType1.id);
                }
                else {
                    modifier = modifier * getPsychicAttackModifier(defenseType1.id) * getPsychicAttackModifier(defenseType2.id);
                }
                break;
            }
            case BUG: {
                if (defenseType2 == null) {
                    modifier = modifier * getBugAttackModifier(defenseType1.id);
                }
                else {
                    modifier = modifier * getBugAttackModifier(defenseType1.id) * getBugAttackModifier(defenseType2.id);
                }
                break;
            }
            case ROCK: {
                if (defenseType2 == null) {
                    modifier = modifier * getRockAttackModifier(defenseType1.id);
                }
                else {
                    modifier = modifier * getRockAttackModifier(defenseType1.id) * getRockAttackModifier(defenseType2.id);
                }
                break;
            }
            case GHOST: {
                if (defenseType2 == null) {
                    modifier = modifier * getGhostAttackModifier(defenseType1.id);
                }
                else {
                    modifier = modifier * getGhostAttackModifier(defenseType1.id) * getGhostAttackModifier(defenseType2.id);
                }
                break;
            }
            case DRAGON: {
                if (defenseType2 == null) {
                    modifier = modifier * getDragonAttackModifier(defenseType1.id);
                }
                else {
                    modifier = modifier * getDragonAttackModifier(defenseType1.id) * getDragonAttackModifier(defenseType2.id);
                }
                break;
            }
        }

        return modifier;
    }
}