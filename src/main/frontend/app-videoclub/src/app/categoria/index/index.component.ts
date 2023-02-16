import {Component, OnInit} from '@angular/core';
import { Categoria} from "../categoria";
import {CategoriaService} from "../categoria.service";

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  categorias: Categoria[] = [];

  constructor(public categoriaService:CategoriaService) { }

  ngOnInit(): void {
    this.categoriaService.getAll().subscribe((data: Categoria[])=>{
      this.categorias= data;
      console.log(this.categorias);
    })
  }

  deleteCategoria(id: any){
    this.categoriaService.delete(id).subscribe(res => {
      this.categorias = this.categorias.filter(cat => cat.id !== id);
      console.log('Categoria id =' + id + ' eliminada satisfactoriamente!');
    })
  }


}

