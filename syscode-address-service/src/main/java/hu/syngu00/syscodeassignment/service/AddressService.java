package hu.syngu00.syscodeassignment.service;

import hu.syngu00.syscodeassignment.model.Address;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddressService {

    public static final List<Address> TEST_ADDRESSES;
    public static final Random RANDOM = new Random();

    static {
        List<Address> tempAddresses = new ArrayList<>();
        tempAddresses.add(new Address(UUID.randomUUID(), "123 Main St, Anytown, USA"));
        tempAddresses.add(new Address(UUID.randomUUID(), "456 Maple Ave, Somewhere, USA"));
        tempAddresses.add(new Address(UUID.randomUUID(), "789 Oak St, Thistown, USA"));
        tempAddresses.add(new Address(UUID.randomUUID(), "101 Pine St, Yourtown, USA"));
        tempAddresses.add(new Address(UUID.randomUUID(), "202 Elm St, Mytown, USA"));
        tempAddresses.add(new Address(UUID.randomUUID(), "303 Birch St, Theirtown, USA"));
        tempAddresses.add(new Address(UUID.randomUUID(), "404 Cedar St, Othertown, USA"));
        tempAddresses.add(new Address(UUID.randomUUID(), "505 Dogwood St, Anothertown, USA"));
        tempAddresses.add(new Address(UUID.randomUUID(), "606 Fir St, Elsewhere, USA"));
        tempAddresses.add(new Address(UUID.randomUUID(), "707 Grove St, Nowheretown, USA"));

        TEST_ADDRESSES = Collections.unmodifiableList(tempAddresses);
    }

    public Address getAnAddress() {
        return TEST_ADDRESSES.get(RANDOM.nextInt(TEST_ADDRESSES.size()));
    }

}
