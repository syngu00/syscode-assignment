package hu.syngu00.syscodeassignment;

import hu.syngu00.syscodeassignment.model.dto.Profile;
import hu.syngu00.syscodeassignment.model.dto.ProfileModifyOrCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ProfileControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testProfileCRUDOperations() {

        // List initially empty
        List<Profile> profilesBeforeSaved = webTestClient.get().uri("/api/profile")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Profile.class).returnResult().getResponseBody();


        assertThat(profilesBeforeSaved, hasSize(0));

        // Create a profile
        ProfileModifyOrCreateRequest profileToCreate = new ProfileModifyOrCreateRequest("John Doe", "john.doe@example.com");
        Profile createdProfile = webTestClient.post().uri("/api/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(profileToCreate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Profile.class).returnResult().getResponseBody();

        assertThat(createdProfile, allOf(
                hasProperty("id", notNullValue()),
                hasProperty("name", is("John Doe")),
                hasProperty("email", is("john.doe@example.com"))
        ));

        // List should now contain 1 profile
        List<Profile> profilesAfterSaved = webTestClient.get().uri("/api/profile")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Profile.class).returnResult().getResponseBody();

        assertThat(profilesAfterSaved, contains(allOf(
                        hasProperty("id", is(createdProfile.getId())),
                        hasProperty("name", is("John Doe")),
                        hasProperty("email", is("john.doe@example.com"))
                )
        ));

        // Retrieve the created profile
        Profile retrievedProfile = webTestClient.get().uri("/api/profile/{id}", createdProfile.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Profile.class).returnResult().getResponseBody();


        assertThat(retrievedProfile, allOf(
                hasProperty("id", is(createdProfile.getId())),
                hasProperty("name", is("John Doe")),
                hasProperty("email", is("john.doe@example.com"))
        ));


        // Update the profile

        ProfileModifyOrCreateRequest updateRequest = new ProfileModifyOrCreateRequest("Jane Doe", "john.doe@example.com");

        Profile updatedProfile = webTestClient.put().uri("/api/profile/{id}", createdProfile.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updateRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Profile.class)
                .returnResult().getResponseBody();

        assertThat(updatedProfile, allOf(
                hasProperty("id", is(createdProfile.getId())),
                hasProperty("name", is("Jane Doe")),
                hasProperty("email", is("john.doe@example.com"))
        ));

        // Verify update
        Profile retrievedProfileAfterUpdate =  webTestClient.get().uri("/api/profile/{id}", createdProfile.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Profile.class)
                .returnResult().getResponseBody();

        assertThat(retrievedProfileAfterUpdate, allOf(
                hasProperty("id", is(createdProfile.getId())),
                hasProperty("name", is("Jane Doe")),
                hasProperty("email", is("john.doe@example.com"))
        ));

        // Delete the profile
        webTestClient.delete().uri("/api/profile/{id}", createdProfile.getId())
                .exchange()
                .expectStatus().isOk();

        // List should be empty again
        List<Profile> profilesAfterDelete = webTestClient.get().uri("/api/profile")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Profile.class).returnResult().getResponseBody();

        assertThat(profilesAfterDelete, hasSize(0));

        webTestClient.get().uri("/api/profile/{id}", createdProfile.getId())
                .exchange()
                .expectStatus().isNotFound();
    }
}