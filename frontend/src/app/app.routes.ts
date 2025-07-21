import { Routes } from '@angular/router';
import { WarehouseC } from './warehouse/warehouse';
import { WarehouseList } from './warehouse-list/warehouse-list';
import { RackC } from './rack/rack';

export const routes: Routes = [
    { path: '', component: WarehouseList },
    { path: 'warehouse/:id', component: WarehouseC },
    { path: 'rack/:id', component: RackC }
];
