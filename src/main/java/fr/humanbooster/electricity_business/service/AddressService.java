package fr.humanbooster.electricity_business.service;

import fr.humanbooster.electricity_business.dto.AddressDTO;
import fr.humanbooster.electricity_business.dto.AddressRequestDTO;
import java.util.List;

public interface AddressService {

    AddressDTO createAddress(AddressRequestDTO addressRequestDTO);

    AddressDTO getAddressById(Long id);

    List<AddressDTO> getAllAddresses();

    AddressDTO updateAddress(Long id, AddressRequestDTO addressRequestDTO);

    void deleteAddress(Long id);
}
