package es.andim.asos.domain;

import java.util.UUID;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Member {

    private final UUID id;
    private final String dni;
    private final String alias;
}
