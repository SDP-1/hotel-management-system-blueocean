package db;

import module.Booking;
import sun.util.resources.LocaleData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class BookingDataBase {
    public static ArrayList<Booking> bookings= new ArrayList<>();

    static{
        bookings.add(new Booking(543,"Dasun","123456789876543v","0771265396","Dasun123@gmail.com","Karapitiya",2,3,false,true,LocalDate.parse("2022-01-08"),LocalDate.parse("2022-01-09"),LocalDate.parse("2022-01-12"),"","18000 Rs"));
        bookings.add(new Booking(670,"kunar","5768753664546545","0783245098","kumar@gmail.com","USA",6,3,true,false,LocalDate.parse("2022-01-08"),LocalDate.parse("2022-01-09"),LocalDate.parse("2022-01-12"),"he went out for 2 days trip","300 $"));
        bookings.add(new Booking(540,"Gimhan","657362473575373v","0774534578","Gimhancathuranga123@gmail.com","Homagama",5,2,true,true,LocalDate.parse("2022-01-02"),LocalDate.parse("2022-01-04"),LocalDate.parse("2022-01-12"),"add one extar bed","670 $"));
        bookings.add(new Booking(230,"Pasindu","4346575875643v","0712345645","pasindu@gmail.com","Dabulla",3,0,false,true, LocalDate.parse("2022-01-01"),LocalDate.parse("2022-01-02"),LocalDate.parse("2022-01-08"),"he cam and tok with me 2022-01-01 at 9.00am","25000 Rs"));
        bookings.add(new Booking(560,"Chan","575657856536536375","0774530983","chan@gmail.com","Chaina",2,2,true,true,LocalDate.parse("2021-12-01"),LocalDate.parse("2021-12-13"),LocalDate.parse("2021-12-31"),"hikz...","250 $"));
        bookings.add(new Booking(880,"Chun","68665768647867565","0778363826","chun@gmail.com","Chaina",3,2,true,true,LocalDate.parse("2021-12-01"),LocalDate.parse("2021-12-13"),LocalDate.parse("2021-12-31"),"hikz...","350 $"));
        bookings.add(new Booking(450,"sahan","426426426746425464v","0712309789","sahan@gmail.com","Galle",6,0,false,true,LocalDate.parse("2021-11-15"),LocalDate.parse("2021-12-05"),LocalDate.parse("2021-12-31"),"","50000 Rs"));
        bookings.add(new Booking(970,"nadun","4542642653765364v","0774568325","nadun@gmail.com","Hikkaduwa",7,1,false,true,LocalDate.parse("2021-11-21"),LocalDate.parse("2021-12-01"),LocalDate.parse("2021-12-31"),"","45000 Rs"));
        bookings.add(new Booking(210,"kabir","5768564657675645656","0780954683","kabir@gmail.com","Rusiya",3,3,true,true,LocalDate.parse("2021-10-01"),LocalDate.parse("2021-11-01"),LocalDate.parse("2021-12-01"),"","350 $"));
        bookings.add(new Booking(120,"jonathan","45435756465465757","0717465382","jonathan@gmail.com","UK",1,3,true,true,LocalDate.parse("2021-09-08"),LocalDate.parse("2021-09-13"),LocalDate.parse("2021-09-23"),"","250 $"));
    }
}
