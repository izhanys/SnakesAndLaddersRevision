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
public class Snake{
    int head;
    int tail;

    Snake(int head, int tail){
        this.head = head;
        this.tail = tail;
    }

    void setTail(int tail){
        this.tail = tail;
    }
    void setHead(int head){
        this.head = head;
    }
    int getTail(){
        return this.tail;
    }
    int getHead(){
        return this.head;
    }
}