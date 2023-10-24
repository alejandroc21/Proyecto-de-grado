import { Proyecto } from "./proyecto";

export interface Insumo{
    id:number;
    nombre: string;
    cantidad: number;
    precio:number;
    fecha: Date;
    proyecto:Proyecto;
}