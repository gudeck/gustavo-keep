import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { Table } from 'primeng';
import { Page } from '../../shared/model/page';
import { TarefaList } from './model/tarefa-list';
import { TarefaService } from './service/tarefa.service';

@Component({
    selector: 'app-tarefa',
    templateUrl: './tarefa.component.html'
})
export class TarefaComponent implements AfterViewInit {

    @ViewChild('tableTarefas') tabelaTarefas: Table;

    tarefaSelecionada: TarefaList;
    tarefas = new Page<TarefaList>();

    constructor(private tarefaService: TarefaService) {
    }

    findAllTarefas(): void {
        this.tarefaService.findAll<Page<TarefaList>>(this.tabelaTarefas)
            .subscribe(tarefas => this.tarefas = tarefas);
    }

    ngAfterViewInit(): void {
        this.findAllTarefas();
    }

}
