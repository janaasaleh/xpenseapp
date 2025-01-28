package com.example.xpenseapp;

import java.util.List;

public class Users {
    private String email;
    private  String name;
    private List<Item> Items;
    private List<Goal> Goals;
    private int walletBalance;


    public int getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(int walletBalance) {
        this.walletBalance = walletBalance;
    }

    public Users(String email,String name,List<Item> Items,List<Goal> Goals,int walletBalance) {
        this.email = email;
        this.name=name;
        this.Items=Items;
        this.Goals = Goals;
        this.walletBalance=walletBalance;
    }
    //Constructor with parameters
    public Users(String email, String name) {
        this.email = email;
        this.name = name;
    }
    //
    public Users() {}

    public List<Item> getItems() {
        return Items;
    }

    public void setItems(List<Item> items) {
        Items = items;
    }

    public List<Goal> getGoals() {
        return Goals;
    }

    public void setGoals(List<Goal> goals) {
        Goals = goals;
    }


//Constructor

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Classes
    public static class Item {
        private String Name;
        private int Amount;
        private int ID;

        public Item(String name, int amount, int ID) {
            this.Name = name;
            this.Amount = amount;
            this.ID = ID;
        }


        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public int getAmount() {
            return Amount;
        }

        public void setAmount(int amount) {
            Amount = amount;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }
    }

    public static class Goal {
        private String Name;
        private int targetAmount;
        private int savedAmount;

        public Goal(String name, int targetAmount, int savedAmount) {
            Name = name;
            this.targetAmount = targetAmount;
            this.savedAmount = savedAmount;
        }


        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public int getTargetAmount() {
            return targetAmount;
        }

        public void setTargetAmount(int targetAmount) {
            this.targetAmount = targetAmount;
        }

        public int getSavedAmount() {
            return savedAmount;
        }

        public void setSavedAmount(int savedAmount) {
            this.savedAmount = savedAmount;
        }
    }
}
