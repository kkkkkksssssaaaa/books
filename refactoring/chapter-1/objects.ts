import invoicesData from "./invoices.json"
import playsData from "./plays.json"

export interface Invoice {
  customer: string,
  performances: Performance[]
}

export interface Performance {
  playId: string,
  audience: string
}

export interface Play {
  name: string,
  type: string
}

export class Statement {
  customer?: string;
  performances?: EnrichPerformance[];
  totalAmount?: number;
  totalVolumeCredits?: number;

  constructor() {

  }
}

export class EnrichPerformance {
  play?: Play;
  amount?: number;
  volumeCredits?: number;
  playId?: string;
  audience?: string;

  constructor() {
    
  }
}

export const invoices: Invoice[] = <Invoice[]>JSON.parse(JSON.stringify(invoicesData));
export const plays: Map<string, Play> = <Map<string, Play>>JSON.parse(JSON.stringify(playsData));