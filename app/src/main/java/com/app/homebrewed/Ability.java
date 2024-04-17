package com.app.homebrewed;

public class Ability {
    private int abilityIndex;
    private int abilityLevel;
    private String abilityClass;
    private String abilityName;
    private String abilityDescription;
    private String abilityRange;
    private String abilityDamage;
    private String abilityEffect;

    // Constructor
    public Ability(String abilityName) {
        this.abilityName = abilityName;
    }

    public int getAbilityIndex() { return abilityIndex; }
    public int getAbilityLevel() { return abilityLevel; }
    public String getAbilityClass() { return abilityClass; }
    public String getAbilityName() { return abilityName; }
    public String getAbilityDescription() { return abilityDescription; }
    public String getAbilityRange() { return abilityRange; }
    public String getAbilityDamage() { return abilityDamage; }
    public String getAbilityEffect() { return abilityEffect; }
    public void setAbilityIndex(Integer i) { this.abilityIndex = i; }
    public void setAbilityLevel(Integer i) { this.abilityLevel = i; }
    public void setAbilityClass(String s) { this.abilityClass = s; }
    public void setAbilityName(String s) { this.abilityName = s; }
    public void setAbilityDescription(String s) { this.abilityDescription = s; }
    public void setAbilityRange(String s) { this.abilityDescription = s; }
    public void setAbilityDamage(String s) { this.abilityDamage = s; }
    public void setAbilityEffect(String s) { this.abilityEffect = s; }
}
