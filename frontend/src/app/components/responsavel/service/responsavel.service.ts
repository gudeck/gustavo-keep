import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AbstractService } from '../../../shared/service/abstract.service';

@Injectable({
    providedIn: 'root'
})
export class ResponsavelService extends AbstractService {

    constructor(private httpClient: HttpClient) {
        super(httpClient, 'responsavel');
    }

}
