import { Proyecto } from "./proyecto";

export interface Tarea{
    id:number;
    tarea:string;
    realizado:boolean;
    proyecto:Proyecto;
}