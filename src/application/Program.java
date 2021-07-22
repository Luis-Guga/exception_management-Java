package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.DomainException;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		boolean error=false;
		
		System.out.println("Hotel reserving. Type anything.");

		do {
		sc.nextLine();
		if(error==true) System.out.println("Please, try again:\n");
		error=false;
		try {
			System.out.printf("Room number: ");
			int roomNumber = sc.nextInt();
			sc.nextLine();
			System.out.printf("Check-in date (dd/MM/yyyy): ");
			Date checkIn = sdf.parse(sc.next());
			System.out.printf("Check-out date (dd/MM/yyyy): ");
			Date checkOut = sdf.parse(sc.next());

			Reservation reserv = new Reservation(roomNumber, checkIn, checkOut);
			System.out.println("\nReservation\n" + reserv.toString());
			System.out.println();
			System.out.println();

			System.out.println("Enter data to update the reservation:");
			System.out.printf("Check-in date (dd/MM/yyyy): ");
			checkIn = sdf.parse(sc.next());
			System.out.printf("Check-out date (dd/MM/yyyy): ");
			checkOut = sdf.parse(sc.next());

			reserv.updateDates(checkIn, checkOut);

			System.out.println("Reservation: " + reserv.toString());
		} catch (ParseException e) {
			System.out.println("Invalid date format.");
			error=true;
			
		} catch (DomainException e) {
			System.out.println("Error in reservation: " + e.getMessage());
			error=true;
		}catch (InputMismatchException e) {
			System.out.println("\nInvalid data format.");
			error=true;
		}
		}while(error=true);
		sc.close();
	}

}
