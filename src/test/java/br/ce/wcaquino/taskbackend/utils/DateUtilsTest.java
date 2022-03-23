package br.ce.wcaquino.taskbackend.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void shouldReturnFalseToPastDates() {
		final LocalDate date = LocalDate.of(2010, 1, 1);
		assertFalse(DateUtils.isEqualOrFutureDate(date));
	}

	@Test
	public void shouldReturnTrueToCurrentDate() {
		final LocalDate date = LocalDate.now();
		assertTrue(DateUtils.isEqualOrFutureDate(date));
	}

	@Test
	public void shouldReturnTrueToFutureDates() {
		final LocalDate dateFutureYear = LocalDate.now().plusYears(1);
		assertTrue(DateUtils.isEqualOrFutureDate(dateFutureYear));

		final LocalDate dateFutureMonth = LocalDate.now().plusMonths(1);
		assertTrue(DateUtils.isEqualOrFutureDate(dateFutureMonth));

		final LocalDate dateFutureDay = LocalDate.now().plusDays(1);
		assertTrue(DateUtils.isEqualOrFutureDate(dateFutureDay));
	}

}
