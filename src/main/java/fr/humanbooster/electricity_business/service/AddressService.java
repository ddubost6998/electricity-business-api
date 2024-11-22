package fr.humanbooster.electricity_business.service;

import fr.humanbooster.electricity_business.dto.AddressDTO;

import java.util.List;

public interface AddressService {

    AddressDTO createAddress(AddressDTO addressDTO);

    AddressDTO getAddressById(Long id);

    List<AddressDTO> getAllAddresses();

    AddressDTO updateAddress(Long id, AddressDTO addressDTO);

    void deleteAddress(Long id);
}
