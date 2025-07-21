import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WarehouseService, Warehouse  } from '../service/warehouse.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Rack } from '../service/rack.service';
import { RackService } from '../service/rack.service';
import { Router } from '@angular/router';
declare var bootstrap: any;

@Component({
  selector: 'app-warehouse',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './warehouse.html',
  styleUrl: './warehouse.css'
})
export class WarehouseC implements OnInit {
  warehouse?: Warehouse;
  editableWarehouse: any = {};
  newRackDescription: string = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private warehouseService: WarehouseService,
    private rackService: RackService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    
    this.warehouseService.getWarehouses().subscribe({
      next: (warehouses) => {
        this.warehouse = warehouses.find(w => w.id === id);
      },
      error: (err) => {
        console.error('Failed to load warehouses', err);
      }
    });
  }

  
  onRackClick(rack: Rack): void {
    this.router.navigate(['/rack', rack.id]);
  }

  openEditModal() {
    this.editableWarehouse = { ...this.warehouse };

   
    const modal = new bootstrap.Modal(document.getElementById('editWarehouseModal'));
    modal.show();
  }

  saveWarehouse() {
    if (!this.warehouse) return;

    
    this.warehouse.name = this.editableWarehouse.name;
    this.warehouse.description = this.editableWarehouse.description;

    
    this.warehouseService.updateWarehouse(this.warehouse).subscribe({
      next: updated => {
        console.log('Warehouse updated:', updated);

       
        const modal = bootstrap.Modal.getInstance(document.getElementById('editWarehouseModal'));
        modal?.hide();
      },
      error: err => {
        console.error('Failed to update warehouse', err);
       
      }
    });
  }

  onAddRack(): void {
  this.newRackDescription = '';
  const modal = new bootstrap.Modal(document.getElementById('addRackModal'));
  modal.show();
  }

  saveNewRack(): void {
  if (!this.newRackDescription.trim() || !this.warehouse) return;

  const newRack = {
    description: this.newRackDescription,
    warehouseId: this.warehouse.id
  };

  this.rackService.addRackToWarehouse(newRack).subscribe({
    next: (createdRack) => {
      
      this.warehouse?.racks.push(createdRack);

      
      const modal = bootstrap.Modal.getInstance(document.getElementById('addRackModal'));
      modal?.hide();

      
      this.newRackDescription = '';
    },
    error: (err) => {
      console.error('Failed to add rack:', err);
    }
  });
 }

}