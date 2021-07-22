package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	private int roomNumber;
	private Date checkIn;
	private Date checkOut;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Reservation() {
	}

	public Reservation(int roomNumber, Date checkIn, Date checkOut) throws DomainException {
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		if(!checkOut.after(checkIn))
			throw new DomainException("Error in reservation: Check-out date must be after check-in date.");
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public void updateDates(Date checkIn, Date checkOut) throws DomainException {
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now))
			throw new DomainException("Reservation dates for update must be future dates.");

		else if (!checkOut.after(checkIn))
			throw new DomainException("Check-out date must be after check-in date.");

		this.checkIn = checkOut;
		this.checkIn = checkIn;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Room: " + roomNumber + ", ");
		sb.append("check in: " + sdf.format(checkIn) + ", ");
		sb.append("check out: " + sdf.format(checkOut) + ", ");
		sb.append(duration() + " nights.");
		return sb.toString();
	}

}
