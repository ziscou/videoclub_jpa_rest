import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { IndexComponent } from './index/index.component';
import { CreateComponent } from './create/create.component';
import { EditComponent } from './edit/edit.component';

const routes: Routes = [
  { path: 'categoria', redirectTo: 'categoria/index', pathMatch: 'full'},
  { path: 'categoria/index', component: IndexComponent },
  { path: 'categoria/create', component: CreateComponent },
  { path: 'categoria/edit/:idCategoria', component: EditComponent }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CategoriaRoutingModule { }
