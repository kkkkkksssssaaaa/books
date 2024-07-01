export class Province {
  private _name: string;
  private _producers: Producer[];
  private _demand: number;
  private _price: number;
  private _totalProduction: number;

  constructor(doc: ProvinceData) {
    this._name = doc.name;
    this._producers = [];
    this._totalProduction = 0;
    this._demand = doc.demand;
    this._price = doc.price;
    
    doc.producers.forEach(d => {
      this.addProducer(new Producer(this, d));
    });
  }

  get name() {
    return this._name;
  }

  get producers() {
    return this._producers.slice();
  }

  get totalProduction() {
    return this._totalProduction;
  }

  set totalProduction(arg: number) {
    this._totalProduction = arg;
  }

  get demand() {
    return this._demand;
  }

  set demand(arg: number) {
    this._demand = arg
  }

  get price() {
    return this._price;
  }

  set price(arg: number) {
    this._price = arg;
  }

  get shortfall() {
    return this._demand - this._totalProduction;
  }

  get profit() {
    return this.demandValue - this.demandCost;
  }

  get demandValue() {
    return this.satisfiedDemand * this.price;
  }

  get satisfiedDemand() {
    return Math.min(this._demand, this._totalProduction);
  }

  get demandCost() {
    let remainingDemand = this.demand;
    let result = 0;

    this.producers.sort((a, b) => a.cost - b.cost)
      .forEach(p => {
        const contribution = Math.min(remainingDemand, p.production);

        remainingDemand -= contribution;
        
        result += contribution * p.cost;
      });
    
    return result;
  }

  addProducer = (arg) => {
    this._producers.push(arg);
    this._totalProduction += arg.production;
  }
}

class Producer {
  private _province: Province;
  private _cost: number;
  private _name: string;
  private _production: number;

  constructor(
    aProvince: Province,
    data
  ) {
    this._province = aProvince;
    this._cost = data.cost;
    this._name = data.name;
    this._production = data.production || 0;
  }

  get name() {
    return this._name;
  }

  get cost() {
    return this._cost;
  }

  set cost(arg: number) {
    this._cost = arg;
  }

  get production() {
    return this._production;
  }

  set production(arg: number) {
    const newProduction = Number.isNaN(arg) ? 0 : arg;

    this._province.totalProduction += (newProduction - this._production);
    this._production = newProduction;
  }
}

export interface ProvinceData {
  name: string;
  demand: number;
  price: number;
  producers: ProducerData[]
}

export interface ProducerData {
  name: string;
  cost: number;
  production: number;
}

export const sampleProvinceData = (): ProvinceData => {
  return {
    name: "Asia",
    producers: [
      {
        name: "Byzantium",
        cost: 10,
        production: 9
      },
      {
        name: "Attalia",
        cost: 12,
        production: 10
      },
      {
        name: "Sinope",
        cost: 10,
        production: 6
      }
    ],
    demand: 30,
    price: 20
  }
}