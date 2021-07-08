import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
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

    findAllResponsaveis(): void {
        this.responsavelService.findAll<Page<ResponsavelList>>(this.tableResponsaveis)
            .subscribe((responsaveis) => this.responsaveis = responsaveis);
    }

    ngAfterViewInit(): void {
        this.findAllResponsaveis();
    }

    search(formFilter: NgForm): void {
        this.responsavelService.filter<Page<ResponsavelList>>(formFilter.value, this.tableResponsaveis)
            .subscribe((responsaveis) => this.responsaveis = responsaveis);
    }
}
