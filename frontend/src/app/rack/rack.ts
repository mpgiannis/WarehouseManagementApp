
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product, ProductService } from '../service/product.service';
import { RackService, Rack } from '../service/rack.service';
import { FormsModule } from '@angular/forms';
import { ReportService } from '../service/report.service';
import { CommonModule } from '@angular/common';
import { Report } from '../service/report.service';
import { ImportExportService, ImportsExports } from '../service/importExport.service';
import { StockService, Stock } from '../service/stock.service';
declare var bootstrap: any;

@Component({
  selector: 'app-rack',
  imports: [CommonModule,FormsModule],
  templateUrl: './rack.html',
  styleUrls: ['./rack.css']
})
export class RackC implements OnInit {
  rack?: Rack;
  products: any[] = [];
  allProducts: Product[] = [];
  importDescription: string = '';
  importReceivedFrom: string = '';
  stockList: Stock[] = [];
  exportEntries:  { productId: number | null; amount: number | null }[] = [
  { productId: null, amount: null }
  ];
  exportDescription: string = '';
  exportSentTo: string = '';
  importEntries: { productId: number | null; amount: number | null }[] = [
  { productId: null, amount: null }
  ];

  constructor(
    private route: ActivatedRoute,
    private rackService: RackService,
    private productService: ProductService,
    private reportService: ReportService,
    private importExportService: ImportExportService,
    private stockService: StockService
  ) {}

  ngOnInit(): void {
  
    const rackId = Number(this.route.snapshot.paramMap.get('id'));

    this.productService.getAllProducts().subscribe({
      next: (prod) => {
        this.allProducts = prod;
        console.log(this.allProducts);
        this.loadProductsInStock(rackId);
      },

      error: (err) => console.error('Failed to load products:', err)
    });

    

    this.rackService.getRackById(rackId).subscribe({
      next: rack => {
        this.rack = rack;
       
        console.log(this.rack);
      },
      error: err => console.error('Error loading rack:', err)
    });

  }

  loadProductsInStock(rackId: number) {
    this.stockService.getStockByRackId(rackId).subscribe({
      next: (stockItems) => {
        this.stockList = stockItems;

        
        this.products = stockItems.map(stockItem => {
          const productDetails = this.allProducts.find(p => p.id === stockItem.productId);
          return {
            ...productDetails!, 
            quantity: stockItem.quantity 
          };
        });
        console.log(this.products);
      },
      error: (err) => console.error('Failed to load stock:', err)
    });
  }
  editableRack: Rack = { id: 0, description: '' };
  openEditRackModal() {
  if (!this.rack) return;
  this.editableRack = { ...this.rack }; // copy current rack
  const modal = new bootstrap.Modal(document.getElementById('editRackModal'));
  modal.show();
}

  openExportModal(){
    this.exportEntries = [{ productId: null, amount: null }];
    const modal = new bootstrap.Modal(document.getElementById('exportProductModal'));
    modal.show();

  }

  openImportModal() {
    this.importEntries = [{ productId: null, amount: null }];
    const modal = new bootstrap.Modal(document.getElementById('importProductModal'));
    modal.show();
  }

  addImportEntry() {
    this.importEntries.push({ productId: null, amount: null });
  }

  removeImportEntry(index: number) {
    this.importEntries.splice(index, 1);
  }

  addExportEntry() {
    this.exportEntries.push({ productId: null, amount: 0 });
  }

  removeExportEntry(index: number) {
    this.exportEntries.splice(index, 1);
  }
  confirmImport() {
    const validEntries = this.importEntries.filter(e => e.productId && e.amount);
    const infos: { [key: string]: number } = {};

    validEntries.forEach(entry => {
      const productIdNum = Number(entry.productId);
      const product = this.allProducts.find(p => p.id === productIdNum);
      if (!product) {
        console.warn(`Product with id ${productIdNum} not found in allProducts`);
      } else {
        infos[product.name] = entry.amount!;
      }
    });
    console.log('infos object before stringify:', infos);

    const reportDto = {
      type: 'import',
      dateRep: new Date().toISOString().split('T')[0],
      descriptionReason: this.importDescription,
      receivedDeliveredBy: this.importReceivedFrom,
      infos: JSON.stringify(infos) 
    };
    console.log('infos string:', reportDto.infos);
    this.reportService.addReport(reportDto).subscribe({
      next: (createdReport) => {
        const modal = bootstrap.Modal.getInstance(document.getElementById('importProductModal'));
        modal?.hide();
        this.importEntries = [{ productId: null, amount: null }];
        this.importDescription = '';
        this.importReceivedFrom = '';
        alert('Import report saved!');

        
        validEntries.forEach(entry => {
          const importExportDto = {
          reportId: (createdReport as any).id,
          productId: entry.productId!,
          rackId: this.rack?.id!,
          amount: entry.amount!
          };
          this.importExportService.addImportExport(importExportDto).subscribe({
            next: () => console.log('ImportExport saved'),
            error: err => console.error('Failed to save ImportExport', err)
          });
        });
      },
      error: (err) => {
        console.error('Failed to save import report:', err);
        alert('Import failed.');
      }
    });
  }

  getAvailableQuantity(productId: number | null): number {
  if (!productId) return 0;
  const stockItem = this.stockList.find(s => s.productId === productId);
  return stockItem ? stockItem.quantity : 0;
  }

  confirmExport() {
    if (!this.rack) return;
    const validEntries = this.exportEntries.filter(e => e.productId && e.amount);
    if (validEntries.length === 0) {
      alert('No valid export entries!');
      return;
    }

    const infos: { [key: string]: number } = {};
    validEntries.forEach(entry => {
  const productIdNum = Number(entry.productId);
  const product = this.allProducts.find(p => p.id === productIdNum);
  console.log('Checking entry:', entry, 'Found product:', product);
  if (product) {
    infos[product.name] = entry.amount!;
  }
});

    console.log('valid export entries:', validEntries);
    console.log('infos object:', infos);

    const reportDto = {
      type: 'export',
      dateRep: new Date().toISOString().split('T')[0],
      descriptionReason: this.exportDescription,
      receivedDeliveredBy: this.exportSentTo,
      infos: JSON.stringify(infos)
    };


    this.reportService.addReport(reportDto).subscribe({
    next: (createdReport) => {
      const reportId = (createdReport as any).id;

      validEntries.forEach(entry => {
        const exportDto = {
          reportId: reportId,
          productId: entry.productId!,
          rackId: this.rack!.id,
          amount: entry.amount!
        };
        this.importExportService.addImportExport(exportDto).subscribe({
          next: () => console.log('Export saved'),
          error: err => console.error('Failed to save Export', err)
        });
      });
      this.loadProductsInStock(this.rack!.id);
      this.exportEntries = [{ productId: null, amount: 0 }];
      this.exportDescription = '';
      this.exportSentTo = '';

      const modal = bootstrap.Modal.getInstance(document.getElementById('exportProductModal'));
      modal?.hide();

      alert('Export report saved!');
    },
    error: err => {
      console.error('Failed to save export report:', err);
      alert('Export failed.');
    }
    });
  }

   saveRack() {
  if (!this.editableRack) return;
  this.rackService.updateRack(this.editableRack).subscribe({
    next: (updatedRack) => {
      this.rack = updatedRack;
      const modal = bootstrap.Modal.getInstance(document.getElementById('editRackModal'));
      modal?.hide();
      alert('Rack updated!');
    },
    error: (err) => {
      console.error('Failed to update rack:', err);
      alert('Update failed.');
    }
  });
}










}