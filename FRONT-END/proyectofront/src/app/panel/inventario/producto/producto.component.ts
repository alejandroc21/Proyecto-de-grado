import { Component, OnInit } from "@angular/core";
import { FormBuilder, Validators } from "@angular/forms";
import { Cliente } from "src/app/models/cliente";
import { Producto } from "src/app/models/producto";
import { Proyecto } from "src/app/models/proyecto";
import { Venta } from "src/app/models/venta";
import { ClienteService } from "src/app/services/inventario/cliente.service";
import { ProductoService } from "src/app/services/inventario/producto.service";
import { ProyectoService } from "src/app/services/proyectos/proyecto.service";

@Component({
  selector: "app-producto",
  templateUrl: "./producto.component.html",
  styleUrls: ["./producto.component.css"],
})
export class ProductoComponent implements OnInit {
  productos: Producto[] = [];
  proyecto: Proyecto = { id: 0, nombre: "", descripcion: "", usuario: null , categoria:null};
  producto: Producto = {
    id: 0,
    nombre: "",
    descripcion: "",
    cantidadInicial: 0,
    cantidadFinal: 0,
    precio: 0,
    fecha: new Date(""),
    proyecto: this.proyecto,
  };
  productoCart: Producto = {
    id: 0,
    nombre: "",
    descripcion: "",
    cantidadInicial: 0,
    cantidadFinal: 0,
    precio: 0,
    fecha: new Date(""),
    proyecto: this.proyecto,
  };
  selectedProducto: Producto = {
    id: 0,
    nombre: "",
    descripcion: "",
    cantidadInicial: 0,
    cantidadFinal: 0,
    precio: 0,
    fecha: new Date(""),
    proyecto: this.proyecto,
  };
  editar: Boolean = false;
  carrito: Producto[] = [];
  cart:Venta[]=[];
  idProyecto:number=0;
  clientes:Cliente[]=[];
  cliente:Cliente={id:0, documento:'', nombre:'', proyecto: this.proyecto};
  ventaSelected:Venta= {
    id: 0,
    nombre: "",
    precio: 0,
    cantidad: 0,
    data:0,
    fecha: new Date(),
    proyecto: this.proyecto,
    factura:{
      id: 0,
      cliente: this.cliente
    }
  };
  

  mostrarCart=false;
  nuevoCliente=false;

  ventaform= this.formBuilder.group({
    client: ["", Validators.required]
  });

  clienteform= this.formBuilder.group({
    nombre:["", Validators.required],
    documento: ["", Validators.required]
  })

  productoForm = this.formBuilder.group({
    nombre: ["", Validators.required],
    descripcion: [""],
    cantidadFinal: ["", Validators.required],
    precio: ["", Validators.required],
    fecha: ["", Validators.required],
  });

  constructor(
    private productoService: ProductoService,
    private formBuilder: FormBuilder,
    private proyectoService: ProyectoService,
    private clienteService: ClienteService
  ) {}

  ngOnInit(): void {
    this.productoService.productos.subscribe((data) => (this.productos = data));
    this.listarProductos();
  }

  btnNuevoCliente(){
    this.nuevoCliente=!this.nuevoCliente;
    this.proyectoService.selected.subscribe({
      next: (data) => {
        this.proyecto = data;
      }
    });
    // if(!this.nuevoCliente){
    //   this.nuevoCliente=true;
    // }else{
    //   this.nuevoCliente=false;
    // }
  }

  EditarVenta(venta:Venta){
    if(this.ventaSelected==venta){
      this.cart=this.cart.map(objeto=>(objeto.nombre===this.ventaSelected.nombre) ? this.ventaSelected: objeto);

      this.ventaSelected= {
        id: 0,
        nombre: "",
        precio: 0,
        cantidad: 0,
        fecha: new Date(),
        data:0,
        proyecto: this.proyecto,
        factura:{
          id: 0,
          cliente: this.cliente
        }
      };

    }else{
      this.ventaSelected=venta;
      console.log(this.cart);
    }
    
  }

  listarClientes(){
    this.clienteService.listarClientes().subscribe((data)=>(this.clientes=data.body));
  }

  ventaMultiple(){
    if(this.ventaform.valid&&this.cart.length>0){
      //let categoriaAdd= this.categorias.find((categoria)=>categoria.id==data);
      

      


      let clienteAdd = this.clientes.find((cliente)=>cliente.id== Number(this.ventaform.value.client) );
      this.cart.forEach(venta=>{
        venta.factura.cliente = clienteAdd;
      });

      this.cart.forEach(venta =>{
        let productoVendido = this.productos.find(producto=>producto.nombre==venta.nombre);
        if(productoVendido){
          productoVendido.cantidadFinal-=venta.cantidad;
        }
        
      });

      
      
      this.productoService.ventaMultiple(this.cart).subscribe((res)=>{ 
        const blob = new Blob([res], { type: 'application/pdf' });
        const url = window.URL.createObjectURL(blob);

        // Crear un enlace <a> en el DOM y hacer clic en él para iniciar la descarga
        const a = document.createElement('a');
        a.href = url;
        a.download = 'ventas.pdf';
        document.body.appendChild(a);
        a.click();

        // Liberar el objeto URL y eliminar el enlace <a>
        window.URL.revokeObjectURL(url);
        document.body.removeChild(a);
    


        this.ventaform.reset();
        this.cart=[];
        this.carrito=[];
        this.productoService.actualizarProductoMultiple(this.productos).subscribe();  
      });
      

    }
    
  }

  

  btnCart(){
    if(!this.mostrarCart){
      this.listarClientes();
      this.mostrarCart=true
    }else{
      this.mostrarCart=false;
    }
  }

  listarProductos() {
    this.productoService
      .listarProductos()
      .subscribe((data) => (this.productos = data));
  }

  botonProducto() {
    if (this.selectedProducto.id === 0) {
      this.crearProducto();
    } else {
      this.actualizarProducto();
    }
  }

  aggToCart(producto: Producto) {
    this.listarClientes();
    this.proyectoService.selected.subscribe({
      next: (data) => {
        this.proyecto = data;
      }
    });
    if(this.idProyecto!==this.proyecto.id){
      this.carrito=[];
      this.cart=[];
    }
    if (this.proyecto.id === 0) {
      alert("elige un proyecto primero");
    } else {
      if (this.carrito.includes(producto)) {
        
        console.log("ya esta");
        let indice =this.carrito.indexOf(producto);
        if(indice!==-1){
          this.carrito.splice(indice, 1);
          this.cart.splice(indice, 1);
        }
      } else {
        this.idProyecto=this.proyecto.id;
        let venta: Venta = {
          id: 0,
          nombre: "",
          precio: 0,
          cantidad: 0,
          fecha: new Date(),
          data:0,
          proyecto: this.proyecto,
          factura:{
            id: 0,
            cliente: this.cliente
          }
        };
        venta.nombre = producto.nombre;
        venta.cantidad = 1;
        venta.data=producto.cantidadFinal;
        venta.precio = producto.precio;

        // const date = new Date();
        // const year = date.getFullYear();
        // const month = (date.getMonth() + 1).toString().padStart(2, '0');
        // const day = date.getDate().toString().padStart(2, '0');
        // const aaaammdd = `${year}-${month}-${day}`;

        // venta.fecha = new Date(aaaammdd);

        const fechaActual: Date = new Date();

        // Obtener los componentes de la fecha en UTC
        const anio: number = fechaActual.getUTCFullYear();
        const mes: number = fechaActual.getUTCMonth() + 1; // Los meses son indexados desde 0
        const dia: number = fechaActual.getUTCDate()+1;

        // Asegurarse de que los componentes tengan dos dígitos
        const anioStr: string = anio.toString().padStart(4, '0');
        const mesStr: string = mes.toString().padStart(2, '0');
        const diaStr: string = dia.toString().padStart(2, '0');

        // Formatear la fecha en el formato deseado "aaammdd"
        const fechaFormateada: string = `${anioStr}-${mesStr}-${diaStr}`;

        venta.fecha = new Date(fechaFormateada);
        venta.proyecto = this.proyecto;
        venta.factura = {id:0, cliente:this.cliente}

        
        this.carrito.push(producto);
        this.cart.push(venta);
        //console.log(venta); 
        
      }
    }
  }

  crearCliente(){
    if(this.proyecto.id==0){
      alert("elige un proyecto");
      return;
    }
    if(this.clienteform.valid){
      this.cliente.nombre=this.clienteform.value.nombre;
      this.cliente.documento=this.clienteform.value.documento;
      this.cliente.proyecto=this.proyecto;
      this.clienteService.crearCliente(this.cliente).subscribe((res)=>{
        this.clienteform.reset();
        this.listarClientes();
      });
    }
  }


  crearProducto() {
    if (this.productoForm.valid) {
      this.producto.nombre = this.productoForm.value.nombre!;
      this.producto.descripcion = this.productoForm.value.descripcion!;
      this.producto.cantidadInicial = parseInt(
        this.productoForm.value.cantidadFinal!
      );
      this.producto.cantidadFinal = parseInt(
        this.productoForm.value.cantidadFinal!
      );
      this.producto.precio = parseFloat(this.productoForm.value.precio!);
      //this.producto.fecha=new Date(this.productoForm.value.fecha!);

      let formFecha = this.productoForm.value.fecha;
      let reparar = new Date(formFecha);
      this.producto.fecha = new Date(
        reparar.getTime() + reparar.getTimezoneOffset() * 60000
      );

      this.productoService.crearProducto(this.producto).subscribe((res) => {
        this.productoForm.reset();
        this.listarProductos();
      });
    } else {
      this.productoForm.markAllAsTouched();
    }
  }

  actualizarProducto() {
    if (this.productoForm.valid) {
      this.selectedProducto.nombre = this.productoForm.value.nombre!;
      this.selectedProducto.descripcion = this.productoForm.value.descripcion!;
      this.selectedProducto.precio = parseFloat(
        this.productoForm.value.precio!
      );
      this.selectedProducto.cantidadInicial = parseInt(
        this.productoForm.value.cantidadFinal!
      );
      this.selectedProducto.cantidadFinal = parseInt(
        this.productoForm.value.cantidadFinal!
      );
      //this.selectedProducto.fecha=new Date(this.productoForm.value.fecha!);

      let formFecha = this.productoForm.value.fecha;
      let reparar = new Date(formFecha);
      this.producto.fecha = new Date(
        reparar.getTime() + reparar.getTimezoneOffset() * 60000
      );

      this.productoService
        .actualizarProducto(this.selectedProducto)
        .subscribe((res) => {
          this.productoForm.reset();
          this.listarProductos();
          this.selectedProducto = {
            id: 0,
            nombre: "",
            descripcion: "",
            cantidadInicial: 0,
            cantidadFinal: 0,
            precio: 0,
            fecha: new Date(""),
            proyecto: this.proyecto,
          };
        });
    } else {
      this.productoForm.markAllAsTouched();
    }
  }

  selectEdithProducto(producto: Producto) {
    if (producto !== this.selectedProducto || !this.editar) {
      this.editar = true;
      this.selectedProducto = producto;
      this.productoForm.setValue({
        nombre: this.selectedProducto.nombre,
        descripcion: this.selectedProducto.descripcion,
        precio: this.selectedProducto.precio.toString(),
        cantidadFinal: this.selectedProducto.cantidadFinal.toString(),
        fecha: this.selectedProducto.fecha.toString(),
      });
    } else {
      this.editar = false;
      this.selectedProducto = {
        id: 0,
        nombre: "",
        descripcion: "",
        cantidadInicial: 0,
        cantidadFinal: 0,
        precio: 0,
        fecha: new Date(""),
        proyecto: this.proyecto,
      };
      this.productoForm.setValue({
        nombre: "",
        descripcion: "",
        precio: "",
        cantidadFinal: "",
        fecha: "",
      });
    }
  }

  eliminarProducto(idProducto: number) {
    this.productoService.eliminarProducto(idProducto).subscribe((res) => {
      this.listarProductos();
    });
  }
}
