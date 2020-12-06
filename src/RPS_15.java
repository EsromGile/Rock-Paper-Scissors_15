import java.util.*;

public class RPS_15 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        
        //an array containing all of the options 
        String[] gesture = {"Rock", "Fire", "Scissors", "Snake", "Human", 
                            "Tree", "Wolf", "Sponge", "Paper", "Air",
                            "Water", "Dragon", "Devil", "Lightning", "Gun"
        };
        
        //briefly listing the options 
        System.out.println("Welcome to Rock-Paper-Scissors-15! Your choices are: ");
        for (int i = 0; i < gesture.length; i++){
            if (i == gesture.length - 1){
                System.out.println(gesture[i] + ".");
            }
            else{
                System.out.print(gesture[i] + " ");
            }
        }

        //prompt user to enter desired number of rounds
        System.out.print("\nHow many rounds would you like to go? The number must be odd: ");
        int n = -1;
        String c;
        
        //makes sure the input is valid
        for (int x = 0; x < 1;){
            c = input.nextLine();
            if (Character.isDigit(c.charAt(0)) == true){
                n = Integer.parseInt(c); 
                if (n % 2 == 0){
                    System.out.print("Invalid number of rounds. Try again. Remember, it must be odd: ");
                }
                else{
                    x++;
                }
            }
            else{
                System.out.print("Invalid entry. Try again. Enter a number: ");
            }
        }
        
        //caculate rounds to win and initialize user and computer counters as 0
        int roundsToWin = n / 2 + 1; 
        int user = 0, comp = 0; 

        //determine winner after computer or user winning needed number of rounds
        while (user < roundsToWin && comp  < roundsToWin){
            int computerIndex = (int)(Math.random() * 15);
            //int computerIndex = 6;  test value 

            //prompt user to enter a gesture
            System.out.print("\nEnter a gesture: ");
            String userInput = input.nextLine();
        
            //initialize user index as -1 for errors -- determine index of gesture entered
            int userIndex = -1;
            for (int i = 0; i < gesture.length; i++){
                if (userInput.equalsIgnoreCase(gesture[i]) == true){
                    userIndex = i; break;
                }
            }
            
            //error output 
            if (userIndex == -1){
                System.out.println("Invalid gesture. Try again.");
            }

            //regular output
            else {
                System.out.println("The computer got " + gesture[computerIndex] + ".");

                //determine the cutoff point of where who beats whom -- output winner or draw
                int cutoff = ((userIndex + 7) % 15) + 1; 
                if (computerIndex == userIndex){
                    System.out.println("Draw. That one doesn't count. Try again.");
                }
                else {
                    //error prevention -- will not run correctly unless specified
                    if (cutoff < userIndex){
                        //decide winner
                        if (computerIndex < cutoff ^ computerIndex > userIndex){
                            System.out.println(insertVerb(userIndex,computerIndex,gesture) + "You win."); user++;
                        }
                        else{
                            System.out.println(insertVerb(computerIndex,userIndex,gesture) + "You lose."); comp++;
                        }
                    }
                    else{
                        //decide winner
                        if (computerIndex < cutoff && computerIndex > userIndex){
                            System.out.println(insertVerb(userIndex,computerIndex,gesture) + "You win."); user++;
                        }
                        else{
                            System.out.println(insertVerb(computerIndex,userIndex,gesture) + "You lose."); comp++;
                        }
                    }
                }
            }
        }

        //output the overall winnner of the i-out-of-n number of games
        System.out.println((user > comp) ? ("\nYou won " + roundsToWin + "-out-of-" + n + "!\n") : ("\nThe computer won " + roundsToWin + "-out-of-" + n + "...\n"));
    }   

    public static String insertVerb(int winnerIndex, int loserIndex,String[] gesture){
        
        //winnerIndex for row and loserIndex for collumn
        String[][] verb = {  //rock,    fire,       scissors,   snake,      human,      tree,       wolf,       sponge,     paper,      air,        water,      dragon,     devil,      lightning,  gun
            /*rock*/        {"",       "pounds out","crushes",  "crushes",  "crushes",  "blocks",   "crushes",  "crushes",  "",         "",         "",         "",         "",         "",         ""              },
            /*fire*/        {"",        "",         "melts",    "burns",    "burns",    "burns",    "burns",    "burns",    "burns",    "",         "",         "",         "",         "",         ""              },
            /*scissors*/    {"",        "",         "",         "cut",      "cut",      "carve",    "cut",      "cut",      "cut",      "swish through","",     "",         "",         "",         ""              },       
            /*snake*/       {"",        "",         "",         "",         "bites",    "nests in", "bites",    "swallows", "nests in", "breathes","drinks",    "",         "",         "",         ""              },
            /*human*/       {"",        "",         "",         "",         "",         "plants",   "tames",   "cleans with","writes on","breathes","drinks",   "slays",    "",         "",         ""              },
            /*tree*/        {"",        "",         "",         "",         "",         "",         "shelters", "outlives", "becomes",  "produces", "drinks",   "shelters", "imprisons","",         ""              },
            /*wolf*/        {"",        "",         "",         "",         "",         "",         "",         "chews up", "chews up", "breathes", "drinks",   "outruns",  "bites",    "outruns",  ""              },
            /*sponge*/      {"",        "",         "",         "",         "",         "",         "",         "",         "soaks",    "uses",     "absorbs",  "cleanses", "cleanses", "conducts", "cleans"        },
            /*paper*/       {"covers",  "",         "",         "",         "",         "",         "",         "",         "",         "fans",     "floats on","rebukes",  "rebukes",  "defines",  "outlaws"       },
            /*air*/         {"erodes",  "blows out","",         "",         "",         "",         "",         "",         "",         "",         "evaporates","freezes", "chokes",   "creates",  "tarnishes"     },
            /*water*/       {"erodes",  "puts out","rusts",     "",         "",         "",         "",         "",         "",         "",         "",         "drowns",   "drowns",   "conducts", "rusts"         },
            /*dragon*/      {"rests on","breathes","is immune to","spawns", "",         "",         "",         "",         "",         "",         "",         "",         "commands", "breathes", "is immune to"  },
            /*devil*/       {"hurls",   "breaths", "is immune to","eats",   "possesses","",         "",         "",         "",         "",         "",         "",         "",         "casts",    "is immune to"  },
            /*lightning*/   {"splits",  "starts",  "melts",     "strikes",  "strikes",  "splits",   "",         "",         "",         "",         "",         "",         "",         "",         "melts"         },
            /*gun*/         {"targets", "targets", "outclasses","shoots",   "shoots",   "targets",  "shoots",   "",         "",         "",         "",         "",         "",         "",         ""              }
        };
        String result = gesture[winnerIndex] + " " + verb[winnerIndex][loserIndex] + " " + gesture[loserIndex] + ". ";
        return result;
    }
}

