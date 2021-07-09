package br.com.basis.colatina.gcz.keep.service.event;

import br.com.basis.colatina.gcz.keep.service.event.common.BaseEvent;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
public class TarefaEvent extends BaseEvent {

    public TarefaEvent(Long id) {
        super(id);
    }

    public TarefaEvent(Set<Long> ids) {
        super(ids);
    }
}
