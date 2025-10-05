package fr.humanbooster.electricity_business.service.impl;

import fr.humanbooster.electricity_business.dto.AddressDTO;
import fr.humanbooster.electricity_business.dto.AddressRequestDTO;
import fr.humanbooster.electricity_business.mapper.AddressMapper;
import fr.humanbooster.electricity_business.model.Address;
import fr.humanbooster.electricity_business.repository.AddressRepository;
import fr.humanbooster.electricity_business.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public AddressDTO createAddress(AddressRequestDTO addressRequestDTO) {

        Address address = addressMapper.toEntity(addressRequestDTO);

        Address savedAddress = addressRepository.save(address);

        return addressMapper.toDto(savedAddress);
    }

    @Override
    public AddressDTO getAddressById(Long id) {
        return addressRepository.findById(id)
                .map(addressMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
    }

    @Override
    public List<AddressDTO> getAllAddresses() {
        List<AddressDTO> list = new ArrayList<>();
        for (Address address : addressRepository.findAll()) {
            AddressDTO dto = addressMapper.toDto(address);
            list.add(dto);
        }
        return list;
    }

    @Override
    public AddressDTO updateAddress(Long id, AddressRequestDTO addressRequestDTO) {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));

        addressMapper.updateEntityFromDto(addressRequestDTO, existingAddress);

        Address updatedAddress = addressRepository.save(existingAddress);
        return addressMapper.toDto(updatedAddress);
    }

    @Override
    public void deleteAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
        addressRepository.delete(address);
    }
}