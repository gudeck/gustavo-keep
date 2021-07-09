package br.com.basis.colatina.gcz.keep.service.event.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BaseEvent {

    private Long id;

    private Set<Long> ids;

    public BaseEvent(Long id) {
        this.id = id;
    }

    public BaseEvent(Set<Long> ids) {
        this.ids = ids;
    }


}
