package no.nav.springbootkurs.calendar;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointment")
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentRepository repository;

    @GetMapping
    public List<Appointment> getAllAppointments  () { return repository.findAll(); }

    @GetMapping("/{id}")
    public Optional<Appointment> getAppointmentById (@PathVariable Long id) {return repository.findById(id);}

    @PostMapping
    public Appointment createNewAppointment (@RequestBody Appointment appointment) {return repository.save(appointment);}

    @GetMapping("/test")
    public Appointment createTestAppointment () {return repository.save(Appointment.builder()
            .id(1L)
            .title("Test appointment")
            .place("Grunerløkka")
            .time(LocalDateTime.now())
            .status(AppointmentStatus.SUBMITTED)
            .build()
    ); }

    @DeleteMapping("/{id}")
    public Long deleteAppointment (@PathVariable Long id) {
        repository.deleteById(id);
        return id;
    }

}