import { Proyecto } from "./proyecto";

export interface Producto{
    id:number;
    nombre: string;
    descripcion:string;
    cantidadInicial: number;
    cantidadFinal:number;
    precio:number;
    fecha: Date;
    proyecto:Proyecto;
}