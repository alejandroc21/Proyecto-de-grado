import { Component, OnInit } from "@angular/core";
import { Proyecto } from "src/app/models/proyecto";
import { GestionService } from "src/app/services/gestion/gestion.service";
import { ClienteService } from "src/app/services/inventario/cliente.service";
import { InsumoService } from "src/app/services/inventario/insumo.service";
import { ProductoService } from "src/app/services/inventario/producto.service";
import { VentaService } from "src/app/services/inventario/venta.service";
import { ProyectoService } from "src/app/services/proyectos/proyecto.service";
import { TareasService } from "src/app/services/tareas/tareas.service";

@Component({
  selector: "app-sidebar",
  templateUrl: "./sidebar.component.html",
  styleUrls: ["./sidebar.component.css"],
})
export class SidebarComponent implements OnInit {
  proyectos: Proyecto[] = [];
  vacio:Proyecto = {id:0,nombre:'',descripcion:'',usuario:null, categoria:null};
  marcador=0;
  // clickProyecto: number = 0;
  // anterior: number = 0;
  // selected: Boolean = false;
  constructor(
    private proyectoService: ProyectoService,
    private insumoService: InsumoService,
    private productoService: ProductoService,
    private ventasService:VentaService,
    private gestionService:GestionService,
    private clienteService: ClienteService,
    private tareasService: TareasService
  ) {}
  ngOnInit(): void {
    this.proyectoService.proyectosObs.subscribe({
      next: (proyectosdata) => {
        this.proyectos = proyectosdata;
      },
    });
    this.proyectoService
      .listarProyectos()
      .subscribe((data) => (this.proyectos = data));
  }

   elegido(proyecto: Proyecto) {
    this.proyectoService.elegido(proyecto);
    this.insumoService.listarInsumos().subscribe();
    this.productoService.listarProductos().subscribe();
    this.clienteService.listarClientes().subscribe();
    this.ventasService.listarVentas().subscribe();
    this.gestionService.obtenerDatos();
    this.tareasService.listarTareas().subscribe();
    this.marcado(proyecto.id);
  }

  total(){
    this.proyectoService.elegido(this.vacio);
    this.insumoService.listarInsumos().subscribe();
    this.productoService.listarProductos().subscribe();
    this.ventasService.listarVentas().subscribe();
    this.tareasService.listarTareas().subscribe();
    this.gestionService.obtenerDatos();
  }

  marcado(idProyecto:number){
    this.marcador=idProyecto;
  }

  // elegido(id: number) {

  //   if(this.clickProyecto!==id&&!this.selected){
  //     this.selected=true;
  //     this.clickProyecto=id;
  //     let idNotNull = document.getElementById(this.clickProyecto.toString())!;
  //     idNotNull.style.backgroundColor="green";
  //     console.log(id+"A:"+this.anterior);
  //     // if(id==this.anterior){
  //     //   idNotNull = document.getElementById(id.toString())!;
  //     //   idNotNull.style.backgroundColor="green";
  //     // }
  //     this.anterior=this.clickProyecto;
  //   }else{
  //     this.selected=false;

  //     let idNotNull = document.getElementById(this.clickProyecto.toString())!;
  //     idNotNull.style.backgroundColor="green";
  //     idNotNull = document.getElementById(this.anterior.toString())!;
  //     idNotNull.style.backgroundColor="white";
  //     // if(id!==this.anterior){
  //     //   idNotNull = document.getElementById(id.toString())!;
  //     //   idNotNull.style.backgroundColor="green";
  //     // }

  //     console.log(id+"B:"+this.anterior);
  //     this.clickProyecto=0;
  //   }
  //}
}
