package TEST;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeekDaysExample {

    public static List<String> getDaysOfWeek(int year, int weekNumber) {
        List<String> daysOfWeek = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Initialize Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, weekNumber);

        // Set to the first day of the week (Monday)
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        // Loop through the 7 days of the week
        for (int i = 0; i < 7; i++) {
            Date date = calendar.getTime();
            daysOfWeek.add(dateFormat.format(date));
            calendar.add(Calendar.DAY_OF_MONTH, 1); // Move to the next day
        }

        return daysOfWeek;
    }

    public static void main(String[] args) {
        int year = 2024;
        int weekNumber = 32;

        List<String> daysOfWeek = getDaysOfWeek(year, weekNumber);

        System.out.println("Danh sách các ngày trong tuần thứ " + weekNumber + " của năm " + year + ":");
        for (String day : daysOfWeek) {
            System.out.println(day);
        }
    }
}