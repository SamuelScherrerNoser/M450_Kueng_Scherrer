package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;

import java.util.Comparator;

public class AddressComparator implements Comparator<Address> {

    @Override
    public int compare(Address a1, Address a2) {
        return a1.getLastname().compareToIgnoreCase(a2.getLastname());
    }

    // @Override
    public int compare2(Address a1, Address a2) {
        int lastNameComparison = a1.getLastname().compareToIgnoreCase(a2.getLastname());
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }

        int firstNameComparison = a1.getFirstname().compareToIgnoreCase(a2.getFirstname());
        if (firstNameComparison != 0) {
            return firstNameComparison;
        }

        int phonenumberComparison = a1.getPhonenumber().compareToIgnoreCase(a2.getPhonenumber());
        if(phonenumberComparison != 0) {
            return phonenumberComparison;
        }

        return a1.getRegistrationDate().compareTo(a2.getRegistrationDate());
    }
}