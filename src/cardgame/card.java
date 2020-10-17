/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

/**
 *
 * @author Josh
 */
public class card {
    
    public enum Suit{HEART,DIAMOND,CLUB,SPADE};
    
    public int value;
    public int secondValue = 0;
    public String CardChar;
    public Suit suit;
    public Boolean hidden = false;
    
}
