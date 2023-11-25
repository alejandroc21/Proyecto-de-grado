import { Categoria } from "./categoria";
import { Usuario } from "./usuario";

export class Proyecto{
    id: number= 0;
    nombre: string='';
    descripcion: string='';
    usuario!: Usuario | null;
    categoria!: Categoria | null;
}