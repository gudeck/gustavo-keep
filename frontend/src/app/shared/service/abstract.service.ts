import { HttpClient } from '@angular/common/http';
import { Table } from 'primeng';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { RequestUtil } from '../util/request-util';

export abstract class AbstractService {

    baseUrl: string;
    http: HttpClient;

    protected constructor(http: HttpClient, resource: string) {
        this.http = http;
        this.baseUrl = `${ environment.apiUrl }/${ resource }`;
    }

    create<T>(data): Observable<T> {
        return this.http.post<T>(`${ this.baseUrl }`, data);
    }

    delete(id: number | string): Observable<void> {
        return this.http.delete<void>(`${ this.baseUrl }/${ id }`);
    }

    findAll<T>(tabelaTarefas: Table): Observable<T> {
        return this.http.get<T>(`${ this.baseUrl }/`, { params: RequestUtil.getRequestParams(tabelaTarefas) });
    }

    findById<T>(id: number | string): Observable<T> {
        return this.http.get<T>(`${ this.baseUrl }/${ id }`);
    }

    update<T>(data): Observable<T> {
        return this.http.put<T>(`${ this.baseUrl }`, data);
    }

}
