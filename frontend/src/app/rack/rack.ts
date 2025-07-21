
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product, ProductService } from '../service/product.service'; // Adjust path
import { RackService, Rack } from '../service/rack.service';
import { FormsModule } from '@angular/forms';
import { ReportService } from '../service/report.service';
import { CommonModule } from '@angular/common';
import { Report } from '../service/report.service';
import { ImportExportService, ImportsExports } from '../service/importExport.service';
declare var bootstrap: any;

@Component({
  selector: 'app-rack',
  imports: [CommonModule,FormsModule],
  templateUrl: './rack.html',
  styleUrls: ['./rack.css']
})
export class RackC implements OnInit {
  rack?: Rack;
  products: Product[] = [];
  allProducts: Product[] = [];
  importDescription: string = '';
  importReceivedFrom: string = '';

  constructor(
    private route: ActivatedRoute,
    private rackService: RackService,
    private productService: ProductService,
    private reportService: ReportService,
    private importExportService: ImportExportService
  ) {}

  ngOnInit(): void {
    this.productService.getAllProducts().subscribe({
      next: (products) => {
        this.allProducts = products;
      },
      error: (err) => console.error('Failed to load products:', err)
    });

    const rackId = Number(this.route.snapshot.paramMap.get('id'));

    this.rackService.getRackById(rackId).subscribe({
      next: rack => {
        this.rack = rack;
        this.products = rack.products || [];
      },
      error: err => console.error('Error loading rack:', err)
    });
  }

  openEditRackModal() {
  }

  importProduct() {
  }

  exportProduct() {
  }
  
  importEntries: { productId: number | null; amount: number | null }[] = [
  { productId: null, amount: null }
  ];

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

      // Loop and post ImportExport for each product
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
}