package com.person_manager.utils.address;

import com.person_manager.entities.Address;
import com.person_manager.entities.Person;
import com.person_manager.exceptions.BadRequestException;
import com.person_manager.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

public class AddressUtils {

    public static void reorganizeAddressList(Person person, Address address) {
        List<Address> addressList = person.getAddressList();

        boolean newAddressIsPrincipal = address.isPrincipal();

        addressList.removeIf(existingAddress -> existingAddress.getAddressName().equalsIgnoreCase(address.getAddressName()));
        boolean addressExists = addressList.stream()
                .anyMatch(existingAddress -> existingAddress.getAddressName().equalsIgnoreCase(address.getAddressName()));

        if (!addressExists) {
            if (newAddressIsPrincipal) {
                addressList.removeIf(Address::isPrincipal);
                addressList.add(0, address);
            } else {
                addressList.add(address);
            }

            if (newAddressIsPrincipal) {
                for (Address a : addressList) {
                    if (!a.equals(address)) {
                        a.setPrincipal(false);
                    }
                }
            }
        } else {
            throw new BadRequestException("Address with the same name already exists in the list!");
        }
    }
}