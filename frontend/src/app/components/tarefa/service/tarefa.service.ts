import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AbstractService } from '../../../shared/service/abstract.service';

@Injectable({
    providedIn: 'root'
})
export class TarefaService extends AbstractService {

    constructor(httpClient: HttpClient) {
        super(httpClient, 'tarefa');
    }

}
