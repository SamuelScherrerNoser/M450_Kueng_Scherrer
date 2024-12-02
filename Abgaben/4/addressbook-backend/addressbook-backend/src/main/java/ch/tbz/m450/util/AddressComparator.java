package ch.tbz.m450.util;

import ch.tbz.m450.repository.Address;

import java.util.Comparator;

public class AddressComparator implements Comparator<Address> {
    public int isLastNameEqual(Address a1, Address a2) {
        return a1.getLastname().compareToIgnoreCase(a2.getLastname());
    }

    public int isFirstNameEqual(Address a1, Address a2) {
        return a1.getFirstname().compareToIgnoreCase(a2.getFirstname());
    }

    public int isPhonenumberEqual(Address a1, Address a2) {
        return a1.getPhonenumber().compareToIgnoreCase(a2.getPhonenumber());
    }

    public int isRegistrationDateEqual(Address a1, Address a2) {
        return a1.getRegistrationDate().compareTo(a2.getRegistrationDate());
    }

    @Override
    public int compare(Address a1, Address a2) {
        int ln = isLastNameEqual(a1, a2);
        int fn = isFirstNameEqual(a1, a2);
        int pn = isPhonenumberEqual(a1, a2);
        int rd = isRegistrationDateEqual(a1, a2);

        return ln == fn && fn == pn && pn == rd ? ln : (ln + fn + pn + rd) > 0 ? 1 : -1;
    }
}