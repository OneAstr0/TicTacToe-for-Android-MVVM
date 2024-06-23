package game.tictactoe.model;

public class Cell {

    private Player player;
    private String imageLink;

    public Cell(Player player, String imageLink) {
        this.player = player;
        this.imageLink = imageLink;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public boolean isEmpty() {
        return player == null || imageLink == "nothing";
    }


}
