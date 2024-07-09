/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : Q
 * Group    : 5
 * Members  :
 * 1. 5026231060 - Hafizhan Yusra Sulistyo
 * 2. 5026221049 - Justin Ariyuna
 * ------------------------------------------------------
 */
public class Player{
    //states
    private String name;
    private int position;
    private int number;
    private static int playerCounter = 1;
    //constructor method
    public Player (String name){
        this.name = name;
        this.number = playerCounter++;
        this.position = 1; // All players start at position 1
    }
    //setter methods

    public void setName (String name){
        this.name=name;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public void setNumber (int number){
        this.number=number;
    }

    //getter methods
    public String getName() {
        return this.name;
    }


    public int getPosition() {
        return this.position;
    }

    public int getNumber() {
        return this.number;
    }
    //another method
    public int rollDice()
    {
        return (int)((Math.random()*6)+1);
    }

    public void moveAround(int x, int boardSize)
    {
        if (this.position + x > boardSize){
            this.position = (boardSize - this.position) + (boardSize - x);
        }
        else {
            this.position = this.position + x;

        }
    }
}