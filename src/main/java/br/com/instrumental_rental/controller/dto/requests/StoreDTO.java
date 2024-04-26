package br.com.instrumental_rental.controller.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {

    @NotBlank(message = "a deposit must be made")
    @Pattern(regexp = "^[0-9]+$", message = "value must be numerical digits")
    @Positive(message = "value must be positive")
    private Long storeId;

    @NotBlank(message = "field must not be left blank")
    @Pattern(regexp = "^[0-9]+$", message = "value must be in numerical digits")
    @Positive(message = "value must be positive")
    private List<Long> customerIdList;

    @NotBlank(message = "field must not be left blank")
    @Pattern(regexp = "^[0-9]+$", message = "value must be in numerical digits")
    @Positive(message = "value must be positive")
    private List<Long> attendantIdList;

    @NotBlank(message = "field must not be left blank")
    @Pattern(regexp = "^[0-9]+$", message = "value must be in numerical digits")
    @Positive(message = "value must be positive")
    private List<Long> instrumentIdList;

    @NotBlank(message = "field must not be left blank")
    @Pattern(regexp = "^[0-9]+$", message = "value must be in numerical digits")
    @Positive(message = "value must be positive")
    private Long addressId;
}
