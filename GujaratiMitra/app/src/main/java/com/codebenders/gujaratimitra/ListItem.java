package com.codebenders.gujaratimitra;

/**
 * Created by madhav on 13/2/15.
 */
public class ListItem {
    private int icon, score;

    public ListItem(int icon, int score) {
        this.icon = icon;
        this.score = score;
    }

    public int getIcon() {
        return this.icon;
    }

    public int getScore() {
        return this.score;
    }
}
