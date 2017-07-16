import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import 'rxjs/Rx';

import { Restaurante } from './restaurante';

@Injectable()
export class RestauranteService {
    
    constructor(private http: Http) { }

    private headers = new Headers({ 'Content-Type': 'application/json' });  
    //GET: busca os restaurantes
    private apiUrl = 'http://localhost:9901/restaurants'; 

    //Metodo GET all
    get(): Promise<Restaurante[]> {
        // var r = this.http.get(this.apiUrl)
        //     .toPromise()
        //     //valida a consulta
        //     .then(response => response.json() as Restaurante[])
        //     .catch(this.handleError);
        // return r;
        return this.http.get(this.apiUrl)
            .toPromise()
            .then(response => response.json() as Restaurante[])
            //.catch(this.handleError);
    }

    //busca pelo nome
    getByName(name: string): Promise<Restaurante> {
        const url = `${this.apiUrl}/find-by-name/${name}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response.json() as Restaurante)
        
            //.catch(this.handleError);
    }

//busca pelo id
    getById(id: number): Promise<Restaurante> {
        const url = `${this.apiUrl}/${id}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response.json() as Restaurante)
        
            //.catch(this.handleError);
    }

    //Metodo POST
    post(restaurante: Restaurante): Promise<Restaurante> {
        return this.http
            .post(this.apiUrl, JSON.stringify(restaurante), { headers: this.headers })
            .toPromise()
            //valida resposta
            .then(res => res.json() as Restaurante)
            //.catch(this.handleError);
    }
    
     //Metodo POST
    update(restaurante: Restaurante): Promise<Restaurante> {
        const url = `${this.apiUrl}/${restaurante.id}`;
        return this.http
            .put(url, JSON.stringify(restaurante), { headers: this.headers })
            .toPromise()
            //valida resposta
            .then(res => res.json() as Restaurante)
            //.catch(this.handleError);
    }

    delete(id){
        return this.http.delete(this.getUserUrl(id), { headers: this.headers })
            .toPromise()
            //valida resposta
            .then(res => console.log(res))
    }

    private getUserUrl(id){
        return this.apiUrl + "/" + id;
    }
    //trata o erro    
    // private handleError(error: any): Promise<any> {
    //     console.log(error);
    //     //console.error('An error occurred', error);
    //     return Promise.reject(error.message || error);
    // }

}