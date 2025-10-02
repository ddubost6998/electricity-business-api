package fr.humanbooster.electricity_business.service;

import fr.humanbooster.electricity_business.dto.AddressDTO;
import fr.humanbooster.electricity_business.dto.AddressRequestDTO;
import fr.humanbooster.electricity_business.mapper.AddressMapper;
import fr.humanbooster.electricity_business.model.Address;
import fr.humanbooster.electricity_business.repository.AddressRepository;
import fr.humanbooster.electricity_business.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceImplTest {

    @InjectMocks
    private AddressServiceImpl addressService;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressMapper addressMapper;

    @Test
    void shouldCreateAddressSuccessfully() {

        AddressRequestDTO requestDTO = new AddressRequestDTO();
        requestDTO.setStreet("123 Rue de la Paix");
        requestDTO.setCity("Paris");
        requestDTO.setZipcode("75001");

        Address addressToSave = new Address();
        addressToSave.setStreet(requestDTO.getStreet());
        addressToSave.setCity(requestDTO.getCity());
        addressToSave.setZipcode(requestDTO.getZipcode());

        Address savedAddress = new Address();
        savedAddress.setId(1L);
        savedAddress.setStreet(requestDTO.getStreet());
        savedAddress.setCity(requestDTO.getCity());
        savedAddress.setZipcode(requestDTO.getZipcode());

        AddressDTO expectedResponseDTO = new AddressDTO();
        expectedResponseDTO.setId(1L);
        expectedResponseDTO.setStreet(savedAddress.getStreet());
        expectedResponseDTO.setCity(savedAddress.getCity());
        expectedResponseDTO.setZipcode(savedAddress.getZipcode());

        when(addressMapper.toEntity(any(AddressRequestDTO.class))).thenReturn(addressToSave);
        when(addressRepository.save(any(Address.class))).thenReturn(savedAddress);
        when(addressMapper.toDto(any(Address.class))).thenReturn(expectedResponseDTO);

        AddressDTO result = addressService.createAddress(requestDTO);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getStreet()).isEqualTo("123 Rue de la Paix");
        assertThat(result.getCity()).isEqualTo("Paris");
        assertThat(result.getZipcode()).isEqualTo("75001");

        verify(addressMapper, times(1)).toEntity(requestDTO);
        verify(addressRepository, times(1)).save(addressToSave);
        verify(addressMapper, times(1)).toDto(savedAddress);
    }

    @Test
    void shouldGetAddressByIdSuccessfully() {
        Long addressId = 1L;
        Address foundAddress = new Address(addressId, "45 Rue Victor Hugo", "31000", "Toulouse");
        AddressDTO expectedDTO = new AddressDTO(addressId, "45 Rue Victor Hugo", "31000", "Toulouse");

        when(addressRepository.findById(addressId)).thenReturn(Optional.of(foundAddress));
        when(addressMapper.toDto(foundAddress)).thenReturn(expectedDTO);

        AddressDTO result = addressService.getAddressById(addressId);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(addressId);
        assertThat(result.getCity()).isEqualTo("Toulouse");
        verify(addressRepository, times(1)).findById(addressId);
        verify(addressMapper, times(1)).toDto(foundAddress);
    }

    @Test
    void shouldThrowExceptionWhenGetAddressByIdNotFound() {
        Long addressId = 99L;
        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> addressService.getAddressById(addressId));
        verify(addressRepository, times(1)).findById(addressId);
        verify(addressMapper, never()).toDto(any(Address.class)); // Assure que le mapper n'est pas appelé
    }

    @Test
    void shouldGetAllAddresses() {
        Address address1 = new Address(1L, "Rue A", "10000", "Ville A");
        Address address2 = new Address(2L, "Rue B", "20000", "Ville B");
        List<Address> addresses = Arrays.asList(address1, address2);

        AddressDTO dto1 = new AddressDTO(1L, "Rue A", "10000", "Ville A");
        AddressDTO dto2 = new AddressDTO(2L, "Rue B", "20000", "Ville B");

        when(addressRepository.findAll()).thenReturn(addresses);
        when(addressMapper.toDto(address1)).thenReturn(dto1);
        when(addressMapper.toDto(address2)).thenReturn(dto2);

        List<AddressDTO> result = addressService.getAllAddresses();

        assertThat(result).isNotNull();
        assertThat(result.get(0).getCity()).isEqualTo("Ville A");
        assertThat(result.get(1).getStreet()).isEqualTo("Rue B");
        verify(addressRepository, times(1)).findAll();
        verify(addressMapper, times(2)).toDto(any(Address.class));
    }

    @Test
    void shouldUpdateAddressSuccessfully() {
        // Arrange
        Long addressId = 1L;
        AddressRequestDTO requestDTO = new AddressRequestDTO("Nouvelle Rue", "75002", "Nouvelle Ville");

        Address existingAddress = new Address(addressId, "Ancienne Rue", "75001", "Ancienne Ville");
        Address updatedAddress = new Address(addressId, "Nouvelle Rue", "75002", "Nouvelle Ville"); // L'entité après mise à jour

        AddressDTO expectedDTO = new AddressDTO(addressId, "Nouvelle Rue", "75002", "Nouvelle Ville");

        when(addressRepository.findById(addressId)).thenReturn(Optional.of(existingAddress));

        doNothing().when(addressMapper).updateEntityFromDto(any(AddressRequestDTO.class), any(Address.class));
        when(addressRepository.save(any(Address.class))).thenReturn(updatedAddress);
        when(addressMapper.toDto(any(Address.class))).thenReturn(expectedDTO);

        AddressDTO result = addressService.updateAddress(addressId, requestDTO);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(addressId);
        assertThat(result.getCity()).isEqualTo("Nouvelle Ville");

        verify(addressRepository, times(1)).findById(addressId);
        verify(addressMapper, times(1)).updateEntityFromDto(requestDTO, existingAddress);
        verify(addressRepository, times(1)).save(existingAddress);
        verify(addressMapper, times(1)).toDto(updatedAddress);
    }

    @Test
    void shouldThrowExceptionWhenUpdateAddressNotFound() {
        Long addressId = 99L;
        AddressRequestDTO requestDTO = new AddressRequestDTO("Rue bidon", "00000", "Ville bidon");

        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> addressService.updateAddress(addressId, requestDTO));
        verify(addressRepository, times(1)).findById(addressId);
        verify(addressMapper, never()).updateEntityFromDto(any(AddressRequestDTO.class), any(Address.class));
        verify(addressRepository, never()).save(any(Address.class));
        verify(addressMapper, never()).toDto(any(Address.class));
    }

    @Test
    void shouldDeleteAddressSuccessfully() {
        Long addressId = 1L;
        Address addressToDelete = new Address(addressId, "Rue à supprimer", "10101", "Ville à supprimer");

        when(addressRepository.findById(addressId)).thenReturn(Optional.of(addressToDelete));
        doNothing().when(addressRepository).delete(addressToDelete);

        addressService.deleteAddress(addressId);

        verify(addressRepository, times(1)).findById(addressId);
        verify(addressRepository, times(1)).delete(addressToDelete);
    }

    @Test
    void shouldThrowExceptionWhenDeleteAddressNotFound() {
        Long addressId = 99L;
        when(addressRepository.findById(addressId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> addressService.deleteAddress(addressId));
        verify(addressRepository, times(1)).findById(addressId);
        verify(addressRepository, never()).delete(any(Address.class)); // Assure que delete n'est pas appelé
    }
}
