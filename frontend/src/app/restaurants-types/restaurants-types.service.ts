import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import 'rxjs/add/operator/toPromise';
import 'rxjs/add/operator/map';
import 'rxjs/Rx';

import { RestaurantType } from './restaurant-type';

@Injectable()
export class RestaurantsTypesService {

  constructor(private http: Http) { }

  private headers = new Headers({ 'Content-Type': 'application/json' });  
    //GET: busca os restaurant-types
    private apiUrl = 'http://localhost:9901/restaurant-types'; 

    //Metodo GET all
    get(): Promise<RestaurantType[]> {
        // var r = this.http.get(this.apiUrl)
        //     .toPromise()
        //     //valida a consulta
        //     .then(response => response.json() as RestaurantType[])
        //     .catch(this.handleError);
        // return r;
        return this.http.get(this.apiUrl)
            .toPromise()
            .then(response => response.json() as RestaurantType[])
            //.catch(this.handleError);
    }

    //busca pelo nome
    getByName(name: string): Promise<RestaurantType> {
        const url = `${this.apiUrl}/find-by-name/${name}`;
        console.log('->--'+url);
        return this.http.get(url)
            .toPromise()
            .then(response => response.json() as RestaurantType)
            //.catch(this.handleError);
    }


  //busca pelo id
    getById(id: number): Promise<RestaurantType> {
        const url = `${this.apiUrl}/${id}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response.json() as RestaurantType)
        
            //.catch(this.handleError);
    }
    //Metodo POST
    post(restaurantType: RestaurantType): Promise<RestaurantType> {
        return this.http
            .post(this.apiUrl, JSON.stringify(restaurantType), { headers: this.headers })
            .toPromise()
            //valida resposta
            .then(res => res.json() as RestaurantType)
            //.catch(this.handleError);
    }
    
     //Metodo POST
    update(restaurantType: RestaurantType): Promise<RestaurantType> {
        const url = `${this.apiUrl}/${restaurantType.id}`;
        return this.http
            .put(url, JSON.stringify(restaurantType), { headers: this.headers })
            .toPromise()
            //valida resposta
            .then(res => res.json() as RestaurantType)
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
