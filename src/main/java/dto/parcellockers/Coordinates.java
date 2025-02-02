package dto.parcellockers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates {
    private double latitude;
    private double longitude;
}
