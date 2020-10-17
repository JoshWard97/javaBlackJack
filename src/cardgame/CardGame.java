/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import cardgame.card.Suit;
import static cardgame.card.Suit.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Josh
 */
public class CardGame {

    /**
     * @param args the command line arguments
     */
    public static String cardSymbol;
    public static String cardSign;
    public static Suit suitH;
    
    public static void main(String[] args) {
        // Create Deck
        

     card[] deck = makeDeck();
     deck = makeDeck();
     String[] playerCards = new String[7];
     String[] dealerCards = new String[7];
     int i;
     for(i=0;i<7;i++)
    {
         playerCards[i]= new String();
         dealerCards[i] = new String();
    }
     
    deck = shuffler(deck);
    drawCard(deck[0],playerCards);
    drawCard(deck[1],playerCards);
    drawCard(deck[2],playerCards);
    deck[3].hidden=true;
    drawCard(deck[3],playerCards);
    drawCard(deck[26],playerCards);
     
    printCards(playerCards);
     
    }
    public static card[] shuffler(card[] deck)
    {
        Random rand = new Random();
		
		for (int i = 0; i < deck.length; i++) {
                    int randomIndexToSwap = rand.nextInt(deck.length - 1);
                    card temp = new card();
                    temp = deck[randomIndexToSwap];
                    deck[randomIndexToSwap] = deck[i];
                    deck[i] = new card();
                    deck[i] = temp;
		}
        return deck;
    }
    public static void printCards(String[] lines)
    {
        int i;
        for(i=0;i<7;i++)
        {
            System.out.println(lines[i]);
        }
    }
    public static void drawCard(card cardIn, String[] outLines)
    {
        //Adds a single card, given as card, and adds the card to the outLines for displaying multiple 
        
        //hardcoded Backside card to prevent user from seing the card.
        if(cardIn.hidden)
        {
            outLines[0] += ("------   ");
            outLines[1] += ("| XX |   ");
            outLines[2] += ("| ?? |   ");
            outLines[3] += ("| XX |   ");
            outLines[4] += ("| ?? |   ");
            outLines[5] += ("| XX |   ");
            outLines[6] += ("------   ");
            return;
        }
        
        
        outLines[0] += ("------   ");
        cardSign = cardIn.CardChar + " ";
        if(cardIn.CardChar.equals("10"))
        {
            cardSign = cardIn.CardChar;
        }
        //card symbol code
        cardSymbol = "";
        if(cardIn.suit == DIAMOND) cardSymbol = "\u2666";
        if(cardIn.suit == HEART) cardSymbol = "\u2665";
        if(cardIn.suit == CLUB) cardSymbol = "\u2663";
        if(cardIn.suit == SPADE) cardSymbol = "\u2660";
           
        outLines[1] += ("|" + cardSign + " " + cardSymbol + "|   ");
        outLines[2] += ("| " + "\u2000\u2001" + " |   ");
        outLines[3] += ("| " + "\u2000\u2001" + " |   ");
        outLines[4] += ("| " + "\u2000\u2001" + " |   ");
        outLines[5] += ("|" + cardSymbol + " " + cardSign + "|   ");
        outLines[6] += ("------   ");
    }
    public static card[] makeDeck()
    {
        card[] deck;
        deck = new card[52];
        int i;
        for(i=0;i<52;i++)
        {
            deck[i] = new card();
        }
        for(i=0;i<4;i++)
        {
            suitH = null;
            if(i==0) suitH = DIAMOND;    
            if(i==1) suitH = HEART;
            if(i==2) suitH = CLUB;
            if(i==3) suitH = SPADE;
            
            deck[0+(13*i)].value = 2;
            deck[0+(13*i)].CardChar = "2";
            deck[0+(13*i)].suit = suitH;
            
            deck[1+(13*i)].value = 3;
            deck[1+(13*i)].CardChar = "3";
            deck[1+(13*i)].suit = suitH;
            
            deck[2+(13*i)].value = 4;
            deck[2+(13*i)].CardChar = "4";
            deck[2+(13*i)].suit = suitH;
            
            deck[3+(13*i)].value = 5;
            deck[3+(13*i)].CardChar = "5";
            deck[3+(13*i)].suit = suitH;
            
            deck[4+(13*i)].value = 6;
            deck[4+(13*i)].CardChar = "6";
            deck[4+(13*i)].suit = suitH;
            
            deck[5+(13*i)].value = 7;
            deck[5+(13*i)].CardChar = "7";
            deck[5+(13*i)].suit = suitH;
            
            deck[6+(13*i)].value = 8;
            deck[6+(13*i)].CardChar = "8";
            deck[6+(13*i)].suit = suitH;
            
            deck[7+(13*i)].value = 9;
            deck[7+(13*i)].CardChar = "9";
            deck[7+(13*i)].suit = suitH;
            
            deck[8+(13*i)].value = 10;
            deck[8+(13*i)].CardChar = "10";
            deck[8+(13*i)].suit = suitH;
            
            deck[9+(13*i)].value = 10;
            deck[9+(13*i)].CardChar = "J";
            deck[9+(13*i)].suit = suitH;
            
            deck[10+(13*i)].value = 10;
            deck[10+(13*i)].CardChar = "Q";
            deck[10+(13*i)].suit = suitH;
            
            deck[11+(13*i)].value = 10;
            deck[11+(13*i)].CardChar = "K";
            deck[11+(13*i)].suit = suitH;
            
            deck[12+(13*i)].value = 11;
            deck[12+(13*i)].secondValue = 1;
            deck[12+(13*i)].CardChar = "A";
            deck[12+(13*i)].suit = suitH;
            
            
        }
        
        return deck;
    }
    
}
