package br.com.basis.colatina.gcz.keep.service.event;

import br.com.basis.colatina.gcz.keep.service.event.common.BaseEvent;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
public class ResponsavelEvent extends BaseEvent {
    public ResponsavelEvent(Long id) {
        super(id);
    }

    public ResponsavelEvent(Set<Long> ids) {
        super(ids);
    }
}
