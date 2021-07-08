import { HttpParams } from '@angular/common/http';
import { Table } from 'primeng';

export class RequestUtil {

    static concatParams(params: HttpParams[]): HttpParams {
        let httpParams = new HttpParams();
        params.forEach(param => {
            param['updates']
                ?.map(update => ({ param: update['param'], value: update['value'] }))
                .forEach((update: { param: string, value: string }) => httpParams = httpParams.append(update.param, update.value));
        });
        return httpParams;
    }

    static getFilterParams(object: Object): HttpParams {
        let httpParams = new HttpParams();
        Object.keys(object).forEach(key => {
            const value: string = object[key];
            if (value) {
                httpParams = httpParams.append(key, value);
            }
        });
        return httpParams;
    }

    static getTableParams(table: Table): HttpParams {
        let httpParams = new HttpParams();
        if (table == null) {
            return httpParams;
        }
        httpParams = httpParams.append('page', Math.round(table.first / table.rows).toString());
        httpParams = httpParams.append('size', table.rows != null ? table.rows.toString() : null);

        const direction = table.sortOrder === 1 ? 'ASC' : 'DESC';
        httpParams = httpParams.append('sort', table.sortField != null ? table.sortField + ',' + direction : '');
        return httpParams;
    }

}
