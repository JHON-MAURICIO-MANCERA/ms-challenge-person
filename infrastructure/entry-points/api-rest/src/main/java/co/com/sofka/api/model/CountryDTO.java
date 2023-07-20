package co.com.sofka.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class CountryDTO {
    private String id;
    private String name;
}
