import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AggregateDonationsTest {

    @DisplayName("Each Donation has a distinct user")
    @Test
    void distinctUsers() {
        ArrayList<Donation> donations = new ArrayList<>();
        donations.add(new Donation("Mike", 60));
        donations.add(new Donation("Hailey", 10));
        donations.add(new Donation("Sam", 25.5));

        ArrayList<TotalDonation> result = DonationUtils.aggregateDonations(donations);

        Set<TotalDonation> expected = Set.of(
                new TotalDonation("Mike", 60, 1),
                new TotalDonation("Hailey", 10, 1),
                new TotalDonation("Sam", 25.5, 1));
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(expected, new HashSet<>(result));
    }

    @DisplayName("Some Donations have the same user")
    @Test
    void sameUsers() {
        ArrayList<Donation> donations = new ArrayList<>();
        donations.add(new Donation("Mike", 60));
        donations.add(new Donation("Hailey", 10));
        donations.add(new Donation("Mike", 10));
        donations.add(new Donation("Mike", 60));

        ArrayList<TotalDonation> result = DonationUtils.aggregateDonations(donations);

        Set<TotalDonation> expected = Set.of(
                new TotalDonation("Mike", 130, 3),
                new TotalDonation("Hailey", 10, 1));
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expected, new HashSet<>(result));
    }

    @DisplayName("Return empty list when input is null")
    @Test
    void nullList() {
        ArrayList<TotalDonation> result = DonationUtils.aggregateDonations(null);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @DisplayName("Return empty list when input contains a null Donation")
    @Test
    void nullDonation() {
        ArrayList<Donation> donations = new ArrayList<>();
        donations.add(new Donation("Mike", 60));
        donations.add(null);
        donations.add(new Donation("Hailey", 10));

        ArrayList<TotalDonation> result = DonationUtils.aggregateDonations(donations);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @DisplayName("Return empty list when a Donation has a null user")
    @Test
    void nullUser() {
        ArrayList<Donation> donations = new ArrayList<>();
        donations.add(new Donation("Mike", 60));
        donations.add(new Donation(null, 20));
        donations.add(new Donation("Hailey", 10));

        ArrayList<TotalDonation> result = DonationUtils.aggregateDonations(donations);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @DisplayName("Return empty list when a Donation has a negative amount")
    @Test
    void negativeAmount() {
        ArrayList<Donation> donations = new ArrayList<>();
        donations.add(new Donation("Mike", 60));
        donations.add(new Donation("Hailey", -10));
        donations.add(new Donation("Sam", 25));

        ArrayList<TotalDonation> result = DonationUtils.aggregateDonations(donations);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
