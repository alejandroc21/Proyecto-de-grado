import { Component, OnInit } from "@angular/core";
import { Proyecto } from "src/app/models/proyecto";
import { InsumoService } from "src/app/services/inventario/insumo.service";
import { ProductoService } from "src/app/services/inventario/producto.service";
import { VentaService } from "src/app/services/inventario/venta.service";
import { ProyectoService } from "src/app/services/proyectos/proyecto.service";

@Component({
  selector: "app-sidebar",
  templateUrl: "./sidebar.component.html",
  styleUrls: ["./sidebar.component.css"],
})
export class SidebarComponent implements OnInit {
  proyectos: Proyecto[] = [];
  clickProyecto: number = 0;
  anterior: number = 0;
  selected: Boolean = false;
  constructor(
    private proyectoService: ProyectoService,
    private insumoService: InsumoService,
    private productoService: ProductoService,
    private VentasService:VentaService
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
    this.VentasService.listarVentas().subscribe();
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
  // }
}
