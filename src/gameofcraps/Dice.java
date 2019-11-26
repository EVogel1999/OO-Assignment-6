
package gameofcraps;

public class Dice {
    // Context class - current roll of the dice
    
    private int current_roll;
    private State objState;

    public Dice() {
        objState = State.InitialState(this);
    }

    public void setState(State newState) {
        objState = newState;
    }

    public State getState() {
        return objState;
    }

    public int getCurrentRoll() {
        return current_roll;
    }

    public void rollDice() {
        current_roll = generateRoll();
        System.out.println("rolled a " + current_roll);
        objState.roll_dice();
    }
    
    private int generateRoll(){
        return (int) ((Math.random() * 11) + 2);
    }
    // randmomly generated number between 2-12
    
}
