import { Proyecto } from "./proyecto";

export interface Cliente{
    id:number;
    documento:string;
    nombre:string;
    proyecto:Proyecto;
}