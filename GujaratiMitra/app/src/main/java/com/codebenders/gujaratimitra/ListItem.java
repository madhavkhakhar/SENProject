package com.codebenders.gujaratimitra;

/**
 * Created by madhav on 13/2/15.
 */
public class ListItem {
    private int icon;

    public ListItem(){}

    public ListItem(int  icon){
        this.icon = icon;
    }

    public ListItem(int icon, boolean isCounterVisible, String count){
        this.icon = icon;
    }


    public int getIcon(){
        return this.icon;
    }

    public void setIcon(int icon){
        this.icon = icon;
    }

}
