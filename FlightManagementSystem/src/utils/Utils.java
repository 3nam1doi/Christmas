/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Crew;
import entities.Flight;
import entities.Passenger;
import entities.User;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Nguyen Trung Nam
 */
public class Utils {

    public static String getString(String msg, boolean mayEmpty) {
        Scanner sc = new Scanner(System.in);
        String result = null;
        boolean flag = true;
        while (flag) {
            try {
                System.out.print(msg);
                result = sc.nextLine();
                if (result.isEmpty()) {
                    result = null;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Try again!");
            }
            if (mayEmpty) {
                flag = false;
            }
            if (mayEmpty == false && result != null) {
                flag = false;
            }
        }
        return result;
    }

    public static int getInt(String msg, int min, int max) {
        Scanner sc = new Scanner(System.in);
        int result = Integer.MIN_VALUE;
        while (result < min || result > max) {
            try {
                System.out.print(msg + "(from " + min + " to " + max + ")   ");
                result = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Try again!");
            }

        }
        return result;
    }
    
    public static int getIntMenu(String sms, int min, int max) {
        int n = 0;
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.print(sms + "  ");
                n = Integer.parseInt(sc.nextLine());
                if (n >= min && n < max) {
                    return n;
                }

            } catch (NumberFormatException e) {
                System.out.println();
            }
        }

    }

    public static boolean checkProductID(String str) {
        if (str.length() != 5) {
            return false;
        }

        if (str.charAt(0) != 'F') {
            return false;
        }

        for (int i = 1; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkProductIDSeat(String str) {
        if (str.length() != 5) {
            return false;
        }

        if (str.charAt(0) != 'S') {
            return false;
        }

        for (int i = 1; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static String getCode(String smg, String err, int mode, HashMap<String, Flight> flightMap) {
        String s;
        System.out.print(smg);
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();

        while (!s.isEmpty()) {
            if ((mode == 1 && !flightMap.containsKey(s))
                    || (mode == 2 && flightMap.containsKey(s))) {
                break;
            }
            System.err.println(err);
            s = sc.nextLine();
        }

        return s;
    }
    
    public static String getIdCrew(String smg, String err, int mode, HashMap<String, Crew> CrewAssignment) {
        String s;
        System.out.print(smg);
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();

        while (!s.isEmpty()) {
            if ((mode == 1 && !CrewAssignment.containsKey(s))
                    || (mode == 2 && CrewAssignment.containsKey(s))) {
                break;
            }
            System.err.println(err);
            s = sc.nextLine();
        }

        return s;
    }
    
    public static String getUsername(String smg, String err, int mode, HashMap<String, User> UserAccount) {
        String s;
        System.out.print(smg);
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();

        while (!s.isEmpty()) {
            if ((mode == 1 && !UserAccount.containsKey(s))
                    || (mode == 2 && UserAccount.containsKey(s))) {
                break;
            }
            System.err.println(err);
            System.out.print("Please enter again: ");
            s = sc.nextLine();
        }

        return s;
    }

    public static String getCodeSeat(String smg, String err, int mode, HashMap<String, Passenger> flightMap) {
        String s;
        System.out.print(smg);
        Scanner sc = new Scanner(System.in);
        s = sc.nextLine();

        while (!s.isEmpty()) {
            if ((mode == 1 && !flightMap.containsKey(s))
                    || (mode == 2 && flightMap.containsKey(s))) {
                break;
            }
            System.err.println(err);
            s = sc.nextLine();
        }

        return s;
    }

    public static String getValidDateTime(String welcome, String departureTime) {
        boolean check = true;
        String dateTime = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);

            dateTime = sc.nextLine();
            if (!isValidDateTime(dateTime)) {
                System.out.println("Invalid date and time format! Please use yyyy/MM/dd HH:mm.");
            } else if (!isDateTimeAfter(dateTime, departureTime)) {
                System.out.println("Arrival time must be later than departure time.");
            } else {
                check = false;
            }
        } while (check);
        return dateTime;
    }

    public static boolean isDateTimeAfter(String arrivalDateTimeStr, String departureDateTimeStr) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    dateFormat.setLenient(false);

    try {
        Date arrivalDate = dateFormat.parse(arrivalDateTimeStr);
        Date departureDate = dateFormat.parse(departureDateTimeStr);

        return arrivalDate.after(departureDate);
    } catch (ParseException e) {
        return false;
    }
}

    public static boolean isValidDateTime(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        dateFormat.setLenient(false);

        try {
            Date date = dateFormat.parse(dateStr);

            return dateStr.equals(dateFormat.format(date));
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        dateFormat.setLenient(false);

        try {
            Date date = dateFormat.parse(dateStr);

            return dateStr.equals(dateFormat.format(date));
        } catch (ParseException e) {
            return false;
        }
    }

    public static String getDate(String welcome) {
        boolean check = true;
        String d = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);

            d = sc.nextLine();
            if (!isValidDate(d)) {
                System.out.println("Invalid date!");
            } else {
                check = false;
            }
        } while (check);
        return d;
    }

    public static boolean isValidEmail(String Email) {
        // Sử dụng biểu thức chính quy để kiểm tra định dạng email
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Email);
        return matcher.matches();
    }

    public static boolean isValidPhone(String Phone) {
        // Xóa bỏ khoảng trắng và các ký tự không phải số
        String cleanedPhone = Phone.replaceAll("[^0-9]", "");

        // Kiểm tra xem số điện thoại có đúng 10 chữ số hay không
        return cleanedPhone.length() == 10;
    }
}
