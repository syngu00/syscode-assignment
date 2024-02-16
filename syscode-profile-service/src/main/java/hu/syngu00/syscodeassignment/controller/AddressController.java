package hu.syngu00.syscodeassignment.controller;

import hu.syngu00.syscodeassignment.model.dto.Address;
import hu.syngu00.syscodeassignment.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/address")
public class AddressController {


    private final AddressService addressService;

    @GetMapping
    @Operation(summary = "Get an Address", description = "Retrieves a single address from the AddressService",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful retrieval of an address", content = @Content(schema = @Schema(implementation = Address.class))),
            })
    public Address getAnAddress() {
        return addressService.getAnAddress();
    }

}
