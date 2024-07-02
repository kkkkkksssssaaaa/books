const priceOrder = (
  product: Product,
  quantity: number,
  shippingMethod: ShippingMethod
) => {
  const priceData: PriceData = calculatePricingData(product, quantity);
  
  return applyShipping(priceData, shippingMethod);
}

const calculatePricingData = (
  product: Product,
  quantity: number
): PriceData => {
  const basePrice = product.basePrice * quantity;
  const discount = Math.max(quantity - product.discountThreshold, 0)
    * product.basePrice
    * product.discountRate;

  return {
    basePrice: basePrice,
    quantity: quantity,
    discount: discount
  };
}

const applyShipping = (
  priceData: PriceData,
  shippingMethod: ShippingMethod,
) => {
  const shippingPerCase = (priceData.basePrice > shippingMethod.discountThreshold)
    ? shippingMethod.discountedFee : shippingMethod.feePerCase;
  const shippingCost = priceData.quantity * shippingPerCase;
  const price = priceData.basePrice - priceData.discount + shippingCost;

  return price;
}

interface PriceData {
  basePrice: number;
  quantity: number;
  discount: number;
}

interface Product {
  basePrice: number;
  discountThreshold: number;
  discountRate: number;
}

interface ShippingMethod {
  discountThreshold: number;
  discountedFee: number;
  feePerCase: number;
}