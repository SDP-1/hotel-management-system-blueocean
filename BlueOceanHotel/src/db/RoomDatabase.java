package db;

import module.Room;

import java.util.ArrayList;

public class RoomDatabase {
    public static ArrayList<Room> rooms = new ArrayList<Room>();

    static{
        rooms.add(new Room(1, 16,"$",200,"Rs",true,"Double Room","ist flow 1st room","2 beds 1 athroom and full kican",false,true));
        rooms.add(new Room(2, 18,"$",3455,"Rs",false,"Quad Room","1st flow 2nd room","2 beds 2 athroom and full kican",false,true));
        rooms.add(new Room(3, 30,"$",7000,"Rs",true,"Triple Room","outher","full home",true,false));
        rooms.add(new Room(4, 25,"$",4000,"Rs",false,"Single Room","2st flow 2nd room","2 beds 2 athroom and full kican",false,true));
        rooms.add(new Room(5, 20,"$",100,"Rs",false,"Triple Room","3st flow 1st room","2 beds 2 athroom and full kican",false,false));
        rooms.add(new Room(6, 25,"$",3000,"Rs",true,"Single Room","2st flow 4th room","2 beds 2 athroom and full kican",false,false));
        rooms.add(new Room(7, 23,"$",2500,"Rs",true,"Single Room","3st flow 4th room","1 beds 1 athroom and  kican",true,true));

    }
}
