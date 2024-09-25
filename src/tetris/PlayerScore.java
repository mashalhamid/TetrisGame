package tetris;

public class PlayerScore {
    private String name;
    private int score;
    private String config; // Add config field to store game configuration

    public PlayerScore(String name, int score, String config) {
        this.name = name;
        this.score = score;
        this.config = config;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getConfig() {
        return config;
    }
}
