package game.tictactoe.model;

public class Player {

    private String name;
    private Integer quantity; /* the number of elements installed by the player on the field
    (when 3 is reached, we begin to remember the path for the earliest placed one,
    so that when installing a new element, we delete the earliest one,
    thereby saving exactly 3 elements on the player's field) */
    private Integer[] excess; // coordinates of the cell of the field from where the element will be removed

    public Player(String name, Integer quantity, Integer[] excess) {
        this.name = name;
        this.quantity = quantity;
        this.excess = excess;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setExcess(Integer[] excess) {
        this.excess = excess;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer[] getExcess() {
        return excess;
    }
}
