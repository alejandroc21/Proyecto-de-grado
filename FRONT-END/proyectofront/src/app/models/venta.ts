import { Proyecto } from "./proyecto";

export interface Venta{
    id:number;
    nombre: string;
    cantidad: number;
    precio:number;
    fecha: Date;
    proyecto: Proyecto;
}