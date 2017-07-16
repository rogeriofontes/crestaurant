import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { PratosComponent } from './pratos/pratos.component';
import { PlateFormComponent } from './pratos/plate-form/plate-form.component';
import { RestaurantesComponent } from './restaurantes/restaurantes.component';
import { RestaurantsTypesComponent } from './restaurants-types/restaurants-types.component';
import { RestaurantFormComponent } from './restaurantes/restaurant-form/restaurant-form.component';
import { RestaurantTypeFormComponent } from './restaurants-types/restaurant-type-form/restaurant-type-form.component';
import { RestaurantDetailsComponent } from './restaurantes/restaurant-details/restaurant-details.component';

const APP_ROUTES: Routes = [
    { path: '', component: HomeComponent },
    { path: 'home', component: HomeComponent },
    { path: 'restaurants-types', component: RestaurantsTypesComponent },
    { path: 'restaurants-types/form', component: RestaurantTypeFormComponent },
    { path: 'restaurants-types/form/:id', component: RestaurantTypeFormComponent },
    { path: 'restaurantes', component: RestaurantesComponent },
    { path: 'restaurantes/form', component: RestaurantFormComponent },
    { path: 'restaurantes/form/:id', component: RestaurantFormComponent },
    { path: 'pratos', component: PratosComponent },
    { path: 'pratos/form', component: PlateFormComponent },
    { path: 'pratos/form/:id', component: PlateFormComponent },
    { path: 'restaurantes/:id', component: RestaurantDetailsComponent }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(APP_ROUTES);