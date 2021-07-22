package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.printf("Room number: ");
		int roomNumber = sc.nextInt();
		sc.nextLine();
		System.out.printf("Check-in date (dd/MM/yyyy): ");
		Date checkIn = sdf.parse(sc.next());
		System.out.printf("Check-out date (dd/MM/yyyy): ");
		Date checkOut = sdf.parse(sc.next());

		if (!checkOut.after(checkIn)) {
			System.out.println("Error in reservation: Check-out date must be after check-in date");
		} else {
			Reservation reserv = new Reservation(roomNumber, checkIn, checkOut);
			System.out.println("Reservation\n" + reserv.toString());
			System.out.println();

			System.out.println("Enter data to update the reservation:");
			System.out.printf("Check-in date (dd/MM/yyyy): ");
			checkIn = sdf.parse(sc.next());
			System.out.printf("Check-out date (dd/MM/yyyy): ");
			checkOut = sdf.parse(sc.next());

			if (reserv.updateDates(checkIn, checkOut) == null)
				System.out.println("Reservation\n" + reserv.toString());
			else 
				System.out.println(reserv.updateDates(checkIn, checkOut));

		}

		sc.close();
	}

}
