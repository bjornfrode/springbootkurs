package no.nav.springbootkurs.contact.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ContactResponse {
    private final Long id;
    private final String firstName;
    private final String lastName;
}
