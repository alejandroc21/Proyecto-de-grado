import { Producto } from "./producto";

export interface Venta{
    id:number;
    nombre: string;
    cantidad: number;
    precio:number;
    fecha: Date;
    producto:Producto;
}