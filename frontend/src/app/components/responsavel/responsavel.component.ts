import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { Table } from 'primeng';
import { Page } from '../../shared/model/page';
import { ResponsavelFilter } from './model/responsavel-filter';
import { ResponsavelList } from './model/responsavel-list';
import { ResponsavelService } from './service/responsavel.service';

@Component({
    selector: 'app-responsavel',
    templateUrl: './responsavel.component.html',
    styleUrls: ['responsavel.component.scss']
})
export class ResponsavelComponent implements AfterViewInit {

    @ViewChild('tableResponsaveis') tableResponsaveis: Table;

    responsavelFilter = new ResponsavelFilter();
    responsaveis = new Page<ResponsavelList>();

    constructor(private responsavelService: ResponsavelService) {
    }

    filterResponsaveis(responsavelFilter = this.responsavelFilter): void {
        this.tableResponsaveis.reset();
        this.findAllResponsaveis(responsavelFilter);
    }

    findAllResponsaveis(filter = this.responsavelFilter, table = this.tableResponsaveis): void {
        this.responsavelService.findAll<Page<ResponsavelList>>(filter, table)
            .subscribe((responsaveis) => this.responsaveis = responsaveis);
    }

    ngAfterViewInit(): void {
        this.findAllResponsaveis();
    }
}
