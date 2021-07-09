import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { Table } from 'primeng';
import { Page } from '../../shared/model/page';
import { TarefaFilter } from './model/tarefa-filter';
import { TarefaList } from './model/tarefa-list';
import { TarefaService } from './service/tarefa.service';

@Component({
    selector: 'app-tarefa',
    templateUrl: './tarefa.component.html'
})
export class TarefaComponent implements AfterViewInit {

    @ViewChild('tableTarefas') tabelaTarefas: Table;

    tarefaFilter = new TarefaFilter();
    tarefas = new Page<TarefaList>();

    constructor(private tarefaService: TarefaService) {
    }

    filterTarefas(tarefaFilter = this.tarefaFilter): void {
        this.tabelaTarefas.reset();
        this.findAllTarefas(tarefaFilter);
    }

    findAllTarefas(filter = this.tarefaFilter, table = this.tabelaTarefas): void {
        this.tarefaService.findAll<Page<TarefaList>>(filter, table)
            .subscribe(tarefas => this.tarefas = tarefas);
    }

    ngAfterViewInit(): void {
        this.findAllTarefas();
    }
}
