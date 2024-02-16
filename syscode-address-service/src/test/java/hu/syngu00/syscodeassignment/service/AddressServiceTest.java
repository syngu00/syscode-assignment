package hu.syngu00.syscodeassignment.service;


import hu.syngu00.syscodeassignment.model.Address;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class AddressServiceTest {

    @Test
    void getAnAddressShouldReturnAnAddressFromTestAddresses() {
        AddressService addressService = new AddressService();

        Address result = addressService.getAnAddress();

        // Assert that the result is not null
        assertThat(result, is(notNullValue()));

        // Assert that the result is an instance of Address
        assertThat(result, is(instanceOf(Address.class)));

        // Assert that the result is one of the TEST_ADDRESSES
        assertThat(AddressService.TEST_ADDRESSES, hasItem(result));
    }
}


