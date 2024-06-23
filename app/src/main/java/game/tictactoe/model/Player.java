package game.tictactoe.model;

public class Player {

    private String name;
    private int quantity; // the number of elements placed by the player on the board
    private Cell[] elements; // array of elements placed on the board

    public Player(String name) {
        this.name = name;
        this.quantity = 0;
        this.elements = new Cell[3]; // maximum 3 elements per player
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public Cell[] getElements() {
        return elements;
    }

    public void addElement(Cell cell) {
        if (quantity == 3) {
            removeOldestElement();
        }

        // add new element if player has space
        elements[quantity] = cell;
        quantity++;
    }

    private void removeOldestElement() {
        // find the oldest element (first in the array)
        Cell oldestElement = elements[0];

        // set its state to "nothing"
        oldestElement.setImageLink("nothing");
        oldestElement.setPlayer(null);

        // shift all elements to the left to make space for new element
        for (int i = 0; i < 2; i++) {
            elements[i] = elements[i + 1];
        }

        // clear the reference to the last element
        elements[quantity - 1] = null;

        // decrease the number of elements on the board
        quantity--;
    }

}
