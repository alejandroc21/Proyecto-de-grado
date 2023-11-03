import { Component, OnInit } from "@angular/core";
import { Chart, ChartType } from "chart.js/auto";
import { GestionService } from "src/app/services/gestion/gestion.service";

@Component({
  selector: "app-gestion",
  templateUrl: "./gestion.component.html",
  styleUrls: ["./gestion.component.css"],
})
export class GestionComponent implements OnInit {
  public chart: Chart;
  precio = [];
  fecha = [];

  precioVenta = [];
  fechaVenta = [];

  precioProducto = [];
  fechaProducto = [];

  precioInsumo = [];
  fechaInsumo = [];

  ventaPrecioFecha = new Map();
  ventaSort:any[] = [];

  productoPrecioFecha = new Map();
  productoSort:any[] = [];

  insumoPrecioFecha = new Map();
  insumoSort:any[] = [];

  totalVenta=0;
  totalProducto=0;
  totalInsumo=0;

  ganancias:number[]=[];

  creado=false;

  constructor(private gestionService:GestionService){}

  ngOnInit(): void {
    this.gestionService.obtenerDatos();
    // this.getVentaData();

    this.gestionService.ventaMap.subscribe({
      next:(mapData)=>{
        this.ventaPrecioFecha=mapData;
        this.ventaSort=Array.from(this.ventaPrecioFecha.values());
        if(this.creado){
          this.chart.destroy();
           setTimeout(() => {
          this.grafica();
          console.log("awebo");
        }, 300);
        }
        
        this.ventaSort.sort((a,b)=>{
          const dateA = new Date(a.fecha);
          const dateB = new Date(b.fecha);
          if (dateA < dateB) {
            return -1;
        }
        if (dateA > dateB) {
            return 1;
        }
        return 0;
        });
        this.fechaVenta=[];
        this.precioVenta=[];
      this.ventaSort.forEach(venta=>{
          this.fechaVenta.push(venta.fecha);
          this.precioVenta.push(venta.precio);
        });
      }
    });

    
    this.gestionService.productoMap.subscribe({
      next:(mapData)=>{
        this.productoPrecioFecha=mapData;
        this.productoSort=Array.from(this.productoPrecioFecha.values());
        this.productoSort.sort((a,b)=>{
          const dateA = new Date(a.fecha);
          const dateB = new Date(b.fecha);
          if (dateA < dateB) {
            return -1;
        }
        if (dateA > dateB) {
            return 1;
        }
        return 0;
        });
        this.fechaProducto=[];
        this.precioProducto=[];
        this.productoSort.forEach(producto=>{
          this.fechaProducto.push(producto.fecha);
          this.precioProducto.push(producto.precio);
        });
      }
    });


    this.gestionService.insumoMap.subscribe({
      next: (mapData)=>{
        this.insumoPrecioFecha=mapData;
        this.insumoSort=Array.from(this.insumoPrecioFecha.values());
        this.insumoSort.sort((a,b)=>{
          const dateA = new Date(a.fecha);
          const dateB = new Date(b.fecha);
          if (dateA < dateB) {
            return -1;
        }
        if (dateA > dateB) {
            return 1;
        }
        return 0;
        });
        this.fechaInsumo=[];
        this.precioInsumo=[];
        this.insumoSort.forEach(insumo=>{
          this.fechaInsumo.push(insumo.fecha);
          this.precioInsumo.push(insumo.precio);
        });
      }
    });

    
    // this.gestionService.precioVenta.subscribe({
    //   next: (precioData)=>{
    //     this.precio=precioData;
    //   }
    // });

    // this.gestionService.fechaVenta.subscribe({
    //   next: (fechaData)=>{
    //     this.fecha=fechaData;
    //   }
    // });
    this.totalVenta=0;
    this.totalProducto=0;
    this.totalInsumo=0;
    setTimeout(() => {
      this.grafica();
      this.totalVenta=this.precioVenta.reduce((total, numero)=>total+numero,0);
      this.totalProducto=this.precioProducto.reduce((total, numero)=>total+numero,0);
      this.totalInsumo=this.precioInsumo.reduce((total, numero)=>total+numero,0);
      console.log("siii");
      this.creado=true;
    }, 300);
    
  }

  // getVentaData(){
  //   this.ventaPrecioFecha=  this.gestionService.datosVenta()
  // }

  sumarArrays(array1: number[], array2: number[]): number[] {
    const longitudMaxima = Math.max(array1.length, array2.length);
    
    // Rellenar los arrays para igualar su longitud
    while (array1.length < longitudMaxima) {
        array1.push(0);
    }
    while (array2.length < longitudMaxima) {
        array2.push(0);
    }

    // Sumar los elementos de ambos arrays
    const arrayResultado = array1.map((valor, indice) => valor + array2[indice]);
    
    return arrayResultado;
}

calcularGanancias(gastos: number[], ventas: number[]): number[] {
  const longitudMaxima = Math.max(gastos.length, ventas.length);
  const gastosCompletos = new Array(longitudMaxima).fill(0);
  const ventasCompletas = new Array(longitudMaxima).fill(0);

  gastos.forEach((gasto, index) => {
      gastosCompletos[index] = gasto;
  });

  ventas.forEach((venta, index) => {
      ventasCompletas[index] = venta;
  });

  const ganancias = gastosCompletos.map((gasto, index) => {
      const venta = ventasCompletas[index];
      return venta - gasto;
  });

  return ganancias;
}


  grafica(){
  //   let data = {
  //     labels: this.fechaVenta,
  //     datasets: [{
  //       label: 'My First Dataset',
  //       data: this.precioVenta,
  //       fill: false,
  //       borderColor: 'rgb(75, 192, 192)',
  //       tension: 0.1
  //     }]
  //   };

  //   // Creamos la gráfica
  //   this.chart = new Chart("chart", {
  //     type: 'line' as ChartType, // tipo de la gráfica
  //     data // datos
  //   });
  // }

  
  this.ganancias = this.calcularGanancias(this.precio, this.precioVenta);
  this.precio=this.sumarArrays(this.precioProducto, this.precioInsumo);

  const DATA_COUNT = 7;
    const NUMBER_CFG = { count: DATA_COUNT, min: -100, max: 100 };
    
    const labels = [
      "January",
      "February",
      "March",
      "April",
      "May",
      "June",
      "July",
    ];
    const data = {
      labels: this.fechaVenta,
      datasets: [
        {
          label: "Activos",
          fill: false,
          backgroundColor: "rgb(75, 192, 19)",
          borderColor: "#50C878",
          data: this.precioVenta,
        },
        {
          label: "Gastos",
          fill: false,
          backgroundColor: "rgb(254, 92, 92)",
          borderColor: "rgb(254, 92, 92)",
          borderDash: [5, 5],
          data: this.precio,
        },
        {
          label: "Capital",
          backgroundColor: "#FFD200",
          borderColor: "#FFD200",
          data: this.ganancias,
          fill: false,
        },
      ],
    };

    this.chart = new Chart("chart", {
      type: "line" as ChartType,
      data: data,
      options: {
        responsive: true,
        plugins: {
          title: {
            display: true,
            text: "Totales",
          },
        },
        interaction: {
          mode: "index",
          intersect: false,
        },
        scales: {
          x: {
            display: true,
            title: {
              display: true,
              text: "Tiempo",
            },
          },
          y: {
            display: true,
            title: {
              display: true,
              text: "Precios",
            },
          },
        },
      },
    });

  }
}
