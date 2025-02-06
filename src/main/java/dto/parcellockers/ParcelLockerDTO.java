package dto.parcellockers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParcelLockerDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("location")
    private Coordinates coordinates;

    @JsonProperty("address_details")
    private AddressDetailsDTO postalCode;

}

