package com.app.homebrewed;

public class Character {
    private String name;
    private String species;
    private int level;
    private String characterClass;
    private int health;
    private int body;
    private int mind;
    private int flex;
    private int business;
    private int charm;
    private int deceit;
    private int magic;
    private int religion;
    private int academics;

    public int getBDYlevel() {
        return body / 10;
    }
    public int getMNDlevel() {
        return mind / 10;
    }
    public int getFLXlevel() {
        return flex / 10;
    }
    public int getBSNlevel() {
        return business / 10;
    }
    public int getCHMlevel() {
        return charm / 10;
    }
    public int getDCTlevel() {
        return deceit / 10;
    }
    public int getMGKlevel() {
        return magic / 10;
    }
    public int getRGNlevel() {
        return religion / 10;
    }
    public int getACAlevel() {
        return academics / 10;
    }
    public int getModLevel(Integer i) {
        return i/10;
    }
    public String getName() { return name; }
    public String getCharacterClass() { return characterClass; }
    public String getSpecies() { return species; }
    public int getLevel() { return level; }
    public int getHealth() { return health; }
    public int getBody() { return body; }
    public int getMind() { return mind; }
    public int getFlex() { return flex; }
    public int getBusiness() { return business; }
    public int getCharm() { return charm; }
    public int getDeceit() { return deceit; }
    public int getMagic() { return magic; }
    public int getReligion() { return religion; }
    public int getAcademics() { return academics; }

    public void setName(String s) {
        this.name = s;
    }
    public void setSpecies(String s) {
        this.species = s;
    }
    public void setLevel(Integer i) {
        this.level = i;
    }
    public void setCharacterClass(String s) {
        this.characterClass = s;
    }
    /*
    Health, BDY, MND, FLX, BSN, CHM, DCT, MGK, RGN, ACA
     */
    public void setHealth(Integer i){
        this.health = i;
    }
    public void setModBody(Integer i){
        this.body = i;
    }
    public void setModMind(Integer i){
        this.mind = i;
    }
    public void setModFlex(Integer i){
        this.flex = i;
    }
    public void setModBusiness(Integer i) {
        this.business = i;
    }
    public void setModCharm(Integer i) {
        this.charm = i;
    }
    public void setModDeceit(Integer i) {
        this.deceit = i;
    }
    public void setModMagic(Integer i) {
        this.magic = i;
    }
    public void setModReligion(Integer i) {
        this.religion = i;
    }
    public void setModAcademics(Integer i) {
        this.academics = i;
    }

    public Character(String name, String species, int level, String characterClass, int health, int body, int mind, int flex, int business, int charm, int deceit, int magic, int religion, int academics) {
        this.name = name;
        this.species = species;
        this.level = level;
        this.characterClass = characterClass;
        this.health = health;
        this.body = body;
        this.mind = mind;
        this.flex = flex;
        this.business = business;
        this.charm = charm;
        this.deceit = deceit;
        this.magic = magic;
        this.religion = religion;
        this.academics = academics;
    }

    public Character() {

    }
}
