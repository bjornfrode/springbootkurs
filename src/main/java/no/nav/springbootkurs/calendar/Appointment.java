package no.nav.springbootkurs.calendar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "APPOINTMENT")
public class Appointment {

    private static final String ID_COLUMN = "APPOINTMENT_ID";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_" + ID_COLUMN)
    @SequenceGenerator(name = "SEQ_" + ID_COLUMN, sequenceName = "SEQ_" + ID_COLUMN, allocationSize = 1)
    @Column(name = ID_COLUMN, nullable = false, updatable = false)
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "TIME")
    private LocalDateTime time;

    @Column(name = "PLACE")
    private String place;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private AppointmentStatus status;

}