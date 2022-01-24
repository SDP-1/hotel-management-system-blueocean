package db;

import module.MealPack;

import java.util.ArrayList;

public class MealPackDataBase {
    public static ArrayList<MealPack>  mealPacks = new ArrayList<>();

    static {
        mealPacks.add(new MealPack(1,"local meals",5,700,"$","Rs",true,"Fruit,rice and vagitable","Nomal , Full rice can eat 2 people"));
        mealPacks.add(new MealPack(2,"Chinese meals",10,2000,"$","Rs",true,"Buffet","Best , Evaryday have"));
        mealPacks.add(new MealPack(3,"French meals",10,2000,"$","Rs",true,"Buffet ","Best ,  Evaryday have"));
        mealPacks.add(new MealPack(4,"See Food",50,9000,"$","Rs",false,"crayfish , Crab , Seaweed , octopus ","Best for all time"));
    }
}
