package core;

public class Cell {
    private final int x;
    private final int y;
    private State state;
    private boolean isHidden;
    private boolean isMark;

    //possible states of the cell
    public enum State {
        Empty,
        Mine,
        MineExploded
    }

    //possible click results
    public enum ClickResult {
        Default,
        Open,
        Explode
    }

    public Cell(int x, int y, boolean isMine) {
        this.x = x;
        this.y = y;
        this.isMark = false;
        this.isHidden = true;
        this.state = State.Empty;
        if (isMine) this.state = State.Mine;
    }

    public ClickResult clickResult(String mouseButton) {
        if (this.isHidden) {
            if (mouseButton.equals("PRIMARY") && !this.isMark) {
                if (this.state == State.Mine) {
                    this.state = State.MineExploded;
                    return ClickResult.Explode;
                }

                if (this.state == State.Empty) {
                    this.isHidden = false;
                    return ClickResult.Open;
                }
            } else if (mouseButton.equals("SECONDARY")) {
                this.isMark = !this.isMark;
            }
        }

        return ClickResult.Default;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return  this.y;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setMark(Boolean mark) {
        this.isMark = mark;
    }

    public boolean isHidden() {
        return this.isHidden;
    }

    public boolean isMark() {
        return this.isMark;
    }

    public void openCell() {
        this.isHidden = false;
    }
}
