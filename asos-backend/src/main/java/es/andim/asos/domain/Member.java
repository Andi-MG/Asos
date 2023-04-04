package es.andim.asos.domain;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Member {

    private long id;
    private String alias;
}
