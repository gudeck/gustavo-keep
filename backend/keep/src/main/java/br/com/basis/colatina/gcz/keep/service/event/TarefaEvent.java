package br.com.basis.colatina.gcz.keep.service.event;

import br.com.basis.colatina.gcz.keep.service.event.common.BaseEvent;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TarefaEvent extends BaseEvent {
    public TarefaEvent(Long id) {
        super(id);
    }
}
