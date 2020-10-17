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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public static int playerScore;
    public static int dealerScore;
    public static Scanner sc = new Scanner(System.in);
    public static float coins = 100;
    
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
    blackJack(deck,playerCards,dealerCards);
     
    //printCards(playerCards);
     
    }
    public static void blackJack(card[] deck,String [] playerCards,String [] dealerCards)
    {
        while(true)
        {
            //Initial Drawing of Cards, player dealer, player, hidden dealer card. Starting at 1, not 0, to simulate discard.
            int hitCountPlayer = 0;
            int hitCountDealer = 0;
            int playerAces = 0;
            int dealerAces = 0;
            //aces player has. used to subtract to score for an ace being worth 1. Will display minimum max scores. (avoid confusion of showing 3 scores if 2 aces.)
            drawCard(deck[1],playerCards);
            drawCard(deck[2],dealerCards);
            drawCard(deck[3],playerCards);
            deck[4].hidden=true;
            drawCard(deck[4],dealerCards);
            blackJackPrintDecks(dealerCards,playerCards); //inital printing of deck after player and dealer have 2 cards
            dealerScore = deck[2].value + deck[4].value;
            playerScore = deck[1].value + deck[3].value;
            if(playerScore==21) blackJackBlackJack();
            if(deck[1].value==11)
            {
                playerAces++;
            }
            if(deck[3].value==11)
            {
                playerAces++;
            }
            System.out.print("\nPlayer Score: " + playerScore);
            int temp;
            int tempAces = playerAces;
            while(tempAces>0) //print another score for each ace.
            {
                temp = playerScore -= (10); //subtract 10 for each, show lowest possible score
                System.out.print(" / " + temp);
                tempAces--;            
            }
            
            //at this point, each player has two cards.
             //scanner for playing game
            char cIn = sc.next().charAt(0);
            System.out.println("");
            while('h'== cIn) //while player hits b, then enter (to bet)
            {           
                //player hit b, draw a card, print decks, calulate score
                
                
                
                hitCountPlayer++;
                drawCard(deck[4 + (hitCountPlayer)],playerCards);
                blackJackPrintDecks(dealerCards,playerCards);
                if(deck[4 + (hitCountPlayer)].value==11)//if next draw is an ace
                {
                    playerAces++;
                }
                //update playerScore for new Card value
                playerScore += (deck[(4 + hitCountPlayer)].value); //for first drawn card, draw 5 and add it, then 6, etc
                System.out.print("\nPlayer Score: " + playerScore);
            
                tempAces = playerAces;
                while(tempAces>0) //print another score for each ace
                {
                    temp = playerScore - 10; //For each ace player has, show a score 10 lower
                    System.out.print(" / " + temp);
                    tempAces--;            
                }
                
                if((playerScore-(10*playerAces))>21) //check if lowest possible score is over 21
                {
                    
                    blackJackPlayerBust();
                    break;
                }
                System.out.println(""); 
                cIn = sc.next().charAt(0);
            }
            if('s'== cIn) // to stay
            {
                while(playerScore>22)//still valid score, just figuring out max score player has, if its over 21, we have aces and NEED them to be a 1 so they don't bust
                {
                 playerScore-=10;   
                }
                System.out.println("Best Score is: " + playerScore);
                
                blackJackPrintDecks(dealerCards,playerCards); // just to avoid shifting, reprint
                gamepause(2000);
                
                //need to reset dealer string because we need to un hide their card.
                
                dealerCards = new String[7];
                int i;
                for(i=0;i<7;i++)
                    dealerCards[i] = new String();
                
                drawCard(deck[2],dealerCards);
                deck[4].hidden=false;
                drawCard(deck[4],dealerCards);
                blackJackPrintDecks(dealerCards,playerCards); //Re print deck with dealer card showing.
                gamepause(2000);
                //score check, begin hitting if needed. 
                dealerScore = deck[2].value + deck[4].value;
                if(deck[2].value==11)
                {
                    dealerAces++;
                }
                if(deck[4].value==11)
                {
                    dealerAces++;
                }
                while(dealerScore<17) //rules for a dealer to hit
                {
                    hitCountDealer++;
                    drawCard(deck[(4 + hitCountPlayer + hitCountDealer)],dealerCards);
                    blackJackPrintDecks(dealerCards,playerCards);
                    dealerScore += deck[(4 + hitCountPlayer + hitCountDealer)].value;//4 + hitCountPlayer is the last card drawn by the player. Go to next card.
                    gamepause(2000);
                }
                System.out.println("Dealer Score is " + dealerScore);
                
                if((dealerScore-(10*dealerAces))>21)
                {
                    dBust();
                }
                else if(dealerScore == playerScore)
                {
                    push();
                }
                else if(dealerScore>playerScore)
                {
                    dWin();
                }
                else if(dealerScore<playerScore)
                {
                    pWin();
                }
                
            }
            
            if(!yOrN("Keep playing? y for yes, any other key to exit"))
            {
                System.out.println("\n\n********Thanks for Playing :) ********");
                break;
            }
            //keep playing, clear all vars.
            int i;
            for(i=0;i<7;i++)
            {
                playerCards[i]="";
                dealerCards[i]="";
                
            }
            //reshuffle deck
            deck = shuffler(deck);
            
            
        }
    }
    public static void dBust()
    {
        System.out.println("Dealer busted with " + dealerScore);
        pWin();
    }
    public static void push()
    {
        System.out.println("You pushed with the dealer!");
    }
    public static void dWin()
    {
        System.out.println("The dealer has won this hand.");
    }
    public static void pWin()
    {
        System.out.println("You have won this hand!");
    }
    public static Boolean yOrN(String msg)
    {
        System.out.println(msg);
        char cIn = sc.next().charAt(0);
        return cIn=='y';
    }
    public static void gamepause(int time)
    {
        try 
        {
            Thread.sleep(time); //wait for two seconds
            //dealer turn to draw.
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(CardGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void blackJackPlayerBust()
    {
        System.out.println("\nYou Busted with " + playerScore);
        dWin();
    }
    
    public static void blackJackBlackJack()
    {
        System.out.println("Blackjack! Payout is 3:2");
    }
    
    public static void blackJackPrintDecks(String[] dealer, String[] player)
    {
        System.out.println("******Dealer's Cards******");
        printCards(dealer);
        System.out.println("\n\n********Your Cards********");
        printCards(player);
        
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
