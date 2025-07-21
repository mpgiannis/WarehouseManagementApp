import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Warehouse, WarehouseService } from '../service/warehouse.service';
import { RouterModule, Router } from '@angular/router';

@Component({
  selector: 'app-warehouse-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './warehouse-list.html',
  styleUrl: './warehouse-list.css'
})
export class WarehouseList implements OnInit {
  protected readonly title = signal('Warehouse App');
  warehouses: Warehouse[] = [];

  constructor(
    private warehouseService: WarehouseService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadWarehouses();
  }

  loadWarehouses() {
    this.warehouseService.getWarehouses().subscribe({
      next: (data) => {
        this.warehouses = data;
      },
      error: (err) => {
        console.error('Error loading warehouses', err);
      }
    });
  }

  goToWarehouse(warehouse: Warehouse) {
    this.router.navigate(['/warehouse', warehouse.id]);
  }

  
}
