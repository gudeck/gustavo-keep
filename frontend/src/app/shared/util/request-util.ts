import { HttpParams } from '@angular/common/http';
import { Table } from 'primeng';

export class RequestUtil {

    static getRequestParams(table: Table): HttpParams {
        let httpParams: HttpParams = new HttpParams();
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
