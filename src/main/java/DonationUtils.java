import java.util.ArrayList;

public class DonationUtils {

    public static ArrayList<TotalDonation> aggregateDonations(ArrayList<Donation> donations) {
        //Create total donations arraylist
        ArrayList<TotalDonation> totPeople = new ArrayList<>();

        if(donations == null) {
            return totPeople;
        }
        //Database for seen names
        ArrayList<String> seenIds = new ArrayList<>();

        //Total donation amount variable per person
        double totalDon = 0;

        //Total donation count per person
        int donationCount = 0;



        //Loop through donations arraylist and check for total donations from each name
        for(int i = 0; i < donations.size(); i++)
        {
            if(donations.get(i) == null || donations.get(i).getUser() == null || donations.get(i).getAmount() < 0)
            {
                return totPeople;
            }

            String currentName = donations.get(i).getUser();
            boolean seen = false;
            for(String a: seenIds) {
                if (a.equals(currentName)) {
                    seen = true;
                    break;
                }
            }

            //If name has been seen, skip adding up all its donations
            if(seen) {continue;}

            //Add up donations seen in the rest of the list
            for(int j = i; j < donations.size(); j++)
            {
                if(donations.get(j) == null || donations.get(j).getUser() == null || donations.get(j).getAmount() < 0) {
                    return totPeople;
                }

                if(currentName.equals(donations.get(j).getUser())) {
                    totalDon += donations.get(j).getAmount();
                    donationCount++;
                }

            }

            TotalDonation a = new TotalDonation(currentName, totalDon, donationCount);
            totPeople.add(a);
            totalDon = 0;
            donationCount = 0;
            seenIds.add(currentName);
        }
        return totPeople;
    }

    public static void main(String[] args) {
        Donation a = new Donation("Mike", 60);
        Donation b = new Donation("Mike", 10);
        Donation c = new Donation("Mike", 60);
        Donation d = new Donation("Hailey", 10.0);

        ArrayList<Donation> charity = new ArrayList<>();
        charity.add(a);
        charity.add(b);
        charity.add(c);
        charity.add(d);

        for(TotalDonation note: DonationUtils.aggregateDonations(charity))
        {
            System.out.println(note.toString());
        }

    }
}