interface OrderData {
  quantity: number;
  itemPrice: number;
}

class Order {
  private _quantity: number;
  private _itemPrice: number;

  constructor(data: OrderData) {
    this._quantity = data.quantity;
    this._itemPrice = data.itemPrice;
  }

  get quantity() {
    return this._quantity;
  }

  get itemPrice() {
    return this._itemPrice;
  }

  get price() {
    return this.basePrice
    - this.quantityDiscount
    + this.shipping;
  }

  get basePrice() {
    return this._quantity * this._itemPrice;
  }

  get quantityDiscount() {
    return Math.max(0, this._quantity - 500) * this._itemPrice * 0.05;
  }

  get shipping() {
    return Math.min(this.basePrice * 0.1, 100);
  }
}