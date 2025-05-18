import {Component, OnInit, inject, signal} from "@angular/core";
import {Product} from "app/products/data-access/product.model";
import {ProductsService} from "app/products/data-access/products.service";
import {ProductFormComponent} from "app/products/ui/product-form/product-form.component";
import {ButtonModule} from "primeng/button";
import {CardModule} from "primeng/card";
import {DataViewModule} from 'primeng/dataview';
import {DialogModule} from 'primeng/dialog';
import {AppComponent} from "../../../app.component";
import {FormsModule} from "@angular/forms";
import {PaginatorModule} from "primeng/paginator";
import {InputTextModule} from "primeng/inputtext";
import {CurrencyPipe, NgOptimizedImage} from "@angular/common";
import {TagModule} from "primeng/tag";
import {RatingModule} from "primeng/rating";

const emptyProduct: Product = {
  id: null,
  code: "",
  name: "",
  description: "",
  image: "",
  category: "",
  price: 0,
  quantity: 0,
  internalReference: "",
  shellId: 0,
  inventoryStatus: "INSTOCK",
  rating: 0,
  createdAt: null,
  updatedAt: null,
};

@Component({
  selector: "app-product-list",
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.scss"],
  standalone: true,
  imports: [DataViewModule, CardModule, ButtonModule, DialogModule, ProductFormComponent, FormsModule, PaginatorModule, InputTextModule, CurrencyPipe, TagModule, RatingModule, NgOptimizedImage],
})
export class ProductListComponent implements OnInit {
  private readonly productsService = inject(ProductsService);
  rowsPerPage = 3;
  searchQuery = '';
  public readonly products = this.productsService.products;
  filteredProducts: Product[] = [];

  public isDialogVisible = false;
  public isCreation = false;
  public readonly editedProduct = signal<Product>(emptyProduct);

  constructor(public appComponent: AppComponent) {
  }

  ngOnInit() {
    this.loadProducts();
  }

  loadProducts() {
    this.productsService.get().subscribe({
      next: () => {
        this.filteredProducts = [...this.products()];
      },
      error: (err) => {
        console.error('Erreur lors du chargement des produits', err);
      }
    });
  }

  filterProducts() {
    if (!this.searchQuery) {
      this.filteredProducts = [...this.products()];
    } else {
      const query = this.searchQuery.toLowerCase();
      this.filteredProducts = this.products().filter(product =>
        product.name.toLowerCase().includes(query) ||
        product.description.toLowerCase().includes(query) ||
        product.category.toLowerCase().includes(query)
      );
    }
  }

  public onCreate() {
    this.isCreation = true;
    this.isDialogVisible = true;
    this.editedProduct.set(emptyProduct);
  }

  public onUpdate(product: Product) {
    this.isCreation = false;
    this.isDialogVisible = true;
    this.editedProduct.set(product);
  }

  public onDelete(product: Product) {
    if (product.id !== null) {
      this.productsService.delete(product.id).subscribe();
    }
  }

  public onSave(product: Product) {
      if (this.isCreation) {
        const newProduct = {
          ...product,
          createdAt: new Date().toISOString()
        };
        this.productsService.create(newProduct).subscribe({
          next: () => {
            this.loadProducts();
          },
          error: (err) => console.error('Erreur création produit', err)
        });
      } else {
        const updatedProduct = {
          ...product,
          updatedAt: new Date().toISOString()
        };
        this.productsService.update(updatedProduct).subscribe({
          next: () => {
            this.loadProducts();
          },
          error: (err) => console.error('Erreur création produit', err)
        });
      }
    this.closeDialog();
  }

  public onCancel() {
    this.closeDialog();
  }

  private closeDialog() {
    this.isDialogVisible = false;
  }

  public addToCart(product: Product) {
    this.appComponent.addToCart(product);
  }

  public removeFromCart(product: Product) {
    this.appComponent.removeFromCart(product);
  }

  getStatusSeverity(status: string) {
    switch (status) {
      case 'INSTOCK': return 'success';
      case 'LOWSTOCK': return 'warning';
      case 'OUTOFSTOCK': return 'danger';
      default: return 'info';
    }
  }

  resetSearch() {
    this.searchQuery = '';
    this.filterProducts();
  }

  addToWishlist(product: Product) {
  }

}
