package fr.humanbooster.electricity_business.controller;

import fr.humanbooster.electricity_business.dto.AddressDTO;
import fr.humanbooster.electricity_business.dto.AddressRequestDTO;
import fr.humanbooster.electricity_business.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressRestController {

    private final AddressService addressService;

    public AddressRestController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressDTO> createAddress(@Valid @RequestBody AddressRequestDTO addressRequestDTO) {
        AddressDTO createdAddress = addressService.createAddress(addressRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        try {
            AddressDTO address = addressService.getAddressById(id);
            return ResponseEntity.ok(address);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        List<AddressDTO> addresses = addressService.getAllAddresses();
        return ResponseEntity.ok(addresses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressRequestDTO addressRequestDTO) {
        try {
            AddressDTO updatedAddress = addressService.updateAddress(id, addressRequestDTO);
            return ResponseEntity.ok(updatedAddress);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        try {
            addressService.deleteAddress(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}