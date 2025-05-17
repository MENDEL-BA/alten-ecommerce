import {
  Component, signal,
} from "@angular/core";
import { RouterModule } from "@angular/router";
import { SplitterModule } from 'primeng/splitter';
import { ToolbarModule } from 'primeng/toolbar';
import { PanelMenuComponent } from "./shared/ui/panel-menu/panel-menu.component";
import {Button} from "primeng/button";
import {Product} from "./products/data-access/product.model";
import {DialogModule} from "primeng/dialog";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
  standalone: true,
  imports: [RouterModule, SplitterModule, ToolbarModule, PanelMenuComponent, Button, DialogModule, NgIf, NgForOf],
})
export class AppComponent {
  title = "ALTEN SHOP";

  public isCartVisible = false;
  public cart: Product[] = [];
  public cartQuantity = signal<number>(0);

  public toggleCart() {
    this.isCartVisible = !this.isCartVisible;
  }

  public addToCart(product: Product) {
    this.cart.push(product);
    this.cartQuantity.set(this.cart.length);
  }

  public removeFromCart(product: Product) {
    const index = this.cart.findIndex((p) => p.id === product.id);
    if (index !== -1) {
      this.cart.splice(index, 1);
      this.cartQuantity.set(this.cart.length);
    }
  }
}
