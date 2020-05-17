import java.util.*;

/**
 * The BattleDecider class is used to allow the user to
 * build a list of String type elements which the class
 * will sequentially pair list elements at random in a
 * 'battle' until only one remains. It is essentially
 * an inefficient RNG, but is a good challenge for me
 * at my current skill level.
 */
public class BattleDecider {

    //the list of user made choices
    private ArrayList<String> choiceList;

    /**
     * Instances of the BattleDecider class are
     * constructed with the choiceList instantiated
     * as a new ArrayList.
     */
    public BattleDecider() {
        this.choiceList = new ArrayList<>();
    }

    /**
     * Returns the choiceList as an
     * ArrayList type.
     */
    public ArrayList<String> getChoiceList() {
        return choiceList;
    }

    /**
     * Returns an element of choiceList
     * specified by an index of type int
     * in the argument.
     */
    public String getBattler(int anIndex) {
        return this.choiceList.get(anIndex);
    }

    /**
     * Adds an element of type String
     * to choiceList.
     */
    public void addBattler(String aString) {
        this.choiceList.add(aString);
    }

    /**
     * Removes an element of ChoiceList
     * specified by an index of type int
     * in the argument.
     */
    public void deleteBattler(int anIndex) {
        this.choiceList.remove(anIndex);
    }

    /**
     * Creates an object of the Random class
     * to return a random int between 0 and
     * the current size of choiceList used
     * to choose the First 'battler'.
     */
    public int randomFirstBattler() {
        Random rnd = new Random();
        return rnd.nextInt(this.choiceList.size());
    }

    /**
     * Calls on the randomFirstBattler() method
     * to return an int and compares this to the
     * argument int to ensure that the same element
     * is not chosen twice.
     */
    public int randomSecondBattler(int firstBattler) {
        int secondBattler = this.randomFirstBattler();
        if (secondBattler == firstBattler) {
            while (secondBattler == firstBattler) {
                secondBattler = this.randomFirstBattler();
            }
        }
        return secondBattler;
    }

    /**
     *  Generates a random Boolean to decide the
     *  victor of each battle. Essentially a coin
     *  toss.
     */
    public boolean battle() {
        Random rnd = new Random();
        return rnd.nextBoolean();
    }

    /**
     * Iteratively pairs random elements of the list and
     * decides at random which to delete until there is
     * only one element left on the list.
     */
    public void commenceBattle() {

        int firstBattler;
        int secondBattler;
        String firstName;
        String secondName;

        while (this.choiceList.size() > 1) { //until one element left in list

            firstBattler = this.randomFirstBattler(); //choose first battler
            secondBattler = this.randomSecondBattler(firstBattler); //choose opponent

            firstName = this.getBattler(firstBattler); //assign Strings from list
            secondName = this.getBattler(secondBattler);//to variable for output to user
            System.out.println("Battle between " + firstName
             + " and " + secondName + " commence.");

            if (this.battle()) { // random choice for the victor
                System.out.println(firstName + " is the victor. " +
                        secondName + " has been removed from consideration.");
                this.deleteBattler(secondBattler);
            }
            else {
                System.out.println(secondName + " is the victor. " +
                        firstName + " has been removed from consideration.");
                this.deleteBattler(firstBattler);
            }
        }
    }

    public static void main(String[] args) {

        BattleDecider bd = new BattleDecider();
        Scanner input = new Scanner(System.in); //to parse user input
        boolean battle = false; //condition for input loop

        try {

            System.out.println("Welcome to the BattleDecider. If you are having trouble"
                    + " deciding between two or more objects this program will pit them against"
                    + " one another in a fight to the death to aid in your decision.");

            while (!battle) {

                System.out.println("Please enter an element of your decision below or"
                        + " enter 'battle' to commence the battle.");

                //parse user input to store in variable
                //empty String used to hold the user input
                String currentChoice = input.nextLine();

                if (currentChoice.equals("battle") || currentChoice.equals("Battle") || currentChoice.equals("BATTLE")) {

                    if (bd.getChoiceList().size() < 2) { //list length check
                        System.out.println("Not enough items submitted. Minimum of 2 required.");
                    }
                    else { //if list long enough, proceed with battle
                        battle = true;
                    }
                }
                else {
                    bd.addBattler(currentChoice); //add String to list
                    }
                }

            bd.commenceBattle(); //battle is decided

            System.out.println("The battle is over. Amidst the smouldering ruin there stands a " +
                    "lone victor. The choice is: " + bd.getBattler(0));
            }
        catch (Exception anException) {
            System.out.println("Error: " + anException);
        }
        finally {
            try {
                input.close();
            }
            catch (Exception anException) {
                System.out.println("Error: " + anException);
            }
        }
    }
}