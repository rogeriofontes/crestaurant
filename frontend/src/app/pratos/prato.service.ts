import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import 'rxjs/Rx';

import { Prato } from './prato';

@Injectable()
export class PratoService {

  constructor(private http: Http) { }

  private headers = new Headers({ 'Content-Type': 'application/json' });  
    //GET: busca os restaurantes
  private apiUrl = 'http://localhost:9901/plates'; 

      //Metodo GET all
    get(): Promise<Prato[]> {
        // var r = this.http.get(this.apiUrl)
        //     .toPromise()
        //     //valida a consulta
        //     .then(response => response.json() as Restaurante[])
        //     .catch(this.handleError);
        // return r;
        return this.http.get(this.apiUrl)
            .toPromise()
            .then(response => response.json() as Prato[])
            //.catch(this.handleError);
    }

 //busca pelo nome
    getByName(name: string): Promise<Prato> {
        const url = `${this.apiUrl}/find-by-name/${name}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response.json() as Prato)
        
            //.catch(this.handleError);
    }

    //busca pelo id
    getById(id: number): Promise<Prato> {
        const url = `${this.apiUrl}/${id}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response.json() as Prato)
        
            //.catch(this.handleError);
    }

    //Metodo POST
    post(prato: Prato): Promise<Prato> {
        return this.http
            .post(this.apiUrl, JSON.stringify(prato), { headers: this.headers })
            .toPromise()
            //valida resposta
            .then(res => res.json() as Prato)
            //.catch(this.handleError);
    }
      
     //Metodo PUT
    update(prato: Prato): Promise<Prato> {
        const url = `${this.apiUrl}/${prato.id}`;
        return this.http
            .put(url, JSON.stringify(prato), { headers: this.headers })
            .toPromise()
            //valida resposta
            .then(res => res.json() as Prato)
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
