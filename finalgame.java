/**
 * Write a description of class game here.
 *
 * @author cara templeton
 * @version 25/03/2022
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
public class finalgame
{
    // instance variables - replace the example below with your own
    private int x;
    boolean running = true;
    boolean hasCoco=false;
    boolean hasAxe=false;
    boolean hasWood=false;
    boolean branches=false;
    boolean lose=false;
    boolean dinner=false;
    public int room=0;
    String command;
    String splitRooms;
    String splitExtra;
    String splitLook;
    String areas[];
    String parts[];
    String looking[];
    File rooms=new File("roomtext.txt");
    File extra=new File("extras.txt");
    File look=new File("look.txt");

    /**
     * Constructor for objects of class game
     */
    public finalgame()
    {
        // initialise instance variables
        try {
            Scanner readfiles = new Scanner(rooms); 
            while (readfiles.hasNextLine()){
                splitRooms=(readfiles.nextLine());
            }
            areas=splitRooms.split("123");
        } catch (IOException e) {
            System.out.println("something went wrong. (rooms)");
        }

        try {
            Scanner readExtra = new Scanner(extra); 
            while (readExtra.hasNextLine()){
                splitExtra=(readExtra.nextLine());
            }
            parts=splitExtra.split("123");
        } catch (IOException e) {
            System.out.println("something went wrong. (extra)");
        }

        try {
            Scanner readLook = new Scanner(look); 
            while (readLook.hasNextLine()){
                splitLook=(readLook.nextLine());
            }
            looking=splitLook.split("123");
        } catch (IOException e) {
            System.out.println("something went wrong. (look)");
        }
        x = 0;
        room=0;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("commands: north, south, east, west, up, down, pick up, use *item*, look");
        System.out.println(areas[room]);
        System.out.println("Where would you like to go?");

        while (running==true) {
            command=keyboard.nextLine();
            switch (command) {
                case "south": 
                if (room==0) {
                    room=1;
                    System.out.println(areas[room]);
                    if (branches==false) {
                        System.out.println(parts[4]);
                    }
                } else if (room==5) {
                    room=room-2;
                    System.out.println(areas[room]);
                    hasAxe=false;
                    hasWood=false;
                    if (hasCoco) {
                        System.out.println(parts[0]);
                    } else {
                        System.out.println(parts[1]);
                        lose=true;
                    }
                } else {
                    System.out.println("you can't go that way!");
                }
                break;
                case "west":
                if (room==1 && branches) {
                    room=room+3;
                    System.out.println(areas[room]);
                } else if (room==1 && !branches) { 
                    System.out.println("The path has been blocked by branches...");
                } else if (room==2) {
                    room=room-2;
                    System.out.println(areas[room]);
                }
                else if (room==5) {
                    room=room+2;
                    System.out.println(areas[room]);
                } else {
                    System.out.println("you can't go that way!");
                }
                break;
                case "north": 
                if (room==1) {
                    room--;
                    System.out.println(areas[room]);
                } else  {
                    System.out.println("you can't go that way!");
                }
                break;
                case "east": 
                if (room==0) {
                    room=room+2;
                    System.out.println(areas[room]);
                } else if (room==4){
                    room=room-3;
                    System.out.println(areas[room]);
                    if (branches==false) {
                        System.out.println(parts[4]);
                    }
                } else if (room==7){
                    room=room-2;
                    System.out.println(areas[room]);
                } else {
                    System.out.println("you can't go that way!");
                }
                break;
                case "down":
                if (room==1) {
                    room=room+5;
                    System.out.println(areas[room]);
                } else if (room==2){
                    room=room+3;
                    System.out.println(areas[room]);
                } else {
                    System.out.println("you can't go that way!");
                }
                break;
                case "up":
                if (room==6) {
                    room=room-5;
                    System.out.println(areas[room]);
                    if (branches==false) {
                        System.out.println(parts[4]);
                    }
                } else if (room==5){
                    room=room-3;
                    System.out.println(areas[room]);
                } else if (room==3) {
                    room=room+5;
                    System.out.println(areas[room]);
                    dinner=true;
                } else {
                    System.out.println("you can't go that way!");
                }
                break;
                case "pick up":
                if (room==4 && !hasCoco) {
                    System.out.println("You have picked up a coconut crab!");
                    hasCoco=true;
                } else if (room==4 && hasCoco) {
                    System.out.println("You already have one!");
                } else if (room==7 && !hasAxe) {
                    System.out.println("Obtained: Axe");
                    hasAxe=true;
                } else if (room==7 && hasAxe) {
                    System.out.println("There's nothing here to pick up.");
                } else {
                    System.out.println("..nothing to pick up.");
                }
                break;
                case "use crab":
                case "use coconut crab":
                if (room==3 && hasCoco) {
                    System.out.println(parts[2]);
                } else if (room!=3 && hasCoco) {
                    System.out.println("Don't use it here! Save it for something more important.");
                } else {
                    System.out.println("You don't have a coconut crab to use...");
                }
                break;
                case "use axe":
                if (room==1 && hasAxe && !branches) {
                    System.out.println("You cut down the branches. The pathway west is now cleared.");
                    branches=true;
                } else if (room==1 && hasAxe && branches) {
                    System.out.println("The branches have already been cut..");
                } else if (room!=1 && room!=5 && hasAxe) {
                    System.out.println("Nothing good to chop here...");
                } else if (room==5 && hasAxe && !hasWood) {
                    System.out.println("You got some useless wood...");
                    hasWood=true;
                } else if (room==5 && hasAxe && hasWood) {
                    System.out.println("You don't need more useless wood..");
                }else { 
                    System.out.println("You dont have an axe!");
                }
                break;
                case "place wood":
                case "use wood":
                if (hasWood==true && room!=7) {
                    System.out.println("The wood uselessly plopped on the ground...");
                    hasWood=false;
                } else if (room==7 && hasWood) {
                    System.out.println("Good firewood maybe????");
                    hasWood=false;
                }
                else {
                    System.out.println("What??? You don't have any wood.");
                }
                break;
                case "look":
                System.out.println(looking[room]);
                break;
                case "end game":
                lose=true;
                break;
                default: System.out.println("try something else");
            }

            if (lose==true) {
                System.out.println("YOU LOST. THE END.");
                running=false;
            }
            if (dinner==true) {
                System.out.println(parts[3]);

                System.out.println("YOU WON!!! CONGRATS.");
                running=false;
            }
        }
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}