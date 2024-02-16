package hu.syngu00.syscodeassignment.service;

import hu.syngu00.syscodeassignment.model.dto.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final WebClient addressWebClient;

    public Address getAnAddress() {
        return addressWebClient.get()
                .uri("/api/address")
                .retrieve()
                .bodyToMono(Address.class)
                .block();
    }


}
