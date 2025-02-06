package steps.api;

import apiclient.ApiClient;
import com.google.gson.Gson;
import dto.parcellockers.ParcelLockerDTO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class ParcelLockersSearchApi {

    private final ApiClient apiClient;

    public ParcelLockersSearchApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Given("User search for parcel lockers in {string}")
    public void userSearchForParcelInCity(String city) {
        apiClient.response = apiClient.sendSearchLockerRequest(city);
    }

    @Then("The parcel lockers data should be saved to {string}")
    public void parcelLockerDataIsSavedToFile(String fileName) {
        try {
            List<ParcelLockerDTO> responseDTO = apiClient.response
                    .then()
                    .extract()
                    .jsonPath()
                    .getList("items", ParcelLockerDTO.class);

            Path directory = Paths.get("target", "lockers");
            Files.createDirectories(directory);
            String filePath = directory.resolve(fileName).toString();

            try (FileWriter file = new FileWriter(filePath)) {
                file.write(new Gson().toJson(responseDTO));
            }

            assertFalse(responseDTO.isEmpty(), "No parcel lockers were found.");
        } catch (IOException e) {
            fail("Failed to save parcel lockers data to file: " + e.getMessage());
        }
    }
}
