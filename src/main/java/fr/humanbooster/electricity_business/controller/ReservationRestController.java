package fr.humanbooster.electricity_business.controller;

import fr.humanbooster.electricity_business.dto.ReservationDTO;
import fr.humanbooster.electricity_business.dto.ReservationRequestDTO;
import fr.humanbooster.electricity_business.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationRestController {

    private final ReservationService reservationService;

    public ReservationRestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@Valid @RequestBody ReservationRequestDTO reservationRequestDTO) {
        ReservationDTO createdReservation = reservationService.createReservation(reservationRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        ReservationDTO reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<ReservationDTO> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable Long id, @Valid @RequestBody ReservationRequestDTO reservationRequestDTO) {
        ReservationDTO updatedReservation = reservationService.updateReservation(id, reservationRequestDTO);
        return ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/accept") // PATCH pour une mise à jour partielle du statut
    public ResponseEntity<ReservationDTO> acceptReservation(@PathVariable Long id) {
        ReservationDTO acceptedReservation = reservationService.acceptReservation(id);
        return ResponseEntity.ok(acceptedReservation);
    }

    @PatchMapping("/{id}/reject") // PATCH pour une mise à jour partielle du statut
    public ResponseEntity<ReservationDTO> rejectReservation(@PathVariable Long id) {
        ReservationDTO rejectedReservation = reservationService.rejectReservation(id);
        return ResponseEntity.ok(rejectedReservation);
    }

    @GetMapping("/user/{userId}/current")
    public ResponseEntity<List<ReservationDTO>> getCurrentUserReservations(@PathVariable Long userId) {
        List<ReservationDTO> currentReservations = reservationService.getCurrentReservations(userId);
        return ResponseEntity.ok(currentReservations);
    }

    @GetMapping("/user/{userId}/past")
    public ResponseEntity<List<ReservationDTO>> getPastUserReservations(@PathVariable Long userId) {
        List<ReservationDTO> pastReservations = reservationService.getPastReservations(userId);
        return ResponseEntity.ok(pastReservations);
    }

}