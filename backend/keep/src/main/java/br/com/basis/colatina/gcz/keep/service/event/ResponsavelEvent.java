package br.com.basis.colatina.gcz.keep.service.event;

import br.com.basis.colatina.gcz.keep.service.event.common.BaseEvent;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResponsavelEvent extends BaseEvent {
    public ResponsavelEvent(Long id) {
        super(id);
    }
}
