package com.person_manager.utils.address;

import com.person_manager.entities.Address;
import com.person_manager.entities.Person;
import com.person_manager.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

public class AddressUtils {

    public static void reorganizeAddressList(Person person, Address address){
        List<Address> addressList = person.getAddressList();

        boolean newAddressIsPrincipal = address.isPrincipal();

        addressList.add(0, address);

        if (newAddressIsPrincipal) {
            for (Address a : addressList) {
                if (!a.equals(address)) {
                    a.setPrincipal(false);
                }
            }
        }

        Optional<Address> principalAddressOptional = addressList.stream()
                .filter(Address::isPrincipal)
                .findFirst();

        principalAddressOptional.ifPresent(principalAddress -> {
            addressList.remove(principalAddress);
            addressList.add(0, principalAddress);
        });
    }
}
