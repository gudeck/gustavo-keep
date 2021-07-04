import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ResponsavelComponent } from './components/responsavel/responsavel.component';
import { TarefaComponent } from './components/tarefa/tarefa.component';

const routes: Routes = [
    { path: 'responsaveis', component: ResponsavelComponent },
    { path: 'tarefas', component: TarefaComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
