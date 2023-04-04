package es.andim.asos.application;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewMember {

    @NotBlank(message = "Field alias cannot be null or empty.")
    private String alias;
    @NotNull(message = "Field dni cannot be null.")
    private String dni;
}
